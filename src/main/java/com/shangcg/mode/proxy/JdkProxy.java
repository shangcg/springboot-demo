package com.shangcg.mode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理对象
 *
 * @author shangchenguang
 * @date 2021/8/25 4:11 下午
 */
public class JdkProxy implements InvocationHandler {

    //被代理对象
    private Object target;

    public JdkProxy(Object target){
        this.target = target;
    }


    //此方法用于增强
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是加强版本");
        Object result = method.invoke(target, args);
        return result;
    }

    public Object createInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
