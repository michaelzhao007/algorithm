package com.algo.string;
import java.util.*;
import java.io.*;
import java.util.zip.CheckedInputStream;

public class SuffixArrayLong {
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

    public class Suffix implements Comparable {
        String suffix;
        int start;

        Suffix(String suffix, int start) {
            this.suffix = suffix;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            Suffix other = (Suffix) o;
            return suffix.compareTo(other.suffix);
        }
    }
    
    public int[] sortCharacters(String s) {
        int[] order = new int[s.length()];
        int[] count = new int[512];
        for(int i = 0; i < s.length(); i++) {
            count[s.charAt(i)] = count[s.charAt(i)] + 1;
        }
        for(int j = 1; j < 512; j++) {
            count[j] = count[j] + count[j-1];
        }
        for(int i = s.length()-1; i >= 0; i--) {
           char c = s.charAt(i);
           count[c] = count[c]-1;
           order[count[c]] = i;
        }
        return order;
    }
    
    public int[] computerCharClasses(String s, int[] order) {
        int[] classes = new int[s.length()];
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(order[i]) != s.charAt(order[i-1])) {
                classes[order[i]] = classes[order[i-1]] + 1;
            }
            else {
                classes[order[i]] = classes[order[i-1]];
            }
        }
        return classes;
    }
    
    public int[] sortDoubled(String s, int L, int[] order, int[] classes) {
        int[] count = new int[s.length()];
        int[] newOrder = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            count[classes[i]] = count[classes[i]]+1;
        }
        
        for(int i = 1; i < s.length(); i++) {
            count[i] = count[i]+count[i-1];
        }
        
        for(int i = s.length()-1; i >= 0; i--) {
            int start = (order[i]-L+s.length())%(s.length());
            int cl = classes[start];
            count[cl] = count[cl]-1;
            newOrder[count[cl]] = start;
        }
        return newOrder;
        
    }
    
    public int[] updateClasses(int[] newOrder, int[] classes, int L) {
        int n = newOrder.length;
        int[] newClass = new int[n];
        newClass[newOrder[0]] = 0;
        for(int i = 1; i < n; i++) {
            int cur = newOrder[i];
            int prev = newOrder[i-1];
            int mid = (cur+L);
            int midPrev = (prev+L)%n;
            if(classes[cur]!=classes[prev] || classes[mid]!=classes[midPrev]) {
                newClass[cur] = newClass[prev]+1;
            }
            else newClass[cur] = newClass[prev];
        }
        return newClass;
    }
    
    public int[] buildSuffixArray(String s) {
        int[] order = sortCharacters(s);
        int[] classes = computerCharClasses(s, order);
        int L = 1;
        while(L < s.length()) {
            order = sortDoubled(s, L, order, classes);
            classes = updateClasses(order, classes, L);
            L = 2*L;
        }
        return order;
    }

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        int[] result = new int[text.length()];

        // write your code here
        result = buildSuffixArray(text);
        return result;
    }


    static public void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
       
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
    }
}
