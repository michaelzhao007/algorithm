package com.algo.stackqueue;

public class FixedCapacityStringStack {
    private String[] arr;
    private int N;
    
    
    public FixedCapacityStringStack(int size) {
        arr = new String[size];
        N = 0;
    }
    
    public void push(String elem) {
        arr[N++] = elem;
    }
    
    public String pop() {
       return arr[--N];
    }

}
