package com.shangcg.redis.aop;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheAnno {

//    String key();		//定义key值
//    Class targetClass(); //定义目标类型
//    CACHE_TYPE cacheType() default CACHE_TYPE.FIND;
//
//    /**
//     * 查找和更新缓存的处理方法不一样，更新逻辑为先删除再同步数据到缓存
//     *
//     */
//    enum CACHE_TYPE{
//        FIND,		//定义查找
//        UPDATE		//定义更新
//    }

}
