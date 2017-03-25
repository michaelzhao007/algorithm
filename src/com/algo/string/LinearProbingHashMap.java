package com.algo.string;

public class LinearProbingHashMap<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private int N;
    private int M;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashMap() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashMap(int capacity) {
        M = capacity;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if(keys[i].equals(key)) return vals[i];
        }
        return null;
    }
    
    public static void main(String[] args) {
        LinearProbingHashMap<Integer, String> map = new LinearProbingHashMap<Integer, String>();
        map.put(1, "a");
        map.put(2, "c");
        System.out.println(map.get(2));
    }

}
