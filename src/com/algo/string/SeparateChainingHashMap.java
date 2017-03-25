package com.algo.string;

import java.util.LinkedList;

public class SeparateChainingHashMap<Key, Value> {

    class Node {
        Key key;
        Value val;
    }

    private static final int INIT_CAPACITY = 4;
    private int N; // number of key-value pairs
    private int M; // hash table size
    LinkedList<Node>[] list;

    public SeparateChainingHashMap() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashMap(int M) {
        this.M = M;
        list = (LinkedList<SeparateChainingHashMap<Key, Value>.Node>[]) new LinkedList[M];
        for (int i = 0; i < M; i++) {
            list[i] = new LinkedList<SeparateChainingHashMap<Key, Value>.Node>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return N;
    }

    private void resize(int chains) {
        SeparateChainingHashMap<Key, Value> temp = new SeparateChainingHashMap<Key, Value>(
                chains);
        for (int i = 0; i < M; i++) {
            for (SeparateChainingHashMap<Key, Value>.Node node : list[i]) {
                temp.put(node.key, node.val);
            }
        }
        this.M = temp.M;
        this.N = temp.N;
        this.list = temp.list;
    }

    public void put(Key key, Value val) {
        // TODO Auto-generated method stub
        int i = hash(key);
        if (!list[i].contains(key))
            N++;
        Node node = new Node();
        node.key = key;
        node.val = val;
        list[i].add(node);
    }

    public Value get(Key key) {
        int i = hash(key);
        Value val = null;
        for (Node node : list[i]) {
            if (node.key == key)
                val = node.val;
        }
        return val;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    
    public static void main(String[] args) {
        SeparateChainingHashMap<Integer, String> map = new SeparateChainingHashMap<Integer, String>();
        map.put(1, "a");
        map.put(2, "c");
        System.out.println(map.get(2));
    }

}
