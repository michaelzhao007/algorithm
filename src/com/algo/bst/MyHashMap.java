package com.algo.bst;


import java.io.*;
import java.util.*;
//do not import java Maps!!

import com.algo.bst.MyHashMap.Node.NodeInner;

public class MyHashMap<K,V> {
class Node<K, V> {
    NodeInner<K, V> first;
    Node(K k, V v) {
        first = new NodeInner(k, v);
    }
    class NodeInner<K, V> {
       K k;
       V v;
       NodeInner next;
       NodeInner(K k, V v) {
           this.k = k;
           this.v = v;
       }
   }
   
   NodeInner getFirst() {
      NodeInner inner = first;
      return inner;
   }
   
   void add(NodeInner node) {
           NodeInner temp = first;
           while(temp.next!=null) {
               temp = temp.next;
           }
           temp.next = node;
    }
}
   
   
    

    private int INIT_CAPACITY = 50;
    //List[] list = new LinkedList[INIT_CAPACITY];
    private int size = INIT_CAPACITY;
    Node[] list = (Node<K, V>[]) new Node[size];

    public V get(K key) {
        //implement!!
        int spot = key.hashCode()%size;
        if(list[spot]==null) return null;
        else {
            Node temp = list[spot];
            Node.NodeInner tempN = temp.getFirst();
            while(tempN!=null) {
                if(tempN.k == key) { 
                    return (V) tempN.v;
                }
            }
        }
        return null;
    }

   

    public void put(K key, V value){
        //implement!!
        int spot = key.hashCode()%size;
        if(list[spot] == null) {
           list[spot] = new Node(key, value);
        }
        else {
            boolean found = false;
            Node.NodeInner tempN = list[spot].getFirst();
            Node.NodeInner last = null;
            while(tempN!=null) {
                
                if(tempN.k == key) tempN.v = value;
                if(tempN.next == null) last = tempN;
                tempN= tempN.next;
            }
            if(!found) {
                Node inner = list[spot];
                inner.add(inner.new NodeInner(key, value));
            }
        }
    }


    public static void main(String args[] ) throws Exception {

        MyHashMap<String,String> mymap = new MyHashMap<>();
        mymap.put("key", "value");
        mymap.put("key2", "Value2");
        String value = mymap.get("key2");

        if(!"value".equals(value)){
            throw new RuntimeException("Invalid Value!");
        }else{
            System.out.println("value=" + value);
        }
    }

}
