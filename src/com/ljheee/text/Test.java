package com.ljheee.text;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test {

	public static void main(String[] args) {
		// �㲥��ʵ�� :�ɿͻ��˷����㲥���������˽���
		String host = "255.255.255.255";//�㲥��ַ
		int port = 9999;//�㲥��Ŀ�Ķ˿�
		
		String message = "test";//���ڷ��͵��ַ���
		try {
			InetAddress adds = InetAddress.getByName(host);
			DatagramSocket ds = new DatagramSocket();
			DatagramPacket dp = new DatagramPacket(message.getBytes(),
					message.length(), adds, port);
			ds.send(dp);
			ds.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}