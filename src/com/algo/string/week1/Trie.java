package com.algo.string.week1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Trie {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
     public static class ListNode {
            int val;
             ListNode next;
             ListNode(int x) { val = x; }
         }
     
     public static void reorderList(ListNode head) {
         if(head==null || head.next==null) return;
         ListNode slow = head;
         ListNode fast = head;
         while(fast.next!=null && fast.next.next!=null) {
             fast = fast.next.next;
             slow = slow.next;
         }
         
         ListNode firsthalf = head;
         ListNode secondhalf = slow.next;
         slow.next = null;
         
         secondhalf = reverse(secondhalf);
         
         while(secondhalf!=null && firsthalf!=null) {
             ListNode firsttemp = firsthalf.next;
             ListNode secondtemp = secondhalf.next;

             firsthalf.next = secondhalf;
             secondhalf.next = firsttemp;
             
             firsthalf = firsttemp;
             secondhalf = secondtemp;
         }
     }
     
     public static ListNode reverse(ListNode node) {
         if(node==null || node.next==null) return node;
         ListNode prev = node;
         ListNode cur = node.next;
         while(cur!=null) {
             ListNode temp = cur.next;
             cur.next = prev;
             prev = cur;
             cur = temp;
         }
         //node.next = null;
         return prev;
     }
    
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode cur = head;
        ListNode ne = head.next;
        head = pre;
        while(cur!=null && ne!=null) {
            while(ne!=null && cur.val==ne.val) {
                ne = ne.next;
            }
            if(cur.next!=ne) {
               pre = new ListNode(-1); 
               pre.next = ne;
            }
            else {
               pre = pre.next;
            }
            if(ne==null) break;
            cur = pre.next;
            if(pre.next!=null) {
            ne = pre.next.next;
            }
        }
        return head.next;
        
    }

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
        // write your code here
        int count = 0;

        for(String elem: patterns) {

            Map<Character, Integer> currentEdge = null;
            if(!trie.isEmpty())   currentEdge = trie.get(0);
            if(currentEdge==null) { currentEdge = new HashMap<Character, Integer>();                     trie.add(currentEdge);
                                    trie.add(currentEdge); }
            for(int i = 0; i < elem.length(); i++)
            {
              if(currentEdge.containsKey(elem.charAt(i))) {
                  currentEdge = trie.get(currentEdge.get(elem.charAt(i)));
              }
              else {
                  currentEdge = new HashMap<Character, Integer>();
                  trie.add(currentEdge);
              }
            }
        }

        return trie;
    }

    static public void main(String[] args) throws IOException {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        reorderList(node);
    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
}
