package com.algo.bst;

public class BST {
	private Node root;
	private class Node{
		private Node left;
		private Node right;
		private int key;
		private int val;
		Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
	
	public void put(int key, int val) {
		root = put(root,key,val);
	}
	
	public Node put(Node x, int key, int val) {
		if(x==null) return new Node(key,val);
		if(key<x.key) x.left = put(x.left,key,val);
		else if(key > x.key) x.right = put(x.right,key,val);
		else x.val = val;
		return x;
	}
	
	public static void main(String[] args) {
		BST bst = new BST();
		bst.put(5, 1);
	}

}
