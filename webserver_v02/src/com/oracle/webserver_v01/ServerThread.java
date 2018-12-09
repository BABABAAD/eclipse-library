package com.oracle.webserver_v01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
/*
 * 服务多个客户
 */
public class ServerThread extends Thread{
private Socket baoxiang;

public ServerThread(Socket baoxiang) {
	this.baoxiang = baoxiang;
}
public void run() {
	PrintWriter pw=null;
	BufferedReader br = null;
	try {
		//获得流
		br = new BufferedReader(new InputStreamReader(baoxiang.getInputStream()));
		pw=new PrintWriter(baoxiang.getOutputStream());
		//包厢里服务员和顾客
		/**
		 * 读取请求信
		 */
		//请求行
		String requestline = br.readLine();
		System.out.println(requestline);
		//拆分请求行
		String [] strmethod=requestline.split(" ");
		System.out.println("请求方法："+strmethod[0]);
		System.out.println("请求URL："+strmethod[1]);
		System.out.println("请求版本号："+strmethod[2]);
		//若干请求行
		String line = "";
		Map<String,String> requestmap=new HashMap<>();
		//读取到空白行输出方法体中的，比较内容用equals   =比较的是地址
		while(!(line=br.readLine()).equals("")) {
			System.out.println(line);
			//查分若干请求头
			requestmap.put(line.split(":")[0], line.split(":")[1].trim());
		}
		System.out.println(requestmap);
		//空白行
		System.out.println();
		/**
		 *	响应
		 */
		//响应行
		pw.println("HTTP/1.1 200 ok");
		pw.println("Content-Type:text/html;charset=utf-8");
		//若干响应头
		
		//空行
		pw.println();
		//正文
		pw.println("<title>我的第一个WEB界面</title>");
		pw.print("<b style='color :red;'>你知道的太多了</b>");
		pw.print("<h1>干啥<h1>");
		//强制发出
		pw.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		if(br!=null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(pw!=null) {
			pw.close();
		}
	}
	}
}
