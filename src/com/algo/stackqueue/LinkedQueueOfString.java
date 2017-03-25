package com.algo.stackqueue;

public class LinkedQueueOfString {
    private Node first, last;
    class Node {
        Node next;
        String value;
    }
    public boolean isEmpty() {
        return first == null;
    }
    
    public void enqueue(String elem) {
        Node oldlast = last;
        last = new Node();
        last.value = elem;
        System.out.println(oldlast);
        if(isEmpty()) first = oldlast;
        else oldlast.next  = last;
    }
    
    public String deque() {
        String elem = first.value;
        first = first.next;
        if(isEmpty()) last = null;
        return elem;
    }
    
    public static void main(String[] args) {
        LinkedQueueOfString qu = new LinkedQueueOfString();
        qu.enqueue("123");
        qu.enqueue("2345");
    }

}
