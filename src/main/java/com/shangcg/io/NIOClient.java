package com.shangcg.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 客户端
 */
public class NIOClient implements Runnable {

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public NIOClient(String host, int port){
        this.host = "127.0.0.1";
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        }catch (IOException e){

        }
    }



    @Override
    public void run() {

        try {
            doConnect();
        }catch (Exception e){
            //
        }

        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.keys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try{
                        handleKey(key);
                    }catch (Exception e){
                        //
                    }
                }
            }catch (IOException e){
                //
            }

            if (selector != null){
                try {
                    selector.close();
                }catch (IOException e){
                    //
                }
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException{
        if (key.isValid()){
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()){
                if (socketChannel.finishConnect()){
                    System.out.println("client finish connect");
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(socketChannel, "query time");
                }else {
                    System.out.println("client connect failed, exit");
                    System.exit(1);
                }
            }
        }

        if (key.isReadable()){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(byteBuffer);
            if (readBytes > 0){
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);

                String currTime = new String(bytes, "UTF-8");
                System.out.println("curr time is: " + currTime);
            }else if(readBytes < 0){
                key.cancel();
                socketChannel.close();
            }else {
                //
            }
        }
    }

    public void doWrite(SocketChannel socketChannel, String request) throws IOException {
        if (request != null && request.trim().length() < 0){
            byte[] bytes = request.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            if (!byteBuffer.hasRemaining()){
                System.out.println("client send req succeed");
            }
        }
    }


    public void doConnect() throws IOException{
        if (socketChannel.connect(new InetSocketAddress(host, port))){
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            // doWrite()
        }else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    public static void main(String[] args) {
        new Thread(new NIOClient("127.0.0.1" ,8080), "NIO-NIOTimeServer").start();
    }
}
