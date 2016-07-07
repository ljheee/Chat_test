package com.ljheee.udpchat.server;

import java.io.IOException;
import java.net.*;

public class UDPserver {
	
	DatagramSocket ds = null; // 连接对象
	DatagramPacket sendDp; // 发送数据包对象
	DatagramPacket receiveDp; // 接收数据包对象
	final int PORT = 10011; // 端口

	
	public UDPserver() throws IOException {
		
		try {// 建立连接，监听端口
		    ds = new DatagramSocket(PORT);
		    System.out.println("服务器端已启动：");
		    // 初始化接收数据
		    byte[] b = new byte[1024];
		    receiveDp = new DatagramPacket(b, b.length);
				
		    ds.receive(receiveDp);// 接收
		    // 读取反馈内容，并输出
		    byte[] data = receiveDp.getData();
		    int len = receiveDp.getLength();
		    System.out.println("客户端发送内容：" + new String(data, 0, len));
		 // 发送反馈
		    InetAddress clientIP = receiveDp.getAddress();
		    int clientPort = receiveDp.getPort();
		    String response = "OK";
		    byte[] bData = response.getBytes();
		    sendDp = new DatagramPacket(bData, bData.length, clientIP,clientPort);
		    // 发送
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
