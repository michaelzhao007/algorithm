package com.algo.union;

public class Node<Key,Value> {
	 public Key key;
	 public Value value;
	 public Node next;
	 public Node(Key key, Value val) {
		 this.key = key;
		 this.value=val;
	 }
}