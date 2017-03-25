package com.algo.stackqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;


import edu.princeton.cs.algs4.Queue;

public class CloneGraph {
    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class UndirectedGraphNode {
             int label;
             List<UndirectedGraphNode> neighbors;
             UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }
    
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode graph) {
        if(graph == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> copied = new HashMap<CloneGraph.UndirectedGraphNode, CloneGraph.UndirectedGraphNode>();
        clone(graph, copied);
        return copied.get(graph);      
    }
    
    public static UndirectedGraphNode clone(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> copied) {
        if(copied.containsKey(node)) return copied.get(node);
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        copied.put(node, newNode);
        for(UndirectedGraphNode neighbor: node.neighbors) {
            newNode.neighbors.add(clone(neighbor, copied));
        }
        return newNode;
    }
    
    public static UndirectedGraphNode cloneGraphBfs(UndirectedGraphNode graph) {
        if(graph==null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> copied = new HashMap<CloneGraph.UndirectedGraphNode, CloneGraph.UndirectedGraphNode>();
        java.util.Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(graph);
        copied.put(graph, new UndirectedGraphNode(graph.label));
        while(!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.remove();
            for(UndirectedGraphNode neighbor: cur.neighbors) {
                if(copied.containsKey(neighbor)) {
                    copied.get(cur).neighbors.add(neighbor);
                }
                else {
                    UndirectedGraphNode newnode = new UndirectedGraphNode(neighbor.label);
                    copied.put(neighbor, newnode);
                    copied.get(cur).neighbors.add(newnode);
                    queue.add(neighbor);
                }
            }
        }
        return copied.get(graph);
    }
    
    public static String reverseSentence(String sent) {
        String reStr  = reverseHelper(" "+sent);
        
        int start = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < reStr.length(); i++) {
            if(reStr.charAt(i) == ' ') {
                sb.append(reverseHelper(reStr.substring(start, i+1)));
                start = i+1;
            }
        }
        
        return sb.toString().substring(1);
    }
    
    static class TreeNode {
        TreeNode left, right;
        int val;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static void inorder(TreeNode node) {
        if(node==null) return;
        inorder(node.left);
        //System.out.println(node.val);
        inorder(node.right);
    }
    
    public static void iterInorder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(!stack.isEmpty() || node!=null) {
           while(node!=null) {
               stack.push(node);
               node = node.left;
           }
           
           node = stack.pop();
           System.out.println(node.val);
           node = node.right;
           
        }
    }
    
    public static String reverseHelper(String str) {
        int i = 0;
        int j = str.length()-1;
        StringBuilder sb = new StringBuilder(str);       
        while(i<=j) {
            char ic = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, ic);
            i++;
            j--;
        }
        return sb.toString();
    }
    
    public static UndirectedGraphNode bfs(UndirectedGraphNode graph) {
        java.util.Queue<UndirectedGraphNode> cur = new LinkedList<CloneGraph.UndirectedGraphNode>();
        java.util.Queue<UndirectedGraphNode> next = new LinkedList<CloneGraph.UndirectedGraphNode>();
        cur.add(graph);

        UndirectedGraphNode root = new UndirectedGraphNode(-1);
        UndirectedGraphNode result = root;
        
        
        
        while(!cur.isEmpty()) {
            UndirectedGraphNode node  = cur.remove();
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
            if(root.neighbors==null) root.neighbors = new LinkedList<CloneGraph.UndirectedGraphNode>();
            root.neighbors.add(newNode);
            
            for(UndirectedGraphNode neighbor: node.neighbors) {
                next.add(neighbor);
            }
            if(cur.isEmpty()) {
                root = node;
                cur = next;
                next =  new LinkedList<CloneGraph.UndirectedGraphNode>();
            }
        }
        return result.neighbors.get(0);
    }

    
    public static double pow(double x, int n) {
        if(n < 0) return 1.0/power(x, -n);
        else return power(x, n);
    }
    
    public static double power(double x, int n) {
        if(n==0) return 1;
        double v = power(x, n/2);
        if(n%2==0) return v*v;
        else return v*v*x;
    }
    
    public static int sqrt(int x) {
        int left  = 1; 
        int right = x/2;
        int last_mid = 0;
        while(left <= right) {
            int mid = left+(right-left)/2;
            if(Math.abs(mid*mid-x)<0.001) return mid;
            else if(mid*mid<x) {last_mid = mid; left = mid+1; }
            else right=mid-1;
        }
        return last_mid;
        
    }
    
    public static int reverInteger(int x) {
        int sign = 1;
        String str = Integer.toString(x);
        if(str.charAt(0) == '+') {
           str = str.substring(1, str.length());
        }
        else if (str.charAt(0) == '-')  {
            sign = -sign;
            str = str.substring(1, str.length());
        }
        int result = 0;
        for(int i = str.length()-1; i >= 0; i--) {
            result = 10*result+Integer.valueOf(str.charAt(i)+"");
        }
        return result*sign;
    }
    
   /* public static boolean isPalindrome(int x) {
        if(x<0) return false;
        int d = 1;
        while(x/d>=10) d*=10;
    }*/
    
    public static String minWindow(String s, String t) {    
      Map<Character, Integer> map = new HashMap<Character, Integer>();
      for(int i = 0; i < t.length(); i++) {
          if(!map.containsKey(t.charAt(i))) {
              map.put(t.charAt(i), 1);
          }
          else {
              map.put(t.charAt(i), map.get(t.charAt(i))+1);
          }
      }
      int tidx = 0;
      int mins = 0;
      int maxs = 0;
      int len = Integer.MAX_VALUE;
      for(int i = 0; i < s.length(); i++) {
           tidx = 0;
           for(int j = i; j < s.length(); j++) {
              if(map.containsKey(s.charAt(j))) tidx++;
              if(tidx==t.length())  {
                  if((j-i) < len) {
                      mins = i;
                      maxs = j;
                      len = j-i;
                  }
              }
           }
      }
      return s.substring(mins, maxs+1);   
    }
    
    public static int maxPts(List<Point> list) {
        int max = Integer.MIN_VALUE;
        Map<Double, Integer> map = new HashMap<Double, Integer>();
        for(Point oriPt: list) {
           int count = 0;
           for(Point corPt: list) {
               double tan = calculateTan(oriPt, corPt);
               if(oriPt!=corPt) {
               if(!map.containsKey(tan)) {
                   if(count < 1) count = 1;
                   map.put(tan, 1);
               }
               else {
                   count++;
                   map.put(tan, map.get(tan)+1);
               }
               }
           }
           if(count > max) max = count;
        }
        return max;
    }
    
    public static double calculateTan(Point oriPt, Point corPt) {
        if(oriPt.x == corPt.x) return 1;
        return Math.abs(oriPt.y-corPt.y)/Math.abs(oriPt.x-corPt.x);
    }
    
