package stack.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import stack.Stack;
import stack.impl.StackA;
import stack.impl.StackL;

public class StackLTest {
	@Test
	public void testStackL(){
		StackL<Integer> stackL = new StackL<Integer>();
		stackL.push(20);
		stackL.push(30);
		System.out.println(stackL.toString());
		
		System.out.println(stackL.pop());
//		System.out.println(stackL.pop());
		
		stackL.push(600);
		stackL.push(500);
		System.out.println(stackL.toString());
		System.out.println(stackL.pop());
		
	}
	
	@Test
	public void testStackA(){
		Stack<Integer> stackA = new StackA<Integer>();
		stackA.push(20);
		stackA.push(30);
		stackA.push(31);
		stackA.push(32);
		stackA.push(33);
		stackA.push(34);
		stackA.push(35);
		stackA.push(36);
		stackA.push(37);
		stackA.push(38);
		System.out.println(stackA.toString());
		
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		System.out.println(stackA.toString());
		
		stackA.push(600);
		stackA.push(500);
		System.out.println(stackA.toString());
		System.out.println(stackA.pop());
		
	}
	
	@Test
	public void testArray() {
		int[] array1 = new int[]{1,2,3,4,5,6};
		int[] array2 = Arrays.copyOf(array1, 12);
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
	}
	
	public static void main(String[] args) {
		// [2, 7, 11, 15], target = 9
		int[] a = twoSum(new int[]{0,4,3,0}, 0);
		System.out.println(Arrays.toString(a));
	}
	
	public static int[] twoSum(int[] nums, int target) {

		int count = nums.length;
		for(int i=0; i<count; i++) {
			for(int j=i+1; j<count; j++) {
				if(nums[i] + nums[j] == target) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}
	
	
}
