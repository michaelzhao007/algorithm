package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class MyQueue {
    // Push element x to the back of queue.
    Stack<Integer>  s1 = new Stack<Integer>();
    Stack<Integer>  s2 = new Stack<Integer>();

    public void push(int x) {
        if(s2.isEmpty()){
            s1.push(x);
            while(!s1.isEmpty()) {
               s2.push(s1.pop());
            }
        }
        else if(s1.isEmpty()){
            s2.push(x);

            while(!s2.isEmpty()) {
               s1.push(s2.pop());
            }
        }
     }
    

    // Removes the element from in front of queue.
    public void pop() {
        if(s2.isEmpty()) s1.pop();
        else s2.pop();
    }

    // Get the front element.
    public int peek() {
        if(s2.isEmpty()) return s1.peek();
        else return s2.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
    
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result= new ArrayList<List<String>>();
        if(strs==null || strs.length==0) return result;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        
        for(int i = 0; i < strs.length; i++) {
            char[] ch = strs[i].toCharArray();
            Arrays.sort(ch);
            String newstr= ch+"";
            if(!map.containsKey(newstr)) {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                map.put(newstr, temp);
            }
            else {
                List<String> temp = map.get(newstr);
                temp.add(strs[i]);
                map.put(newstr, temp);
            }
        }
        
        for(String str : map.keySet()) {
            List<String> res = map.get(str);
            Collections.sort(res);
            result.add(res);
        }
        return result;
        
    }
    
    public static void reverseWords(char[] s) {
        if(s==null || s.length==0) return;
        reverse(s, 0, s.length-1);
        int prevSpace = 0;
        int curSpace = 0;
        for(int i = 0; i < s.length; i++) {
            if(s[i] == ' ') {
                reverse(s, prevSpace, i-1);
                prevSpace = i+1;
            }
        }
    }
    
    public int longestSubSeq(int[] arr) {
        int max = 0;
        int[] f = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            f[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[j] <= arr[i]) {
                f[i] = f[i] > f[j]+1? f[i]: f[j]+1;
                }
            }
            if(f[i] > max) {
                 max = f[i];
            }
            
        }
        return max;
    }
    
    
    public static void reverse(char[] s, int lo, int hi) {
        while(hi>=lo) {
            char temp = s[lo];
            s[lo] = s[hi];
            s[hi] = temp;
            lo--;
            hi++;
        }
    }
    public static void main(String[] args) {
        reverseWords(new char[]{'a',' ','b',' ','c',' ','d',' ','e'});
        //qu.pop();
        //System.out.println(qu.empty());

        //qu.pop();
        //System.out.println(qu.empty());
    }
}