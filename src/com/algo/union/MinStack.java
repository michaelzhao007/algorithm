package com.algo.union;

import java.util.Stack;

public class MinStack {
	private Stack<Integer> actual;
	private Stack<Integer> minStack;
	
	public MinStack() {
		actual = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}
	
	public void push(int elem) {
		actual.push(elem);
		if(minStack.isEmpty()){
			minStack.push(elem);}
		else{
			int val =  minStack.pop();
			minStack.push(val);
			if(val > elem) minStack.push(elem);
			else minStack.push(val);
		}
	}
	
	public int pop() {
		int result = actual.pop();
		minStack.pop();
		return result;
	}
	
	public int getMin() {
		int result  = minStack.pop();
		minStack.push(result);
		return result;
	}
	
	public static void main(String[] args) {
		MinStack mi = new MinStack();
		mi.push(3);
		mi.push(7);
		mi.push(1);
		System.out.println(mi.getMin());
		mi.pop();
		System.out.println(mi.getMin());
	}
}
