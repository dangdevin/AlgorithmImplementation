//Devin Dang


import java.util.*; 

public class Heap {


	Word[] heap;
	int numItems = 0;
	
	public Heap(Word[] array){
		buildHeap(array);
	}
	
	public void buildHeap(Word[] array){
	// Problem #2
	// Fill in this method with an O(n) time algorithm
	// that builds an n element complete binary heap.
	// Note: You are allowed to add and modify fields
    // and helper methods.
		for(int i = parent(array.length); i >= 0; i--)
		{
			insert(array, i, 0);
		}
		//System.out.println(Arrays.toString(array));

		setHeap(array);
	}

	//Set methods
	public void setHeap(Word[] array)
	{
		heap = array;
	}

	public void setNumItems(int num)
	{
		numItems = num;
	}

	//Get methods
	public Word[] getHeap()
	{
		return heap;
	}

	public int getNumItems()
	{
		return numItems;
	}


	//Helper Methods for buildHeap

	void insert(Word[] array, int i, int j)
	{
		int max = j;

		setNumItems(numItems+1);

		if(leftChild(j) < i && array[leftChild(j)].compareTo(array[max]) > 0)
		{
			max = leftChild(j);
		}

		if(rightChild(j) < i && array[rightChild(j)].compareTo(array[max]) > 0)
		{
			max = rightChild(j);
		}

		if(max != j)
		{
			Word temp = array[j];
			array[j] = array[max];
			array[max] = temp;
			insert(array, i, max);
		}
	}

	private int parent(int i)
	{
		return (i-1)/2;
	}

	private int leftChild(int i)
	{
		return (2*i)+1;
	}

	private int rightChild(int i)
	{
		return (2*i)+2;
	}
	
	public Word removeMax(){
	// Problem #3
	// Fill in this method with an O(log(n)) time algorithm
	// that removes the root element, restores the heap
	// structure, and finally returns the removed root element.
		
		Word[] array = getHeap();

		Word result = array[0];
		heap[0] = heap[numItems--];
		sink(0);

		return result;

	}
	
	public Word[] select(int k){
		Word[] result = new Word[k];
		for(int i = 0; i < k; i++){
			result[i] = this.removeMax();
		}
		return result;
	}

	//Helper methods for select
	public void sink(int i)
	{
		if(i >= numItems)
		{
			return;
		}
		else if((heap[rightChild(i)].compareTo(heap[i]) < 0) && heap[leftChild(i)].compareTo(heap[i]) < 0)
		{
			return;
		}
		else
		{
			if(heap[rightChild(i)].compareTo(heap[leftChild(i)]) > 0)
			{
				//Right swap
				swap(heap, i, rightChild(i));
				sink(rightChild(i));
			}
			else
			{
				//Left swap
				swap(heap, i, leftChild(i));
				sink(leftChild(i));
			}
			
		}
	}

	//Swap method from Selector.java
	private static void swap(Word[] array, int i, int j){
		if(i == j) return;
		
		Word temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
