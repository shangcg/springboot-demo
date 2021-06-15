package com.shangcg.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于socket的java网络编程
 * socket = IP + PORT
 * Socket用于创建双向通信的客户端 serverSocket用于创建双向通信的服务端
 * 此类为基于socket通信的java实现
 *
 */
public class SocketServer {

    //1 此处为BIO 阻塞式IO socket连接
    public void singleThreadServer() throws IOException {
        ServerSocket server = null;
        Socket socket = null;
        server = new ServerSocket(9999);
        System.out.println("server start");
        while (true){
            //监听来自客户端的连接，主线程阻塞在accept操作上
            socket = server.accept();
            //以下代码当建立连接后会执行，创建一个新线程处理socket链路
            new Thread(new TimeServerHandler(socket));
        }
    }

    /**
     * BIO的特点：从上边while循环内代码可见，当一个client请求过来时，server必须创建一个
     * 新的线程去处理客户端连接，创建和维护线程是很消耗资源的，所以BIO性能很低
     */


    /**
     * 2 上述一个线程处理一个client请求的进阶，创建一个线程池去处理client请求
     */
    public void multiThreadServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);

            Socket socket = null;
            //创建一个线程池处理socket链路
            TimeServerHandlerPool pool = new TimeServerHandlerPool(10, 1000);
            while (true){
                //监听来自客户端的连接，主线程阻塞在accept操作上 把客户端请求的socket封装成一个task，交给线程池去处理
                socket = serverSocket.accept();
                pool.execute(new TimeServerHandler(socket));
            }
        }finally {
            if (serverSocket != null){
                serverSocket.close();
            }
        }
    }




}

