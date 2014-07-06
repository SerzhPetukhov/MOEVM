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
		/* ����� ������� ������ handleConnection, ��� ���������� �������� ����������� �����. � ��������� ������ ���������� ������
		 * ������ handleConnection, ����������� �� �� ��������, ��� � � ������������� �������� ���������. � ������ ������ ����������� ������
		 * Socket. ������� ��� ����������, ��� ��� ������ run ������ ���������� ���������. ������� ������ Socket � ���������� ����������
		 * ����� ������, ��������� ��� ������� ���������� ������ �������� ���������� ����� ���� ��������. 	*/

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