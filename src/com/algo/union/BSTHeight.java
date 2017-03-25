package com.algo.union;

public class BSTHeight {

	class BSTNode {
		BSTNode left;
		BSTNode right;
		int val;
	}
	
	public int getHeight(BSTNode node) {
		return maxHeight(node);
	}
	
	public int maxHeight(BSTNode node) {
		if(node==null) return 0;
		else if(node.left==null&&node.right==null) return 1;
		else if(maxHeight(node.left)>maxHeight(node.right)) return maxHeight(node.left)+1;
		else return maxHeight(node.right)+1;
	}
	
	public static void main(String[] args) {
		BSTHeight hei = new BSTHeight();
		BSTNode node = hei.new BSTNode();
		BSTNode node1 = hei.new BSTNode();
		BSTNode node2 = hei.new BSTNode();
		BSTNode node3 = hei.new BSTNode();

		node.val = 12;
		node1.val =11;
		node2.val=10;
		node3.val=18;
		
		node.left=node1;
		node1.left=node2;
		node2.right=node3;
	    System.out.println(hei.getHeight(node));


	}
}
