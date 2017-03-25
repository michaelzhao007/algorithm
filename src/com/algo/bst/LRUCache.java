package com.algo.bst;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int capacity = 0;
    int size = 0;
    Map<Integer, Node> map;
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val, Node prev, Node next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node!=null) {
           rmNode(node);
           putToFirst(node);
           return node.val;
        }
        else return -1;
    }
    
    public void set(int key, int value) {
        Node node = new Node(key, value, null, null);
        map.put(key, node);
        putToFirst(node);
        size++;

        if(size > capacity) { removeLast(); }
    }
    
    public void putToFirst(Node node) {
        node.next = head;
        if(head!=null) {
           head.prev = node;
        }
        node.prev = null;
        head = node;
        if(tail==null) tail=head;
    }
    
    public static int uniquePaths(int m, int n) {
        int[] result = new int[1];
        boolean[][] visited = new boolean[m][n];
        helper(0, 0, m, n, result, visited);
        return result[0];
    }
    
    public static void helper(int row, int col, int m, int n, int[] result, boolean[][] visited) {
        if((row+1)==m&&(col+1)==n) {
            result[0]++;
            return;
        }
        else {
            if(!visited[row][col]) {
                visited[row][col] = true;
                if(row+1 < m) {
                helper(++row, col, m, n, result, visited);
                }
                if(row-1 >= 0) {
                helper(--row, col, m, n, result, visited);
                }
                if(col+1 < n) {
                helper(row, ++col, m, n, result, visited);
                }
                if(col-1 >= 0) {
                helper(row, --col, m, n, result, visited);
                }
            }
        }
    }
    public void rmNode(Node node) {
        Node cur = node;
        Node pre = node.prev;
        Node post = node.next;
        if(pre!=null) {
            pre.next = node.next;
        }
        else {
            head = post;
        }
        if(post!=null) {
            post.prev = node.prev;
        }
        else {
            tail = pre;
        }
    }
    
    public void removeLast() {
        map.remove(tail.key);
        tail = tail.prev;
        if(tail!=null) {
          tail.next = null;
          
        }
    }
    
    public static void main(String[] args) {
       
        System.out.println(uniquePaths(1,2));
        
    }
}
