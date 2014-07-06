import java.net.*;
import java.io.*;

public class IOUtil 
{
	public static BufferedReader getReaderBySocket(Socket s) throws IOException
	{
		return (new BufferedReader(new InputStreamReader(s.getInputStream())));
	}
	
	/* �������� PrintWriter ��� �������� �������� ������. ��� ���������� ������ println ������ PrintWriter
	 * ������������� �������� ������, ������������ � ������. */
	
	public static PrintWriter getWriterBySocket(Socket s) throws IOException
	{
		// True ������� ��������� ������ �������������� �������� ����������� � �����
		return new PrintWriter(s.getOutputStream(), true);
	}
	public static BufferedReader getReaderByConsole() throws IOException
	{
		return (new BufferedReader(new InputStreamReader(System.in)));
	}
	public static PrintWriter getWriterByConsole() throws IOException
	{
		return (new PrintWriter(new OutputStreamWriter(System.out), true));
		//return (new PrintWriter(System.out, true));
	}
}
