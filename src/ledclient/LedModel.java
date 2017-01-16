/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam
 */
public class LedModel {
    private InetAddress IPAddress;
    private DatagramSocket clientSocket;
    private boolean[] ledStatus;
    public LedModel(){
        try {
            IPAddress = InetAddress.getByName("192.168.1.177");
            clientSocket = new DatagramSocket();
        } catch (UnknownHostException ex) {
            Logger.getLogger(LedModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(LedModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ledStatus = new boolean[2];
    }
    public void run() throws IOException{
        try {
            
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            String sentence = "Button1__Off";
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
            clientSocket.close();
        } catch (SocketException ex) {
            Logger.getLogger(LedModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(String message){
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = "Button1__Off";
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
        try {
            clientSocket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(LedModel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void processMessage(String message){
        if (message.equalsIgnoreCase("Button1__Pressed")){
            
        }
    }
    
    public boolean getLedStatus(int led){
        if (led<2 && led>0){
            return ledStatus[led];
        }
        return false;
    }
}
