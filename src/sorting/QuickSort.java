package sorting;

public class QuickSort {
	
	public static void quickSort(int n[], int left, int right) {
		int dp = 0;
		if(left < right){
			dp = partition(n, left, right);
			quickSort(n, left ,dp-1);
			quickSort(n, dp+1, right);
		}
	}

	private static int partition(int n[], int left, int right) {
		int key = n[left];
		while(left < right){
			while(left < right && n[right] >= key){
				right--;
			}
			if(left < right){
				n[left++] = n[right];
			}
			while(left < right && n[left] <= key){
				left++;
			}
			if(left < right){
				n[right--] = n[left];
			}
		}
		n[left] = key;
		return left;
	}
	
	
}
