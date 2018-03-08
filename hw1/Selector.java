//Devin Dang

import java.util.*;

public class Selector {
	
	private static void swap(Word[] array, int i, int j){
		if(i == j) return;
		
		Word temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static Word[] select(Word[] array, int k){
	// Problem #1
	// Fill in this method with an O(n*k) time algorithm
	// that returns the k largest elements of array in
	// order from largest to smallest.
	// Note: This should return an array with k elements.

		//Selection Sort Implementation
		for (int i = 0; i < array.length - 1; i++)
		{
			int pos = i;
			for (int j = i + 1; j < array.length; j++)
			{
				if(array[j].compareTo(array[pos]) >= 0)
				{
					pos = j;
				}
			}

			swap(array, i, pos);
		}


		return Arrays.copyOfRange(array, 0, k);
	}
}
