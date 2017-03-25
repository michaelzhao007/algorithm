package com.algo.union;

import com.algo.union.BSTHeight.BSTNode;

public class BSTCount {
	class BSTNode {
		BSTNode left;
		BSTNode right;
		int val;
	}
	
	public int getCounts(BSTNode root) {
		if(root==null) return 0;
		else if(root.left==null&&root.right==null) return 1;
		return getCounts(root.left)+getCounts(root.right)+1;
	}
	
	public static void main(String[] args) {
		BSTCount hei = new BSTCount();
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
		//node2.right=node3;
		System.out.println(hei.getCounts(node));
	}

}
