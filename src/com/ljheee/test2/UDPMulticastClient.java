package com.ljheee.test2;

import java.net.DatagramPacket;   
 
import java.net.InetAddress;   
 
import java.net.MulticastSocket;   
 
    
 /**
  * 发送 
  * @author ljheee
  *
  */
public class UDPMulticastClient {   
 
    
  
         static String destAddressStr = "224.2.0.1";   
  
         static int destPortInt = 9998;   
  
         static int TTLTime = 4;   
  
         public static void main(String[] args) throws Exception {   
  
                      
  
                   InetAddress destAddress = InetAddress.getByName(destAddressStr);   
  
                   if(!destAddress.isMulticastAddress()){//检测该地址是否是多播地址   
  
                            throw new Exception("地址不是多播地址");   
  
                   }   
  
                   int destPort = destPortInt;   
  
                   int TTL = TTLTime;   
  
                   MulticastSocket multiSocket =new MulticastSocket();   
  
                   multiSocket.setTimeToLive(TTL);   
  
                   byte[] sendMSG = "11#msg".getBytes();   
  
                   DatagramPacket dp = new DatagramPacket(sendMSG, sendMSG.length, destAddress  , destPort);   
  
                   multiSocket.send(dp);   
  
                   multiSocket.close();   
  
         }   
  
}  

