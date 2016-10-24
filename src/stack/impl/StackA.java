package stack.impl;

import java.util.Arrays;

import stack.Stack;

public class StackA<T> implements Stack<T> {

	private Object[] data;
	private int size;
	
	public StackA() {
		this.data = new Object[8];
		this.size = 0;
	}
	
	public StackA(int size) {
		this.data = new Object[size];
		this.size = 0;
	}

	@Override
	public void push(T t) {
		if(this.size == data.length) {
			resize(2 * data.length);
		}
		data[size++] = t;
	}

	private void resize(int capacity) {
		this.data = Arrays.copyOf(this.data, capacity);
	}

	@Override
	public T pop() {
		if(size<=0) {
			return null;
		}		
		@SuppressWarnings("unchecked")
		T temp = (T)data[--size];
	    data[size] = null;
	    if (size > 0 && size == data.length / 4) {
	    	resize(data.length / 2);
	    }
	    return temp;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("StackL [size=" + size() + ",data=");
		builder.append(Arrays.toString(data));
		builder.append("]");
		return builder.toString();
	}

}
