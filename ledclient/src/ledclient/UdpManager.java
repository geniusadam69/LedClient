/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam
 */
public class UdpManager {
    private Thread t;
    
    private InetAddress IPAddress;
    private DatagramSocket sendSocket;
    private DatagramSocket listenSocket;
    private boolean noMessage;
    
    public UdpManager(){
        t = new Thread();
        try {
            sendSocket = new DatagramSocket(8888);
            listenSocket = new DatagramSocket(8889);
        } catch (SocketException ex) {
            Logger.getLogger(UdpManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        noMessage=true;
            
        
    }
    
    public void begin(){
        
    }
    public void listen(){
        try {
            byte[] receiveData = new byte[1024];
            while(noMessage){
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                listenSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());
                System.out.println("FROM SERVER:" + modifiedSentence);
            }
        }catch(IOException e){
            System.err.println("IOException " + e);
        }
    }
    
    public void sendMessage(String message){
        
    }
    
    private class SendMessage implements Runnable {
        private int depth = 1;
        private String message;
        private SendMessage(String message){
            this.message = message;
        }
        @Override
        public void run(){
            byte[] sendData = new byte[1024];
            byte[] recData = new byte[1024];
            String sentence = message;
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
            try {
                sendSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(recData, recData.length);
                sendSocket.setSoTimeout(3000);
                try {
                    sendSocket.receive(receivePacket);
                } catch (SocketTimeoutException e){
                    if (depth ==3) throw new ConnectionException (message);
                    depth++;
                    run();
                }
                
                
            } catch (IOException ex) {
                if (depth ==3) throw new ConnectionException (message);
                    depth++;
                    run();
            }
        }
        
    }
    private class ConnectionException extends Exception{
        public ConnectionException(String message){
            super(message);
        }
        
    }
}
