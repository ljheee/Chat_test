package com.ljheee.text;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TestServer {
	public static void main(String[] args) {
		int port = 9999;//���������Ķ˿�
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		byte[] buf = new byte[1024];//�洢��������Ϣ
		StringBuffer sbuf = new StringBuffer();
		try {
			//�󶨶˿ڵ�
			ds = new DatagramSocket(port);
			dp = new DatagramPacket(buf, buf.length);
			System.out.println("�����㲥�˿ڴ򿪣�");
			ds.receive(dp);
			ds.close();
			int i;
			for(i=0;i<1024;i++){
				if(buf[i] == 0){
					break;
				}
				sbuf.append((char) buf[i]);
			}			
			System.out.println("�յ��㲥��Ϣ��" + sbuf.toString());
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}