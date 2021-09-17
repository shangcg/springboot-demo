package com.shangcg.mode.proxy;

/**
 * jdk动态代理
 * 三要素： 接口interface、 实现类impl（被代理对象）、  代理对象proxy
 *
 * 此类为接口
 *
 * @author shangchenguang
 * @date 2021/8/25 3:58 下午
 */
public interface JdkProxyInterface {

    /**
     * 跳舞的人
     * @param name
     * @return
     */
    String dance(String name);
}
