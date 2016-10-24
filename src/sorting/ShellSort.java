package sorting;

import java.util.Arrays;


public class ShellSort {
	
	public static boolean showDetails = false;

	public static void shellSort(int array[]) {
		int n = array.length;
		int h = 1;
		while(h < n / 3) {
			h = h * 3 + 1;
		}
		while(h >= 1) {
			if(showDetails) {
				System.out.println("h : " + h);
			}
			
			for(int i = h; i < n; i++) {
				int value = array[i];
				int j = i - h;
				for(; j >= 0; j = j-h) {
					if(value < array[j]) {
						array[j + h] = array[j];
					}else{
						break;
					}
				}
				array[j + h] = value;
				
				if(showDetails) {
					System.out.println(i + " : " + Arrays.toString(array));
				}
			}
			h = h / 3;
		}
	}
}
