package com.h4313.deephouse.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.h4313.deephouse.network.CallBack;
import com.h4313.deephouse.util.Constant;

public class TcpSender extends Thread
{
	private volatile boolean alive = true;
	private Socket socket;
	private OutputStream out;
//	private String receiverAddress;
//	private Integer receiverPort;
	private static int lastReceiverId = 0;
    private int senderId;
    private CallBack applicant;
	
	public TcpSender(String ip, int port, CallBack applicant) throws IOException
	{	
		this.alive = true;
		this.senderId = TcpSender.lastReceiverId++;
		
		try 
		{
			this.socket = new Socket(ip, port);
			
//			this.receiverAddress = ip;
//			this.receiverPort = port;
			
			this.out = this.socket.getOutputStream();
			
			this.applicant = applicant;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			throw new IOException("Erreur a la connexion avec le client");
		}		
	}
	
	@Override
	public void run()
	{
		try
		{
			String s = null;
			while(alive)
			{
				s = applicant.callBack(null);
				System.out.println("Retour du callback = " + s);
				
				if(s != null)
				{
					send(s);
				}
				
				 Thread.sleep(10000); // Wait X milliseconds
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Erreur avec le TCP");
		}
	}
	
	/**
	* Envoi un message.
	*/
	public void send(String message) throws Exception
	{
		try
		{
			byte[] byteswrite = new byte[Constant.TCP_FRAME_LENGTH];
			byteswrite = message.getBytes();
			out.write(byteswrite);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("Impossible d'envoyer un message");
		}
	}
	
	/**
	* Fermeture du client, Fermeture des connexions.
	*/
	public void closeSender() throws Exception
	{
		try
		{		
			this.alive = false;		
			this.out.close();
			this.socket.close();
		}
		catch(Exception e)
		{
			throw new Exception("Impossible d'arreter le client.");
		}
	}
	
	/**
	 * Getter
	 */
	public int getSenderId()
	{
		return this.senderId;
	}
}