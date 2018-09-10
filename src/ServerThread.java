package src;

import java.io.*;
import java.net.*;

import src.*;

public class ServerThread extends Thread{

    private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
       try {
            System.out.println("thread");
            InputStream is = socket.getInputStream();
            OutputStream os= socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            PrintWriter writer = new PrintWriter(os, true);
            String r = reader.readLine();
            writer.println(r);


            reader.close();
            writer.close();
            is.close();
            os.close();
            socket.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

}