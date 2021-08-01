package com.shangcg.io.rpc.register;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * rpc注册中心实现
 */
public class ServerCenter implements Server {

    //线程池处理socket请求
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //注册
    private static final HashMap<String, Class> serviceRegister = new HashMap<>();

    private static boolean isRunning = false;

    private static int port;


    public ServerCenter(int port){
        this.port = port;
    }

    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    @Override
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("server start");

        try {
            while (true){
                // 1.监听客户端的TCP连接，接到TCP连接后将其封装成task，由线程池执行
                executor.execute(new ServiceTask(serverSocket.accept()));
            }
        }finally {
            serverSocket.close();
        }

    }

    @Override
    public void register(Class serviceInterface, Class impl) {
        serviceRegister.put(serviceInterface.getName(), impl);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int getPort() {
        return 0;
    }


    public static class ServiceTask implements Runnable{
        Socket client;
        public ServiceTask(Socket socket){
            this.client = socket;
        }

        @Override
        public void run() {

            ObjectInputStream input = null;
            ObjectOutputStream output = null;

            try {
                input = new ObjectInputStream(client.getInputStream());
                String serviceName = input.readUTF();
                String methodName = input.readUTF();

                // 2.将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Class serviceClass = serviceRegister.get(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + " not found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);

                // 3.将执行结果反序列化，通过socket发送给客户端
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
