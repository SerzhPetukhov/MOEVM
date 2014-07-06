import java.net.*;
import java.io.*;

public class IOUtil 
{
	public static BufferedReader getReaderBySocket(Socket s) throws IOException
	{
		return (new BufferedReader(new InputStreamReader(s.getInputStream())));
	}
	
	/* Создание PrintWriter для передачи выходных данных. При выполнении метода println объект PrintWriter
	 * автоматически передает данные, содержащиеся в буфере. */
	
	public static PrintWriter getWriterBySocket(Socket s) throws IOException
	{
		// True второго параметра задает автоматическую передачу содержимого в буфер
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
