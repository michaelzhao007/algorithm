package com.algo.graph;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	 static TreeNode lastvisited = null;
	 
	 public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
	        Queue<String> queue = new LinkedList<String>();
	        Queue<String> next = new LinkedList<String>();
	        queue.add(beginWord);
	        int count = 1;
	        while(!queue.isEmpty()) {
	            if(queue.contains(endWord)) return count;

	            String elem = queue.remove();
	            //if(endWord.equals(elem)) return count;
	            
	            for(String str: wordList) {
	                if(checkOne(elem, str)) {
	                    next.add(str);
	                }
	            }
	            if(queue.isEmpty()) {
	                count++;
	                queue = next;
	                next = new LinkedList<String>();
	            }
	            
	        }
	        return 0;
	    }
	    
	    public static boolean checkOne(String a, String b) {
	        if(a.length()!=b.length()) return false;
	        int j = 0;
	        for(int i = 0; i < a.length(); i++) {
	            if(a.charAt(i)!=b.charAt(i)) j++;
	        }
	        return j==1;
	    }
	    public static TreeNode buildTree(int[] inorder, int[] postorder) {
	        return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
	    }
	    
	    public static TreeNode helper(int[] inorder, int istart, int iend, int[] postorder, int pstart, int pend) {
	        if(istart>iend) return null;
	        TreeNode root =  new TreeNode(postorder[pend]);
	        for(int i = istart; i <= iend; i++) {
	            if(root.val==inorder[i]) {
	                int leftcount = i-istart;
	                root.left=helper(inorder, istart, i-1, postorder, pstart, pstart+leftcount);
	                root.right=helper(inorder,i+1,iend, postorder,pstart+leftcount+1, pend-1);
	            }
	        }
	        return root;
	    }
	    
	  public static void flatten(TreeNode root) {
	        if(root==null) return;
	        TreeNode right = root.right;
	        if(lastvisited!=null) {
	        	lastvisited.left=null;
	        	lastvisited.right=root;
	        }
	        
	        lastvisited=root;
	        flatten(root.left);
	        flatten(right);
	    }
	    
	   public static void helper(TreeNode root) {
	        if(root==null || (root.left==null&&root.right==null)) return;
	        
	        TreeNode temp = root.right;
	        root.right = root.left;
	        root.left = null;
	        while(root.right!=null) root=root.right;
	        root.right = temp;
	        helper(root);
	    }
	
	public static int trap(int[] A) {  
        if (A == null || A.length == 0)  
            return 0;  
          
        int i, max, total = 0;
        int left[] = new int[A.length];
        int right[] = new int[A.length];  
  
        // from left to right
        left[0] = A[0];
        max = A[0];
        for (i = 1; i < A.length; i++) {  
            left[i] = Math.max(max, A[i]);
            max = Math.max(max, A[i]);
        }  
  
        // from right to left
        right[A.length-1] = A[A.length-1];
        max = A[A.length-1];
        for (i = A.length-2; i >= 0; i--) {  
            right[i] = Math.max(max, A[i]);
            max = Math.max(max, A[i]);
        }  
  
        // trapped water (when i==0, it cannot trapped any water)
        for (i = 1; i < A.length-1; i++) {  
            int bit = Math.min(left[i], right[i]) - A[i];  
            if (bit > 0)  
                total += bit;  
        }  
  
        return total;  
    }
    public static void main(String args[] ) throws Exception {
    	 Set<String> set = new HashSet<String>();
    	 set.add("hot");
    	 set.add("dog");
    	 set.add("cog");
    	 set.add("pot");
    	 set.add("dot");

    	System.out.println(ladderLength("hot", "dog", set));
    }
}