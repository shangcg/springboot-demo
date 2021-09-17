package com.shangcg.mode.proxy;

/**
 * JDK动态代理三要素
 * 1 接口
 * 2 实现类
 * 3 代理对象
 *
 * 此类为实现类
 *
 * @author shangchenguang
 * @date 2021/8/25 4:07 下午
 */
public class JdkProxyInterfaceImpl implements JdkProxyInterface {

    @Override
    public String dance(String name) {
        return "跳个钢管舞";
    }


}
