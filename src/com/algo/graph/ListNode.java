package com.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListNode {
/*     @Override
    public String toString() {
         String s = Integer.toString(val);
         while(next!=null) {
             s=s+ "->" + next.val;
             next= next.next;
         }
        return s;
    }*/
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
     
     public static String reverse(String s) {
         char[] arr = s.toCharArray();
         int i = 0;
         int j = s.length()-1;
         while(j>i) {
             char temp = arr[i];
             arr[i] = arr[j];
             arr[j] = temp;
             i++;
             j--;
         }
         return new String(arr);
         
     }
     
     public static List<List<Integer>> permute(int[] nums) {
         List<List<Integer>> list = new ArrayList<List<Integer>>();
         List<Integer> result = new ArrayList<Integer>();
         boolean[] visited = new boolean[nums.length];
         dfs(nums,  list, result, visited);
         return list;
     }
     
     public static void dfs(int[] nums, List<List<Integer>> list, List<Integer> res, boolean[] visited) {
         if(res.size()==nums.length) {
             list.add(new ArrayList<Integer>(res));
             return;
         }
         for(int i = 0; i < nums.length; i++) {
             if(!visited[i]) {
                 visited[i] = true;
                 res.add(nums[i]);
                 dfs(nums, list, res, visited);
                 visited[i] = false;
                 res.remove(res.size()-1);
             }
         }
     }
     
     public static boolean hasPathSum(TreeNode root, int sum) {
         int s = 0;
         boolean flag = false;
         hasPathSumHelper(root, sum, s, flag);
         return flag;
     }
     
     public static void hasPathSumHelper(TreeNode root, int sum, int s, boolean flag) {
         if(sum==s) flag = true;
         if(root==null) return;
         hasPathSumHelper(root.left, sum, s+root.val, flag);
         hasPathSumHelper(root.right, sum, s+root.val, flag);
     }
     
     public static void rotate(int[] nums, int k) {
         int[] result = new int[nums.length];

         for(int i = 0; i < nums.length; i++) {
              if(i < (nums.length-k)) {
              result[i+k] =  nums[i];}
              else {
              if(k!=0) {     
              result[i%k] = nums[i];}
              else result[i] = nums[i];
              }
         }
         
         for(int j = 0; j < nums.length; j++) {
             nums[j] = result[j];
         }
         }
         
     public static boolean isSymmetric(TreeNode root) {
         return symmetric(root.left, root.right);
         
     }
     
     static int[] arbitrage(String[] quotes) {
         int[] result = new int[quotes.length];
         int i = 0;
         for(String quote: quotes) {
             double init = 100000;
             String[] inds = quote.split(" ");
             for(String ind: inds) {
                 init = init/Double.valueOf(ind);
             }
             if(init < 100000) result[i++] = 0;
             else result[i++] = (int)(init-100000);
         }
         return result;
     }
     
     public static boolean symmetric(TreeNode node1, TreeNode node2) {
         if(node1 == null) return node2 == null;
         if(node1.val != node2.val) return false;
         return symmetric(node1.left, node2.right)&&symmetric(node1.right, node2.left);
     }
     
     public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
         List<TreeNode> listp = new ArrayList<TreeNode>();
         List<TreeNode> listq = new ArrayList<TreeNode>();
         traverse(root, p, listp);
         traverse(root, q, listq);
         TreeNode prev = null;
         int sizep = listp.size();
         int sizeq = listq.size();
         int i = 0;
         for (i = 0; i < sizep && i < sizeq; i++) {
             if(listp.get(i) != listq.get(i)) break;
         }
         return listp.get(i-1);
         
     }
     
     static int findMax(int n, String tree) {
         int[] max = new int[1];
         boolean parentChosen = false;
         helper(max, tree, 1, parentChosen);
         //if(max[0] > n) return n;
         return max[0];
     }


     static int helper(int[] max, String tree, int idx,  boolean par) {
         if(idx>=tree.length() || tree.charAt(idx-1) == '#') {
             return 0;
         }
        
         int curPC = 0;
         int cur = 0;
         //parent is chosen
         if(par) {
         curPC = helper(max, tree, 2*idx, false)+helper(max, tree, 2*idx+1, false);
         }
         //parent is not chosen
         else {
         cur = Math.max(helper(max, tree, 2*idx, false)+helper(max, tree, 2*idx+1, false), helper(max, tree, 2*idx+1, true)+helper(max, tree, 2*idx, true)+Character.getNumericValue(tree.charAt(idx-1)));
         }
     
         int current = Math.max(cur, curPC);
         max[0] = Math.max(max[0], current);
         return current;
         }
     
     
     public static void traverse(TreeNode root, TreeNode node, List<TreeNode> stack) {
         if(root!=null) stack.add(root);
         if(root == null) { return;}
         if(root == node) return;
         traverse(root.left, node, stack);
         traverse(root.right, node, stack);
         stack.remove(stack.size()-1);

     }
     public static void main(String[] args) {
         TreeNode node = new TreeNode(-1);
         TreeNode node1 = new TreeNode(0);
         TreeNode node2 = new TreeNode(3);
         TreeNode node3 = new TreeNode(-2);
         TreeNode node4 = new TreeNode(4);
         TreeNode node5 = new TreeNode(8);
         node.left = node1;
         node.right = node2;
         node1.left = node3;
         node1.right = node4;
         node3.left = node5;



         //TreeNode node2= new TreeNode(2);
         //TreeNode node3= new TreeNode(3);
         //TreeNode node4= new TreeNode(3);
         //node.right = node1;

       
         //lowestCommonAncestor(node, node5, node4);
         System.out.println(findMax(6, "34513#1"));
     }
     
 }
