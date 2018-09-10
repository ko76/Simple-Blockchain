package src;

import src.*;
import java.net.*;
import java.io.*;

public class Client{
    Socket socket;

    public Client(int port) throws Exception{
        System.out.println("-----Connecting to port " + port+"-----");
        socket = new Socket("localhost", port);
        
    }

    public void startClient() throws Exception{
        System.out.println("-----Starting client node-----");
        InputStream is = socket.getInputStream();
        OutputStream os= socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        PrintWriter writer = new PrintWriter(os, true);
        writer.println("client started");
        String r = reader.readLine();
        System.out.println(r);


        reader.close();
        writer.close();
        is.close();
        os.close();
        socket.close();
    }
    
}