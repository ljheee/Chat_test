package com.ljheee.test3;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.*;

public class TestServer {
	
	private  JTextArea jta = null;
	private JTextField jtf = null;
	private JPanel jPanel = null;
	private JFrame jf;
	
	MulticastSocket ms = null;
	
	//����ͻ���UI����
	public void initGUI(){
		
		jf = new JFrame("�ͻ���Client");
		jf.setSize(400,400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		
		jta = new JTextArea();
		jta.setEditable(false);
		
		jf.add(new JScrollPane(jta));
		
		jPanel = new JPanel();
		jtf = new JTextField(26);
		jf.add(jPanel,BorderLayout.SOUTH);
		jPanel.add(new JLabel("��Ҫ���ԣ�"));
		jPanel.add(jtf);
		
		jtf.addActionListener(new ActionListener() {//
			DatagramPacket dps = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = jtf.getText();
				try {
					dps = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("224.255.0.1"), 9998);
					ms.send(dps);
					jtf.setText("");
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//�ͻ����˳�
//		jf.addWindowListener(new WindowAdapter() {
//
//			@Override
//			public void windowClosed(WindowEvent e) {
//				try {
//					s.close();	//��ǰ�ͻ��˹ر�ʱ----�رյ�ǰ�û�socket���Ҵӷ������б������
//					Server.sockets.remove(s);
//				//	System.exit(0);
//				} catch (IOException e1) {
//				}
//			}
//			
//		});
		
		jf.setVisible(true);
	}


	private  void initNet() {
		//�����鲥�ͷ����鲥�����ݱ�����Ҫ���鲥��ַ��ӽ���
		String host = "224.255.0.1";//�ಥ��ַ
		int port = 9998;
		int length = 1024;
		byte[] buf = new byte[length];
		
		DatagramPacket dp = null;
		byte[] data = null;
		
		try {			
			ms = new MulticastSocket(port);
			dp = new DatagramPacket(buf, length);
			
			//����ಥ��ַ
			InetAddress group = InetAddress.getByName(host);
			ms.joinGroup(group);
			System.out.println("�����鲥�飬׼�����ա�����");
			
			while(true){
				try {
					ms.receive(dp);
//					data = dp.getData();
					
					jta.append(dp.getAddress()+"˵:  "+new String(buf));
					jta.append("\n");
					
//					System.out.println("�յ��ಥ��Ϣ��" + new String(data));
				} catch (Exception e) {
					System.out.println("���ճ���"+new Date());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TestServer() {
		initGUI();
		initNet();
	}

	public static void main(String[] args){
		new TestServer();
	}

}