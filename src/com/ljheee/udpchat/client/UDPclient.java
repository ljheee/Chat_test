package com.ljheee.udpchat.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPclient {
	
	
	DatagramSocket ds = null; // 连接对象
	DatagramPacket sendDp; // 发送数据包对象
	DatagramPacket receiveDp; // 接收数据包对象
	String serverHost = "127.0.0.1"; // 服务器IP
	int serverPort = 10011; // 服务器端口号
	
	public UDPclient() throws IOException {

		try {
		    ds = new DatagramSocket(); // 建立连接
		   
		    Date d = new Date(); // 当前时间
		    String content = d.toString(); // 转换为字符串
		    byte[] data = content.getBytes();
		   
	      // 初始化发送包对象
		    InetAddress address = InetAddress.getByName(serverHost);
		    sendDp = new DatagramPacket(data, data.length, address, serverPort);
		    ds.send(sendDp); // 发送
		 // 初始化接收数据
		    byte[] b = new byte[1024];
		    receiveDp = new DatagramPacket(b, b.length);
		    ds.receive(receiveDp); // 接收，此处线程会等待

	      // 读取反馈内容，并输出
		    byte[] response = receiveDp.getData();
		    int len = receiveDp.getLength();
		    String s = new String(response, 0, len);
		    System.out.println("服务器端反馈为：" + s);
		} catch (Exception e) {
			     e.printStackTrace();
		} finally {
		          try {// 关闭连接
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
