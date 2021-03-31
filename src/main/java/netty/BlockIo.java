package netty;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockIo {

    public void socketTest() throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);
        Socket clientSocket = serverSocket.accept();

       BufferedReader in = new BufferedReader(
               new InputStreamReader(clientSocket.getInputStream()));



    }


}
