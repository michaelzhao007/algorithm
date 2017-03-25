package com.algo.thread;

import java.util.HashMap;

public class LRU {
    int capacity;
    Node head;
    Node end;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    
    
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            setHead(node);
            return node.val;
            
        }
        return -1;
    }
    
    public void remove(Node node) {
        if(node.prev!=null) node.prev.next = node.next;
        else head = node.next;
        
        if(node.next!=null) node.next.prev = node.prev;
        else end = node.prev;
    }
    
    public void setHead(Node node) {
        node.next = head;
        node.prev = null;
        if(head!=null) {
            node = head.prev;
        }
        head = node;
        if(end==null) {
           end = head;   
        }      
    }
    
    public void set(int key, int val) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            setHead(node);
            node.val = val;            
        }
        else {
            Node n = new Node(key,val);
            if(map.size() >= capacity) {
                Node node = map.get(end.key);
                remove(node);
                setHead(n);
            }
            else {
                setHead(n);
            }
            map.put(key, n);
        }
    }
    
    
 

}
