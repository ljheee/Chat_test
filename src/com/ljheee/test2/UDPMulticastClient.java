package com.ljheee.test2;

import java.net.DatagramPacket;   
 
import java.net.InetAddress;   
 
import java.net.MulticastSocket;   
 
    
 /**
  * ���� 
  * @author ljheee
  *
  */
public class UDPMulticastClient {   
 
    
  
         static String destAddressStr = "224.2.0.1";   
  
         static int destPortInt = 9998;   
  
         static int TTLTime = 4;   
  
         public static void main(String[] args) throws Exception {   
  
                      
  
                   InetAddress destAddress = InetAddress.getByName(destAddressStr);   
  
                   if(!destAddress.isMulticastAddress()){//���õ�ַ�Ƿ��Ƕಥ��ַ   
  
                            throw new Exception("��ַ���Ƕಥ��ַ");   
  
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

