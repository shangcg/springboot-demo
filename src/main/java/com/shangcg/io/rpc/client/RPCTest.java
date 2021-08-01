package com.shangcg.io.rpc.client;

import com.shangcg.io.rpc.register.Server;
import com.shangcg.io.rpc.register.ServerCenter;
import com.shangcg.io.rpc.server.HelloService;
import com.shangcg.io.rpc.server.HelloServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * RPC 测试
 */
public class RPCTest {
    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServerCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }
}
