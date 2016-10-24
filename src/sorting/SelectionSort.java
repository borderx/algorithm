package sorting;

import java.util.Arrays;

public class SelectionSort {
	
	public static boolean showDetails = false;
	
	public static void selectionSort(int n[]) {
		for(int i=0; i<n.length; i++) {
			int minIndex = getMinIndex(n, i);
			if(minIndex != i) {
				swap(n, i, minIndex);
			}
			
			if(showDetails) {
				System.out.println((i + 1) + " : " + Arrays.toString(n));
			}
		}
	}
	
	public static void betterSelectionSort(int n[]) {
		int start = 0;
		int end = n.length - 1;
		while(start < end) {
			int[] indexs = getMinAndMaxIndex(n, start, end);
			int minIndex = indexs[0];
			int maxIndex = indexs[1];
			if(minIndex != start) {
				swap(n, start, minIndex);
			}
			if(maxIndex != end) {
				if(maxIndex == start) {
					maxIndex = minIndex;
				}
				swap(n, end, maxIndex);
			}
			start ++;
			end --;
			
			if(showDetails) {
				System.out.println(start + " : " + Arrays.toString(n));
			}
		}
	}
	
	private static int getMinIndex(int n[], int startPosition) {
		int minIndex = startPosition;
		int min = n[minIndex];
		for(int i=startPosition + 1; i<n.length; i++) {
			if (n[i]<min) {
				min = n[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	private static int[] getMinAndMaxIndex(int n[], int startPosition, int endPosition) {
		int minIndex = startPosition;
		int maxIndex = startPosition;
		int min = n[minIndex];
		int max = n[maxIndex];
		for(int i=startPosition + 1; i<=endPosition; i++) {
			if (n[i] < min) {
				min = n[i];
				minIndex = i;
			}
			if (n[i] >= max) {
				max = n[i];
				maxIndex = i;
			}
		}
		return new int[]{minIndex, maxIndex};
	}
	
	private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
	
}
