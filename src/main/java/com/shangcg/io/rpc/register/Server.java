package com.shangcg.io.rpc.register;

import java.io.IOException;

/**
 * rpc 注册中心
 */
public interface Server {

    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();

}
