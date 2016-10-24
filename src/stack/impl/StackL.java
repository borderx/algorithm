package stack.impl;

import stack.Stack;

public class StackL<T> implements Stack<T>{
	
	class Node {
		
		private T data;
		private Node next;
		
		public Node() {
		}

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + next.data + "]";
		}
		
	}

	private Node first;
	private int number;
	
	public StackL() {
		this.first = null;
		this.number = 0;
	}

	@Override
	public void push(T t) {
		Node oldFirst = first;
	    first = new Node();
	    first.data = t;
	    first.next = oldFirst;
	    number++;
	}
	
	@Override
	public T pop() {
		if(first == null) {
			return null;
		}
		T data = first.getData();
		first = first.next;
		number--;
		return data;
	}
	
	@Override
	public boolean isEmpty() {
		return number == 0;
	}
	
	@Override
	public int size() {
		return number;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("StackL [size=" + size() + ",data=");
		Node node = first;
		while(node != null) {
			builder.append(node.data);
			if(node.next != null) {
				builder.append(",");
			}
			node = node.next;
		}
		builder.append("]");
		return builder.toString();
	}
	
}
