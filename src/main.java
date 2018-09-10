package src;

import src.*;
import java.util.Scanner;
import java.util.ArrayList;

public class main{

    ///*
    //local
    public static void main(String[] args){
        BlockChain bc = new BlockChain();
        System.out.print("Welcome. Press your Enter key to start.");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();

        System.out.println("Would you like to mine locally or with others? (1) Locally (2) Others");
        String input = "";
        int l = 1;
        input = scan.nextLine();
        try {
            l = Integer.parseInt(input);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //scan.next();
        if(l==1){
            //System.out.println("Please enter your message");
            //String mess = scan.nextLine();
            //String data = new String();
            //data.add(mess);
            //System.out.println(mess);
            bc.createGenesis("Genesis block");
            bc.printBlockChain();
            System.out.println("Enter exit or control-c to exit");
            
            //String input;
            
            do {
                System.out.println("How many blocks would u like to mine?");
                input = scan.nextLine();
                try {
                    int i = Integer.parseInt(input.trim());
                    for(int d = 0; d < i; d++){
                        System.out.println("Please enter your message");
                        String k = scan.nextLine();
                        //String temp = new String();
                        //temp.add(k);
                        if(!bc.createBlock(k))
                            return;
                    // System.out.println();
                        bc.printBlock(bc.getLastIndex());
                    // System.out.println();
                    }

                } catch (Exception e) {
                    System.out.println("Please enter a number");
                    continue;
                }
                bc.printBlockChain();
                System.out.print("Press your Enter key to continue or enter \"exit\" to exit:  ");
                input = scan.nextLine();
            } while(!input.equalsIgnoreCase("exit"));
        } else {
            System.out.println("Are you the initial node? (y or n)");
            input = scan.nextLine();
            if(input.trim().equalsIgnoreCase("y")){
                Server server;
                System.out.print("Enter port above 1024:  ");
                int port = 0;
                try {
                    while(port<1024){
                        port = scan.nextInt();
                        if(port>=1024)
                            break;
                        System.out.println("Enter port above 1024:  ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //System.out.println(port);
                try {
                    server = new Server(port);
                    server.start();
                    //System.out.println("client");
                    server.runClient();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } else {

            }
        }
        
    }
    //*/
    /*
    //server
    public static void main(String[] args){
        System.out.println("Welcome. Please enter port number:");
        Scanner scan = new Scanner(System.in);

        int port = scan.nextInt();
        
    }
    //*/
}