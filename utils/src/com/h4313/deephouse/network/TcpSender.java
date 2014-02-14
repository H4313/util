package com.h4313.deephouse.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

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
	
	public TcpSender(String host, int port, CallBack applicant) throws IOException, InterruptedException
	{	
		this.alive = true;
		this.senderId = TcpSender.lastReceiverId++;
		boolean isConnected = false;
		
		try 
		{			
			do
			{
				try
				{
					this.socket = new Socket(host, port);
					this.socket.setSoTimeout(Constant.TCP_CONNECTION_TIMEOUT);
					isConnected = true;
					System.out.println("Connecte !");
				}
				catch(Exception e)
				{
					System.out.println("La connexion a echoue : " + e.getMessage());
					isConnected = false;
					Thread.sleep(Constant.TCP_CONNECTION_TIMEOUT);
				}
			} while(!isConnected || this.socket == null || !this.socket.isConnected());
			
			this.out = this.socket.getOutputStream();
			
			this.applicant = applicant;
			
			System.out.println("Sender connecte !");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
//			throw new IOException("Erreur a la connexion avec le client");
		}		
	}
	
	@Override
	public void run()
	{
		try
		{
			String s = null;
			boolean canSend = true;
			while(alive && canSend)
			{
				s = applicant.callBack(null);
				
				if(s != null && !s.isEmpty())
				{
					System.out.println("Message envoye : " + s);
					canSend = send(s);
				}
				
				 Thread.sleep(1000); // Wait X milliseconds
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
	public boolean send(String message) throws Exception
	{
		if(socket.isConnected() && message != null)
		{
			try
			{
				byte[] byteswrite = new byte[Constant.TCP_FRAME_LENGTH];
				byteswrite = message.getBytes();
				if(socket.isConnected()) out.write(byteswrite);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new Exception("Impossible d'envoyer un message");
			}
			return true;
		}
		else return false;
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