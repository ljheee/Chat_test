package com.ljheee.udpchat.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPclient {
	
	
	DatagramSocket ds = null; // ���Ӷ���
	DatagramPacket sendDp; // �������ݰ�����
	DatagramPacket receiveDp; // �������ݰ�����
	String serverHost = "127.0.0.1"; // ������IP
	int serverPort = 10011; // �������˿ں�
	
	public UDPclient() throws IOException {

		try {
		    ds = new DatagramSocket(); // ��������
		   
		    Date d = new Date(); // ��ǰʱ��
		    String content = d.toString(); // ת��Ϊ�ַ���
		    byte[] data = content.getBytes();
		   
	      // ��ʼ�����Ͱ�����
		    InetAddress address = InetAddress.getByName(serverHost);
		    sendDp = new DatagramPacket(data, data.length, address, serverPort);
		    ds.send(sendDp); // ����
		 // ��ʼ����������
		    byte[] b = new byte[1024];
		    receiveDp = new DatagramPacket(b, b.length);
		    ds.receive(receiveDp); // ���գ��˴��̻߳�ȴ�

	      // ��ȡ�������ݣ������
		    byte[] response = receiveDp.getData();
		    int len = receiveDp.getLength();
		    String s = new String(response, 0, len);
		    System.out.println("�������˷���Ϊ��" + s);
		} catch (Exception e) {
			     e.printStackTrace();
		} finally {
		          try {// �ر�����
				ds.close();
			       } catch (Exception e) {}
		}
		   
		
	}
	
	public static void main(String[] args) {
		try {
			new UDPclient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
