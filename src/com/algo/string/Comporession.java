package com.algo.string;

import java.math.BigInteger;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.introcs.BinaryStdIn;
import edu.princeton.cs.introcs.BinaryStdOut;
/**
 * Huffman algorithm
 * 1. count frequency freq[i] for each char i in input
 * 2. start with one node corresponding to each char i with weight freq[i]
 * 3. repeat until sing trie formed
 *    -select two tries with min weight freq[i] and freq[j]
 *    -merge into single trie with weight freq[i] and freq[j]
 * @author dongyangzhao
 *
 */
public class Comporession {
   private static final int R = 256;
   
   public static void compress() {
       // read the input
       String s = BinaryStdIn.readString();
       char[] input = s.toCharArray();
       // tabulate frequency counts
       int[] freq = new int[R];
       for (int i = 0; i < input.length; i++)
           freq[input[i]]++;

       // build Huffman trie
       Node root = buildTrie(freq);

       // build code table
       String[] st = new String[R];
       buildCode(st, root, "");

       // print trie for decoder
       writeTrie(root);

       // print number of bytes in original uncompressed message
       BinaryStdOut.write(input.length);

       // use Huffman code to encode input
       for (int i = 0; i < input.length; i++) {
           String code = st[input[i]];
           for (int j = 0; j < code.length(); j++) {
               if (code.charAt(j) == '0') {
                   BinaryStdOut.write(false);
               }
               else if (code.charAt(j) == '1') {
                   BinaryStdOut.write(true);
               }
               else throw new IllegalStateException("Illegal state");
           }
       }

       // close output stream
       BinaryStdOut.close();
   }

   public void expand() {
	   Node root = readTrie();
	   int N = BinaryStdIn.readInt();
	   
	   for(int i = 0; i < N; i++) {
		   Node x = root;
		   while(!x.isLeaf()) {
			   if(!BinaryStdIn.readBoolean()) x=x.left;
			   else x=x.right;
		   }
		   BinaryStdOut.write(x.ch,8);
	   }
	   BinaryStdOut.close();
	   
   }
   
   private static Node buildTrie(int[] freq) {
	   MinPQ<Node> pq = new MinPQ<Node>();
	   for(char i = 0; i < R; i++) {
		   if(freq[i] > 0) pq.insert(new Node(i,freq[i],null,null));
	   }
	   while(pq.size()>1) {
		   Node x = pq.delMin();
		   Node y = pq.delMin();
		   Node parent = new Node('\0',x.freq+y.freq,x,y);
		   pq.insert(parent);
	   }
	   return pq.delMin();
   }
   private static void writeTrie(Node x) {
	   if(x.isLeaf()) {
		   BinaryStdOut.write(true);
		   BinaryStdOut.write(x.ch,8);
		   return;
	   }
	   BinaryStdOut.write(false);
	   writeTrie(x.left);
	   writeTrie(x.right);
	   
   }
   
   // make a lookup table from symbols and their encodings
   private static void buildCode(String[] st, Node x, String s) {
       if (!x.isLeaf()) {
           buildCode(st, x.left,  s + '0');
           buildCode(st, x.right, s + '1');
       }
       else {
           st[x.ch] = s;
       }
   }
   
   private static Node readTrie() {
	   if(BinaryStdIn.readBoolean()) {
		   char c = BinaryStdIn.readChar(8);
		   return new Node(c,0,null,null);
	   }
	   Node x = readTrie();
	   Node y = readTrie();
	   return new Node('\0',0,x,y);
   }
	private static class Node implements Comparable<Node>{
        private char ch;
        private int freq;
        private final Node left, right;
        
        public Node(char ch, int freq, Node left, Node right) {
        	this.ch =ch;
        	this.freq=freq;
        	this.left = left;
        	this.right=right;
        }
        
        public boolean isLeaf() {
        	return left == null&&right==null;
        }
		@Override
		public int compareTo(Node that) {
			// TODO Auto-generated method stub
			return this.freq-that.freq;
		}
		
	
	}
	public static void main(String[] args) {
    String s = "ABBAACCDD";
    System.out.println(s.length()*8);
    byte[] sarr= s.getBytes();
    char[] charArr = s.toCharArray();
    String binary = new BigInteger(sarr).toString(2);
    System.out.println(binary);
    for(byte chars: sarr) {
      	
    	System.out.print(chars);
    }
	}
}
