package com.h4313.deephouse.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.h4313.deephouse.network.CallBack;
import com.h4313.deephouse.util.Constant;

public class TcpReceiver extends Thread
{
	private volatile boolean alive;
	private ServerSocket serverSocket;
	private static int lastReceiverId = 0;
    private int receiverId;
    private InputStream in;
    private Socket socket;
    private CallBack applicant;
    
	/**
	 * A l'ecoute sur port.
	 * @param port
	 * @throws IOException
	 */
    public TcpReceiver(int port, CallBack applicant) throws IOException
    {
		try
		{
			this.alive = true;
			this.receiverId = TcpReceiver.lastReceiverId++;
			this.serverSocket = new ServerSocket(port);
			this.applicant = applicant;
				
			System.out.println("A l'ecoute sur le port " + port);
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
			throw new IOException("Impossible d'ouvrir le port " + port);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
    
	@Override
	public void run()
	{
		try
		{
			this.startListening();
			
			String message = null;
			while(alive)
			{
				message = this.receive();
				if(message != null && !message.isEmpty())
				{
					System.out.println("Message recu : " + message);
					this.applicant.callBack(message);
				}
			}
		}
		catch(Exception e)
		{
//			e.printStackTrace();
			System.out.println("Erreur avec le TCP : " + e.getMessage());
			try {
				closeReceiver();
			} catch (Exception e1) {
				System.out.println("Arret impossible : " + e.getMessage());
			}
		}
	}
	
	/**
	* Recoit un message.
	*/
	public String receive() throws Exception
	{
		byte[] buffer = new byte[Constant.TCP_FRAME_LENGTH];
		
		try
		{
			this.in.read(buffer);
			
			// Securite temporaire
			if(Byte.valueOf(buffer[0]).equals(Byte.valueOf("0"))
					&& Byte.valueOf(buffer[1]).equals(Byte.valueOf("0"))
					&& Byte.valueOf(buffer[2]).equals(Byte.valueOf("0"))
					&& Byte.valueOf(buffer[3]).equals(Byte.valueOf("0"))
					&& Byte.valueOf(buffer[4]).equals(Byte.valueOf("0"))
					&& Byte.valueOf(buffer[5]).equals(Byte.valueOf("0")))
				return null;
		}
		catch(Exception e)
		{
//			e.printStackTrace();
			throw new Exception("Impossible de recevoir un message");
		}
		
		return new String(buffer);
	}
    
	/**
	 * Ouvre une socket, un input,
	 * @throws Exception
	 */
	public void startListening() throws Exception
	{
		try
		{
			this.socket = this.serverSocket.accept();
			this.in = this.socket.getInputStream();
		}
		catch (Exception e) 
		{ 
//			e.printStackTrace();
			throw new Exception("Erreur client rejete");
		}
	}

	/**
	* Arret du serveur.
	* Ferme serverSocket.
	*/
	public void closeReceiver() throws Exception
	{
		try
		{	
			this.alive = false;
			this.in.close();
			this.serverSocket.close();
		}
		catch(Exception e)
		{
			throw new Exception("Impossible d'arreter le server.");
		}
	}
	
	/**
	 * Getters
	 */
	public int getReceiverId()
	{
		return this.receiverId;
	}
}