/*    public int maxProfit(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
           
        }
    }*/
    
    public static void main(String[] args) {
    /*    System.out.println(sqrt(8));
        System.out.println(minWindow("ADOBECODEBANC","ABC"));*/
 /*       List<Point> list = new ArrayList<Point>();
        Point pt1 = new Point(1, 1);
        Point pt2 = new Point(2, 2);
        Point pt3 = new Point(3, 3);
        Point pt4 = new Point(4, 5);
        list.add(pt1);
        list.add(pt2);
        list.add(pt3);
        list.add(pt4);
        System.out.println(maxPts(list));*/
       /* UndirectedGraphNode node1 = new UndirectedGraphNode(0);
        UndirectedGraphNode node2 = new UndirectedGraphNode(1);
        UndirectedGraphNode node3 = new UndirectedGraphNode(2);
        node1.neighbors = new LinkedList<CloneGraph.UndirectedGraphNode>(); 
        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node2.neighbors = new LinkedList<CloneGraph.UndirectedGraphNode>(); 
        node2.neighbors.add(node2);
        bfs(node1);*/
       /* System.out.println(reverseSentence("I am a student."));*/
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        
        node1.left = node2;
        node2.left = node3;
        node1.right = node4;
        iterInorder(node1);




        
    }
    
   
    

}
