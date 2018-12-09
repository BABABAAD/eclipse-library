package com.oracle.webserver_v01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocket {
	/*
	 * 首先发送一个消息给服务器
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		// 开个饭店
		try {
			// 迎宾
			serverSocket = new ServerSocket(8080);
			while (true) {
				// 等顾客来,如果有顾客来了交给包厢服务器服务员交流
				Socket baoxiang = serverSocket.accept();
				// 由于迎宾知道是否有顾客来，因而由它启动
				new ServerThread(baoxiang).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
