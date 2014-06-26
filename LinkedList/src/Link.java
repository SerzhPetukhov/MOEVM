class Link 
{
	public char character;
	public Link next;
	
	public Link(char character)
	{
		this.character = character;		
	}
	
	public void displayLink()
	{
		System.out.print(character + " ");
	}
} 

class LinkList
{
	private Link first;
	
	public LinkList()
	{
		this.first = null;		
	}
	
	public boolean isEmpty()
	{
		return (first == null);
	}
	public void insertFirst(char character)
	{
		Link newLink = new Link(character);
		
		newLink.next = first;
		first = newLink;
	}
	public Link deleteFirst()
	{
		Link temp = first;
		
		first = first.next;
		
		return temp;
	}
	public void displayList()
	{
		System.out.println("\nLinkedList: ");
		
		Link temp = first;
		
		while(temp != null)
		{
			temp.displayLink();
			
			temp = temp.next;
		}
		
		System.out.println();
	}
}