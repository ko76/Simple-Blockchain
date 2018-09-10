package src;

import src.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{

    private ServerSocket server;
    private ArrayList<Socket> nodes;
    private boolean clientFinished;
    private int port;


    public Server(int port) throws Exception{
        this.port = port;
        nodes = new ArrayList<Socket>();
        clientFinished = false;
        
        System.out.println("-----Creating Server-----");
        server = new ServerSocket(port);
        System.out.println("-----Starting server at port " + port+"-----");
        
    }

    public void runClient() throws Exception{
        Client client = new Client(port);
        
        client.startClient();
    }

    public void run(){
        while(!clientFinished){
            try {
                Socket socket = server.accept();
                nodes.add(socket);
                new ServerThread(socket).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Socket> getNodes(){
        return nodes;
    }



}