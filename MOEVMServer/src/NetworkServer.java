import java.net.*;
import java.io.*;
import java.lang.*;

public class NetworkServer 
{
	private int port;
	private int maxConnections;
	
	public NetworkServer(int port, int maxConnections)
	{		
		setPort(port);
		setMaxConnections(maxConnections);
		
		listen();
	}
	
	public void listen()
	{
		int i = 0;
		
		try
		{
			ServerSocket listener = new ServerSocket(port);
			Socket server;
			
			while(i++ < maxConnections || maxConnections == 0)
			{
				server = listener.accept();
				
				handleConnection(server);
			}	
		}
		catch(IOException ioe) { }		
	}
	protected void handleConnection(Socket server) throws IOException
 	{
		new Writer(server);
		new Reader(server);
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
	public void setMaxConnections(int maxConnections)
	{
		this.maxConnections = maxConnections;
	}
	public int getPort()
	{
		return port;
	}
	public int getMaxConnections()
	{
		return maxConnections;
	}
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
class Reader implements Runnable
{
	private Connection connectionThread;
	private FileOutputStream fos;
	
	public Reader(Socket server)
	{
		connectionThread = new Connection(this, server);
		
		connectionThread.start();
	}
	
	public void run()
	{
		Connection currentThread = (Connection)Thread.currentThread();
		Socket server = currentThread.getSocket();
		String msg = "";
		
		try 
		{			
			fos = new FileOutputStream("database.txt", true);
			BufferedReader br = IOUtil.getReaderBySocket(server);
			
			do
			{				
				msg = br.readLine();									// ÏÐÎ×ÈÒÀËÈ ÑÎÎÁÙÅÍÈÅ ÍÀ ÂÕÎÄ
				msg += System.getProperty("line.separator");
				
				for (int i = 0; i < msg.length(); ++i)					// ÄÎÏÎËÍÈËÈ ËÎÃ Â ÔÀÉËÅ ÍÎÂÛÌ ÑÎÎÁÙÅÍÈÅÌ
					fos.write(msg.charAt(i));
				
			} while(!msg.equals("stop"));
			
			fos.close();
		}
		catch(IOException ioe) { }
	}
}

class Writer implements Runnable
{
	private Connection connectionThread;
	private FileInputStream fin;
	
	public Writer(Socket server)
	{
		try 
		{
			fin = new FileInputStream("database.txt");
		} 
		catch (FileNotFoundException e) { }
		catch (ArrayIndexOutOfBoundsException e) { }
		
		connectionThread = new Connection(this, server);
		
		connectionThread.start();
	}
	
	public void run()
	{
		Connection currentThread = (Connection)Thread.currentThread();
		Socket server = currentThread.getSocket();
		String currFileContent = "", prevFileContent = "";
		PrintWriter pw;
	
		try 
		{
			pw = IOUtil.getWriterBySocket(server);
			
			do
			{
				try 
				{
					Thread.sleep(2000);
				} catch (InterruptedException e) { }
				
				int j = 0;
						
				do
				{
					j = fin.read();
							
					if (j != -1)
						currFileContent += ((char) j);
				} while(j != -1);
					
				if (!currFileContent.equals(prevFileContent))
					pw.println(currFileContent);			
				
				prevFileContent = currFileContent;
			} while(!currFileContent.equals("stop"));
			
			fin.close();
		}
		catch(IOException ioe) { }	
	}
}















