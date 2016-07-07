package com.ljheee.udpchat.server;

import java.io.IOException;
import java.net.*;

public class UDPserver {
	
	DatagramSocket ds = null; // ���Ӷ���
	DatagramPacket sendDp; // �������ݰ�����
	DatagramPacket receiveDp; // �������ݰ�����
	final int PORT = 10011; // �˿�

	
	public UDPserver() throws IOException {
		
		try {// �������ӣ������˿�
		    ds = new DatagramSocket(PORT);
		    System.out.println("����������������");
		    // ��ʼ����������
		    byte[] b = new byte[1024];
		    receiveDp = new DatagramPacket(b, b.length);
				
		    ds.receive(receiveDp);// ����
		    // ��ȡ�������ݣ������
		    byte[] data = receiveDp.getData();
		    int len = receiveDp.getLength();
		    System.out.println("�ͻ��˷������ݣ�" + new String(data, 0, len));
		 // ���ͷ���
		    InetAddress clientIP = receiveDp.getAddress();
		    int clientPort = receiveDp.getPort();
		    String response = "OK";
		    byte[] bData = response.getBytes();
		    sendDp = new DatagramPacket(bData, bData.length, clientIP,clientPort);
		    // ����
		    ds.send(sendDp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {try {ds.close();} catch (Exception e) {}
		}

	}
	public static void main(String[] args) {
		try {
			new UDPserver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
