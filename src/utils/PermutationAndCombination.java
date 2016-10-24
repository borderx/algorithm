package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationAndCombination {
	
	/**
	 * 组合
	 * @param input
	 * @param n
	 * @return
	 */
	public static <T> List<T[]> combine(T[] input, int n) {
		List<T[]> res = new ArrayList<>();
		if(input == null || input.length == 0 || n <= 0 || n > input.length) {
			return res;
		}
		Object[] queue = new Object[n];
		combi2(input, res, queue, 0, 0, n);
		return res;
	}
	
	/**
	 * 组合递归方法1
	 * @param input
	 * @param res
	 * @param queue
	 * @param count
	 * @param position
	 * @param n
	 */
	@SuppressWarnings("unchecked")
	private static <T> void combi(T[] input, List<T[]> res, Object[] queue, int count, int position, int n){
		for(int i=0;i<count;i++){
			System.out.print("  ");
		}
		System.out.println("combi(" + count + ", " + position + ")");
		
		if(count == n) {
			res.add((T[])Arrays.copyOf(queue, queue.length));
			return;
		}
		if(position >= input.length) {
			return;
		}
		queue[count++] = input[position];
		combi(input, res, queue, count, position + 1, n);
		count--;
		combi(input, res, queue, count, position + 1, n);
	}
	
	/**
	 * 组合递归方法2
	 * @param input
	 * @param res
	 * @param queue
	 * @param count
	 * @param position
	 * @param n
	 */
	@SuppressWarnings("unchecked")
	private static <T> void combi2(T[] input, List<T[]> res, Object[] queue, int count, int position, int n){
		if(count == n) {
			res.add((T[])Arrays.copyOf(queue, queue.length));
			return;
		}
		for(int i = position; i < input.length; i ++) {
			if((input.length - i) < (n - count)){
				break;
			}
			queue[count] = input[i];
			combi2(input, res, queue, count + 1, i + 1, n);
		}
	}
	
	/**
	 * 排列
	 * 从input中取出n个元素的所有解的集合
	 * @param input 输入的数组
	 * @param n  要取得元素的个数
	 * @return
	 */
	public static <T> List<T[]> permutate(T[] input, int n) {
		List<T[]> res = new ArrayList<>();
		if(input == null || input.length == 0 || n <= 0 || n > input.length) {
			return res;
		}
		boolean[] flags = new boolean[input.length];
		Object[] queue = new Object[n];
		perm(input, res, queue, flags, 0, n);
		return res;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> void perm(T[] input, List<T[]> res, Object[] queue, boolean[] flags, int position, int n){
		if(position == n) {
			res.add((T[])Arrays.copyOf(queue, queue.length));
			return;
		}
		for(int i = 0; i < input.length; i ++) {
			if(!flags[i]) {
				flags[i] = true;
				queue[position] = input[i];
				perm(input, res, queue, flags, position + 1, n);
				flags[i] = false;
			}
		}
	}
	
}
