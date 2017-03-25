package com.algo.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution2 {
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        int count = 0;
        while(!queue.isEmpty()) {
          String elem = queue.poll();
          String[] arr = (String[]) wordList.toArray();
          if(elem.equals(endWord)) break;
          for(int i = 0; i < arr.length; i++) {
             if(justOneWord(elem, arr[i])) queue.add(arr[i]);     
            
        
          }
          count++;
        }
        return count;
    }
    
    public static boolean justOneWord(String str1, String str2) {
        int num = 0;
        for(int i =0;i < str1.length();i++) {
            if(str1.charAt(i) != str2.charAt(i)) num++;
        }
        if(num==1) return true;
        else return false;
    }
    
    public static void main(String[] args) {
        String beg = "hit";
        String end="cog";
        Set<String> set = new HashSet<String>();
        set.add("hot");
        set.add("dot");
        set.add("hot");
        set.add("hot");
        set.add("hot");
        System.out.println(ladderLength(beg,end,set));

    
    }
}