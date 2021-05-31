package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * java NIO通信服务端
 *
 * NIO三大核心组件：channel(通道)、 缓冲(Buffer)、 选择器(Selector)
 * NIO与传统IO三大区别： 面向缓冲、 同步非阻塞、 选择器(多路复用)
 * 参考： https://blog.csdn.net/dutsoft/article/details/81585197
 */
public class NIOTimeServer implements Runnable{

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    public NIOTimeServer(int port){
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("nio time server starts in port:" + port);
        }catch (Exception e){

        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeySet.iterator();

                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e){
                        if (key != null){
                            key.cancel();
                            if (key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch (Exception e){

            }
        }

        if (selector != null){
            try {
                selector.close();
            }catch (Exception e){
                //ignore
            }
        }
    }


    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            //处理新接入的请求
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = ssc.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }

        if (key.isReadable()){
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(readBuffer);
            if (readBytes > 0){
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body = new String(bytes, "UTF-8");

                System.out.println("nio time server receive:" + body);

                String currTime = new Date(System.currentTimeMillis()).toString();
                doWrite(socketChannel, currTime);
            }else if (readBytes < 0){
                //关闭对端链路
                key.cancel();
                socketChannel.close();
            }else {
                //读到0字节 忽略
            }
        }
    }

    public void doWrite(SocketChannel socketChannel, String response) throws IOException {
        if (response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        System.out.println("server start in port :" + port);
        //创建一个新的NIO time server处理socket链路
        NIOTimeServer timeServer = new NIOTimeServer(port);
        new Thread(timeServer, "NIO-NIOTimeServer").start();
    }
}
