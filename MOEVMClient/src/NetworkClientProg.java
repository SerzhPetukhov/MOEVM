
public class NetworkClientProg
{
	public static void main(String[] args) 
	{		
		NetworkClient nwClient = new NetworkClient("localhost", 8090);
	
		nwClient.connect();
	}
}
