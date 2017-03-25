package com.algo.string;

public class StringAlgo {
   public static int search(String pat, String txt) {
	   int patLen  = pat.length();
	   int txtLen = txt.length();
	   for(int i = 0; i < txtLen - patLen; i++) {
		   int j;
		   for(j = 0; j < patLen; j++) {
			   if(txt.charAt(i+j)!=pat.charAt(j)) break;
		   }
		   if(j==patLen) return i;
	   }
	   return txtLen;
   }
   
   public static int searchExp(String pat, String txt) {
	   int i,j;
	   int N = txt.length();
	   int M = pat.length();
	   for(i=0,j=0;i<N&&j<M;i++){
		   if(txt.charAt(i) == pat.charAt(j)) j++;
		   else {i-=j;j=0;}
	   }
	   if(j==M) return i-M;
	   else return N;
   }
   
   public static void main(String[] args) {
	searchExp("abc","defabdabcftyu");
}
}
