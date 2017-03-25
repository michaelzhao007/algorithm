package com.algo.stackqueue;

public class LinkedStackOfString {

    Node first = null;

    class Node {
        Node next;
        String value;
    }

    public void push(String elem) {  
            Node oldfirst = first;
            first = new Node();
            first.value = elem;
            first.next = oldfirst;
    }
    
    public String pop() {
        String val = first.value;
        first = first.next;
        return val;
    }
    
    public static void main(String[] args) {
        LinkedStackOfString str = new LinkedStackOfString();
        str.push("1");
        str.push("2");
        str.push("3");
        System.out.println(str.pop());
        System.out.println(str.pop());

        System.out.println(str.pop());

    }

}
