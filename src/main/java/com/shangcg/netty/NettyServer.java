package com.shangcg.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * netty 服务端
 */
public class NettyServer {
    public void start(InetSocketAddress address) {
        //配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)//绑定线程池
                .channel(NioServerSocketChannel.class)
                .localAddress(address)
                .option(ChannelOption.SO_BACKLOG, 128) // 服务端接受连接队列的长度，如果队列已满，客户端连接将被拒绝
                .childOption(ChannelOption.SO_KEEPALIVE, true); //保持长链接，2小时无数据激活心跳机制

        //绑定端口
        ChannelFuture future = null;
        try {
            future = bootstrap.bind(address).sync();
            System.out.println("netty 服务器开始监听端口："+ address.getPort());

            //关闭channel和块， 直到它被关闭
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
            bossGroup.shutdownNow();
            workerGroup.shutdownNow();
        }

    }
}
