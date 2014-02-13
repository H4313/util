package com.h4313.deephouse.network;

import java.io.IOException;
import java.util.ArrayList;

import com.h4313.deephouse.network.CallBack;

public class _TcpSendExample implements CallBack
{
	private ArrayList<String> messages;
	
	public _TcpSendExample(String ip, int port)
	{
		messages = new ArrayList<String>();
		
		// Test
		messages.add("A55A6B0550000000FF9F1E0530B1"); // on
		messages.add("A55A6B0570000000FF9F1E0530D3"); // off
		
		TcpSender tcpSender = null;
		try {
			tcpSender = new TcpSender(ip, port, this);
			tcpSender.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Termination
//		try {
//			tcpSender.closeSender();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void submitMessage(String s)
	{
		messages.add(s);
	}
	
	public String callBack(String s)
	{
 		String value = null;
		
		if(messages.size() > 0)
		{
			// Takes 1st message
			value = messages.get(0);
			messages.remove(0);
		}
		
		return value;
	}
}
