package com.shangcg.redis.aop;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@Component
@Aspect
public class RedisCacheAspect {

    private static Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);


    @Resource
    private Jedis jedis;

    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     * @return
     */
    //只切有这个自定义注解的方法
    @Around("@annotation(com.shangcg.redis.aop.CacheAnno)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{

        long startTime = System.currentTimeMillis();

        //获得目标方法的方法名称
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = signature.getName();
        logger.info("方法名："+methodName);

        //获得目标方法所在类
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        logger.info("类名："+className);
        StringBuilder builder = new StringBuilder();
        builder.append(methodName).append(":");

        //获取目标方法的参数
        Object[] args = proceedingJoinPoint.getArgs();
        //拼接valueKey
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            builder.append(arg);
            if (i == args.length-1){
                break;
            }
            builder.append(":");
        }
        String valueKey = builder.toString();

        //判断当前这个方法在缓存中是否存在
        //存在 直接从缓存中取数据 用fastjson反序列化
        Object result = null;
        if(jedis.hexists(className,valueKey)){
            logger.info("**********从Redis中查到了数据**********");
            logger.info("Redis的KEY值:"+className);
            logger.info("REDIS的VALUEKEY值:"+valueKey);
            String s = jedis.hget(className, valueKey);
            result = JSONObject.parse(s);

            logger.info("命中时处理查询所用时间:"+(System.currentTimeMillis()-startTime));
        }else {
            //不存在 放行 去数据库中查并存入 redis 中
            logger.info("**********没有从Redis查到数据**********");
            logger.info("**********开始从MySQL查询数据**********");
            //这里注意 一定要有返回值，否则会出现空指针
            result = proceedingJoinPoint.proceed();
            logger.info("未命中时处理查询所用时间:"+(System.currentTimeMillis()-startTime));
            jedis.hset(className,valueKey,JSONObject.toJSONStringWithDateFormat(result,"yyyy-MM-dd HH:mm:ss"));

            logger.info("Redis的KEY值:"+className);
            logger.info("REDIS的VALUEKEY值:"+valueKey);
            logger.info("**********数据成功保存到Redis缓存!!!**********");
        }
        jedis.close();
        return result;
    }

    //增删改操作会先将对应的缓存从redis中删除，再重新查询数据库并重新存入redis缓存
    @After("@annotation(com.shangcg.redis.aop.CacheAnno)")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        jedis.del(name);
        jedis.close();
    }





}
