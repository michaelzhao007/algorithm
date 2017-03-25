package com.algo.stackqueue;

public class ResizeCapacityStringStack {
    private String arr[];
    private int N;
    
    public ResizeCapacityStringStack() {
        arr = new String[1];
    }
    
    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for(int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }
    
    public void push(String elem) {
        if(N==arr.length) resize(2*arr.length);
        arr[N++] = elem;
    }
    
    public String pop() {
        String elem = arr[--N];
        arr[N] = null;
        if(N>0&&N==(arr.length)/4.0) resize((arr.length)/2);
        return elem;
    }

}
