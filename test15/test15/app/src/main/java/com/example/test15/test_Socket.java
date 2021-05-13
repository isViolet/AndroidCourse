package com.example.test15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class test_Socket implements Runnable {
    public static final int Server_port= 2000;
    @Override
    public void run() {
        System.out.println("S:Connectioning...");
        try {
            ServerSocket serverSocket=new ServerSocket(Server_port);
            while (true){
                Socket client=serverSocket.accept();
                System.out.println("S:Receing...");
                try {
                    BufferedReader breader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String str=breader.readLine();
                    System.out.println("S:Received: "+str);
                }catch (Exception e){
                    System.out.print("S:Error");
                    e.printStackTrace();
                }finally {
                    client.close();
                    System.out.println("S:Done");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(){
        new test_Socket();
    }
}
