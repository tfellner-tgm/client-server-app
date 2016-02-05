package com.fellner.clientserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Yoshi on 28.02.2015.
 */
public class ServerThread implements Runnable {
    static byte[] write;
    MainActivity ma;

    public ServerThread(MainActivity ma){
        this.ma = ma;
    }

    @Override
    public void run(){

        write = new byte[2];
        try {
            ma.findViewById(R.id.circle).setOnTouchListener(ma.getListener());
            ma.findViewById(R.id.view).setOnTouchListener(ma.getListener());
            ServerSocket serverSocket = new ServerSocket(12321);
            ma.setConnectionText("Waiting for Connection...");
            Socket clientSocket = serverSocket.accept();
            ma.setConnectionText("Connected");
            while (true){
                clientSocket.getOutputStream().write(write);
                Thread.sleep(200);
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
