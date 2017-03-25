package com.algo.union;

import java.util.Stack;

public class Queue<T> {
	
	Stack<T> s1;
	Stack<T> s2;

	public Queue() {
	  s1  = new Stack<T>(); 	
	  s2 = new Stack<T>(); 
	}
	
	public void enqueue(T elem) {
		s1.push(elem);
	}
	
	public T deque() {
		while(s2.isEmpty()) {
			while(!s1.isEmpty()) {
			s2.push(s1.pop());
			}
		}
		return s2.pop();
	}

}
