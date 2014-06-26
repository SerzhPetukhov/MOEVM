
public class LinkListProgram 
{
	public static void main(String[] args) 
	{
		LinkList linkList = new LinkList();
		
		for (int i = 0; i < 26; ++i)
			linkList.insertFirst((char)(65 + i));

		linkList.displayList();
		
		while(!linkList.isEmpty())
		{
			linkList.deleteFirst();			
		}
		
		linkList.displayList();
	}
}
