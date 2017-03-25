package com.algo.union;

public class BST {
	private BSTNode root;
	
	class BSTNode {
	private BSTNode left;
	private BSTNode right;
	private Integer value;
	}
	
	public BST() {
		root = null;
	}
	
	public void delete(int val) {
		
	}
	
	private BSTNode delete(BSTNode rootNode, int val) {
		if(rootNode==null) return null;
		if(rootNode.value>val) rootNode.right = delete(rootNode.right,val);
		else if(rootNode.value<val) rootNode.left = delete(rootNode.left,val);
		else if(rootNode.value==val) {
			if(rootNode.right==null) return rootNode.left;
			if(rootNode.left==null) return rootNode.right;
			else {
				BSTNode t = rootNode;
				rootNode = getMin(t.right);
				rootNode.right = deleteMin(t.right);
				rootNode.left=t.left;
			}
		}
		return rootNode;
	}
	
	public void insert(BSTNode node) {
		root = insertUtil(node,root);
	}
	
	public BSTNode deleteMin(BSTNode node) {
		if(node.left==null) return node.right;
		node.left = deleteMin(node.left);
		return node;
	}
	
	private BSTNode insertUtil(BSTNode node, BSTNode rootNode) {
		if(rootNode==null) {
			rootNode = node;
		}
		else{
			if(node.value < rootNode.value) rootNode.left = insertUtil(node, rootNode.left);
			else rootNode.right = insertUtil(node,rootNode.right);
		}
		return rootNode;
	}
	
	public int getMax() {
		BSTNode maxNode = root;
		while(maxNode.right!=null) {
			maxNode = maxNode.right;
		}
		return maxNode.value;
	}
	
	public BSTNode getMin(BSTNode node) {
		BSTNode minNode = node;
		while(minNode.left!=null) {
			minNode = minNode.left;
		}
		return minNode;
	}
	
	public void preoder() {
		preorder(root);
	}
	
	public void preorder(BSTNode node) {
		if(node==null) return;
		preorder(node.left);
		System.out.println(node.value);
		preorder(node.right);
	}
	
	public static void main(String[] args) {
		BST bst = new BST();
		BSTNode node = bst.new BSTNode();
		BSTNode node1 = bst.new BSTNode();
		BSTNode node2 = bst.new BSTNode();
		node.value=2;
		node1.value=1;
		node2.value=8;
		bst.insert(node);
		bst.insert(node2);
		bst.insert(node1);
		System.out.println(bst.getMax());
		bst.preoder();


	}

}
