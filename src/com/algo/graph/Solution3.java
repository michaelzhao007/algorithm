package com.algo.graph;

public class Solution3 {
	
	public static String longestPalindrome(String s) {
		int max = 0;
		String res = "";
		for(int i = 0; i < s.length(); i++) {
			for(int j = i+1; j < s.length(); j++) {
				String sub = s.substring(i, j+1);
				if(isPalindrome2(sub) && sub.length() > max) {
					max = sub.length();
					res = sub;
				}
			}
		}
		return res;
	}
	
	  public static boolean isPalindrome2(String s) {
		  int i = 0;
		  int j = s.length()-1;
		  while(i <= j) {
			  if(s.charAt(i) != s.charAt(j)) return false;
			  i++; j--;
		  }
		  return true;
	  }
	
	  public static String addBinary(String a, String b) {

		  String res = "";
	        int carry = 0;
	        int size = a.length()>b.length()?a.length():b.length();
	        int count = 0;
	        for(int i = size-1; i>=0; i--) {
	            int ad = i < a.length()?Character.getNumericValue(a.charAt(i)):Character.getNumericValue(a.charAt(a.length()-1));
	            if(count >= a.length()) ad=0;
	            int bd = i < b.length()?Character.getNumericValue(b.charAt(i)):Character.getNumericValue(b.charAt(b.length()-1));
	            if(count >= b.length()) bd=0;

	            int sum = ad+bd;
	            int toAdd = (sum+carry)>1?(sum+carry-2):(sum+carry);
	            res = Integer.toString(toAdd)+res;
	            carry = (sum+carry)>1?1:0;
	        	count++;

	        }
	        if(carry>0) res="1"+res;
	        return res;
	    }
	
	public static int strStr(String hay, String needle) {
		int result = -1;
		for(int i = 0; i < hay.length(); i++) {
			int start=0;
			int j = i;
			int count = 0;
			while(start < needle.length() && j < hay.length() && needle.charAt(start)==hay.charAt(j)) {
					count++;			    
					start++;
					j++;
					if(count==needle.length()) { result=i; break; }			
			}
		 }
		return result;
	}
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1= new StringBuilder();
        for(int j = 0; j < s.length(); j++){
             if(Character.isLetter(s.charAt(j)) || Character.isDigit(s.charAt(j))){
            sb1.append(s.charAt(j));}
            
        }
        for(int i = s.length()-1; i>=0; i--) {
            if(Character.isLetter(s.charAt(i))|| Character.isDigit(s.charAt(i))){
            sb.append(s.charAt(i));}
        }
        
        return sb1.toString().equalsIgnoreCase(sb.toString());
    }
    public static void main(String[] args) {
       System.out.println(longestPalindrome("acapoljuypopauabccbatre"));
    }
}
