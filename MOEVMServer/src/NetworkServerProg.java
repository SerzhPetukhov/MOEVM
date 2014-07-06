import java.net.*;
import java.io.*;

public class NetworkServerProg extends NetworkServer implements Runnable 
{
	public static void main(String[] args) 
	{
		int port = 8090;
		
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		
		new NetworkServerProg(port, 0);
	}
	
	public NetworkServerProg(int port, int maxConnections)
	{
		super(port, maxConnections);
	}
	
	public void handleConnection(Socket server)
	{
		/* Новый вариант метода handleConnection, при выполнении которого запускается поток. В созданном потоке вызывается старая
		 * версия handleConnection, выполняющая те же действия, что и в однопотоковом варианте программы. В классе потока сохраняется объект
		 * Socket. Сделать это необходимо, так как методу run нельзя передавать параметры. Хранить объект Socket в переменной экземпляра
		 * также нельзя, поскольку при запуске следующего потока значение переменной может быть изменено. 	*/

		Connection connectionThread = new Connection(this, server);
		
		connectionThread.start();
	}
	public void run()
	{
		Connection currentThread = (Connection) Thread.currentThread();
		
		try
		{
			super.handleConnection(currentThread.getSocket());
		}
		catch(IOException ioe) { }
	}
}
///////////////////////////////////////////////////////////////////////////////
class Connection extends Thread
{
	private Socket serverSocket;
	
	public Connection(Runnable serverObject, Socket serverSocket)
	{
		super(serverObject);
	
		this.serverSocket = serverSocket;
	}
	
	public Socket getSocket()
	{
		return serverSocket;
	}
}