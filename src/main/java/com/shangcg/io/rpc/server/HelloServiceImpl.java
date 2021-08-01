package com.shangcg.io.rpc.server;

/**
 * rpc 服务提供者实现
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi(String name) {
        return "Hi" + name;
    }
}
