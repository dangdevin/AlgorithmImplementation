
public class ChainingHashMap{
	Node[] array;
	int size;
	
	public ChainingHashMap(int size){
		this.size = size;
		array = new Node[size];
	}

	public Integer get(Word key) {
	// Problem #1A
	// Fill in this method to get the value corresponding
	// with the key. Note: if the key is not found, then
	// return null.

		int hash = key.hashCode();		// get hashed key
		hash = hash % this.getSize();

		// return null if key is not found
		if (array[hash] == null)
		{
			return null;
		}
		// if equal, return node's value
		else
		{
			Node temp = array[hash];

			while(temp != null && temp.word != key)
			{
				temp = temp.next;
			}

			if(temp == null)
			{
				return null;
			}
			else
			{
				return temp.frequency;
			}
		}
		

	}

	public void put(Word key, Integer value) {
	// Problem #1B
	// Fill in this method to insert a new key-value pair into
	// the map or update the existing key-value pair if the
	// key is already in the map.

		int hash = key.hashCode();
		hash = hash % this.getSize();

		if(array[hash] == null)
		{
			array[hash] = new Node(key, value, null);
		}
		else
		{
			Node temp = array[hash];

			while(temp.next != null && temp.word != key)
			{
				temp = temp.next;
			}

			if(temp.word == key)
			{
				temp.frequency = value;
			}
			else
			{
				temp.next = new Node(key, value, null);
			}
		}
	}

	public Integer remove(Word key) {
	// Problem #1C
	// Fill in this method to remove a key-value pair from the
	// map and return the corresponding value. If the key is not
	// found, then return null.
		int hash = key.hashCode();		// get hashed key
		hash = hash % this.getSize();

		if(array[hash] != null)		// replace with null
		{
			Node prev = null;
			Node temp = array[hash];

			while(temp.next != null && temp.word != key)
			{
				prev = temp;
				temp = temp.next;
			}

			if(temp.word == key)
			{
				if(prev == null)
				{
					array[hash] = temp.next;
				}
				else
				{
					Integer value = temp.frequency;
					prev.next = temp.next;
					return value;
				}
			}
		}

		return null;				// not found
	}
	
	// This method returns the total size of the underlying array.
	// In other words, it returns the total number of linkedlists.
	public int getSize(){
		return size;
	}
	
	// This method counts how many keys are stored at the given array index.
	// In other words, it computes the size of the linkedlist at the given index.
	public int countCollisions(int index){
		if(index < 0 || index >= size) return -1;
		
		int count = 0;
		Node temp = array[index];
		while(temp != null){
			temp = temp.next;
			count++;
		}
		
		return count;
	}
	
}
