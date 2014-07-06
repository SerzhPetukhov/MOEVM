import java.net.*;
import java.io.*;

public class NetworkClient 
{
	protected String host;
	protected int port;
	protected String username;
	
	public NetworkClient(String host, int port)
	{
		username = "Guest";
		this.host = host;
		this.port = port;
	}
	
	public void connect()
	{
		try
		{
			Socket client = new Socket(host, port);
			
			handleConnection(client);
		}
		catch(UnknownHostException uhe) {}
		catch(IOException ioe) {}
	}
	public void handleConnection(Socket client)
	{
		System.out.print("Type in you nickname: ");
		
		try 
		{
			String name = "";
			BufferedReader br = IOUtil.getReaderByConsole();
			
			name = br.readLine();
			setUsername(name);
		} 
		catch (IOException e) { }
		
		new Reader(client);
		new Writer(client, username);
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getHost()
	{
		return host;
	}
	public int getPort()
	{
		return port;
	}
}
////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
class Reader implements Runnable
{
	private Connection connectionThread;
	
	public Reader(Socket client)
	{
		connectionThread = new Connection(this, client);
		
		connectionThread.start();
	}
	
	public void run()
	{
		Connection currentThread = (Connection)Thread.currentThread();
		String msg = "";
		
		try
		{
			Socket client = currentThread.getSocket();
			BufferedReader in = IOUtil.getReaderBySocket(client);
		
			do
			{			    
				msg = in.readLine();
			
				System.out.println(msg);
				
			} while(!msg.equals("stop"));
		}
		catch(IOException ioe)
		{ }
	}
}

class Writer implements Runnable
{
	private Connection connectionThread;
	private String from;
	
	public Writer(Socket client, String from)
	{
		this.from = from;
		
		connectionThread = new Connection(this, client);
		
		connectionThread.start();
	}
	
	public void run()
	{
		Connection currentThread = (Connection)Thread.currentThread();
		String msg = "";
		
		try
		{
			Socket client = currentThread.getSocket();
			PrintWriter pw = IOUtil.getWriterBySocket(client);
			BufferedReader br = IOUtil.getReaderByConsole();
		
			do
			{
				msg = br.readLine();
				
				pw.println(from + '>' + msg);
				
			} while(!msg.equals("stop"));
		}
		catch(IOException ioe)
		{ }
	}
}

class Connection extends Thread
{
	private Socket clientSocket;
	
	public Connection(Runnable clientObject, Socket clientSocket)
	{
		super(clientObject);
	
		this.clientSocket = clientSocket;
	}
	
	public Socket getSocket()
	{
		return clientSocket;
	}
}























