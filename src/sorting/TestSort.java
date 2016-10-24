package sorting;

import java.util.Arrays;

public class TestSort {
	public static void main(String[] args) {
		int a[] = {25,34,15,25,14,35,98,17,37,84,45,94,65,2,98,95,15,14,33,35,84};
		int b[] = Arrays.copyOf(a, a.length);
		int c[] = Arrays.copyOf(a, a.length);
		int d[] = Arrays.copyOf(a, a.length);
		int e[] = Arrays.copyOf(a, a.length);
		
		System.out.println(Arrays.toString(a));
		
		QuickSort.quickSort(a, 0, a.length-1);
		SelectionSort.selectionSort(b);
		SelectionSort.betterSelectionSort(c);
		InsertionSort.insertionSort(d);
		ShellSort.shellSort(e);
		
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		System.out.println(Arrays.toString(c));
		System.out.println(Arrays.toString(d));
		System.out.println(Arrays.toString(e));
		
	}
}
