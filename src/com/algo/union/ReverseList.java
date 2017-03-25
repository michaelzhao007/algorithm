package com.algo.union;

import java.util.LinkedList;
import java.util.List;

public class ReverseList<T> {
	
	class Node{
	public Node next;
	public int data;
	}
	
	public Node reverse(Node node) {
		Node newNode = null;
		Node current = node;
		while(current!=null) {
			Node next =current.next;
			current.next = newNode;
			newNode = current;
			current = next;
		}
		node = newNode;
		return node;
	}
	
	public Node reverse2(Node node) {
		Node current = node;
		Node prev = null;
		while(current!=null) {
			Node tmp = current.next;
			current.next = prev;
			prev = current;
		    current = tmp;
		}
		return prev;
	}

}
