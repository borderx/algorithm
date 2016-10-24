package sorting;

import java.util.Arrays;

public class InsertionSort {
	public static boolean showDetails = false;
	
	public static void insertionSort(int[] array) {
		for(int i=1; i<array.length; i++) {
			int value = array[i];
			int j = i-1;
			for(; j>=0; j--) {
				if(value < array[j]) {
					array[j+1] = array[j];
				}else{
					break;
				}
			}
			array[j+1] = value;
			
			if(showDetails) {
				System.out.println(i + " : " + Arrays.toString(array));
			}
		}
	}
	
}
