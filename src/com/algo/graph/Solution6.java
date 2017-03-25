package com.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution6 {
	   public static List<String> wordBreak2(String s, Set<String> wordDict) {
	        List<Integer>[] dp = (ArrayList<Integer>[])new ArrayList[s.length()+1];
	        dp[0]=new ArrayList<Integer>();
	        for(int i = 0; i <= s.length(); i++) {
	            if(dp[i]!=null) {
	             for(int j = i+1; j <= s.length(); j++) {
	                if(dp[j]==null && wordDict.contains(s.substring(i, j))) {
	                   dp[j] = new ArrayList<Integer>();
		               dp[j].add(i);
	                }
	                else if(wordDict.contains(s.substring(i, j))) {
	                  dp[j].add(i);
	                }
	             }
	          }
	        }
	        List<String> result = new ArrayList<String>();
	        if(dp[dp.length-1]==null) return result;
	        List<String>  list  = new ArrayList<String>();
	        buildSolution(result, list, dp, s, s.length());
	        return result;
	    }
	    
	    public static void buildSolution(List<String> result, List<String> res, List<Integer>[] dp, String s, int idx) {
	        if(idx==0) {
	        	String str = "";
	        	for(int i = 0; i < res.size(); i++) {
	        		if(!str.isEmpty()) {
	        		str = res.get(i)+" "+str;
	        		}
	        		else {
	        			str = res.get(i);	        			
	        		}
	        	}
	            result.add(str);
	            return;
	        }
	        else {
	            for(Integer from: dp[idx]) {
	                res.add(s.substring(from, idx));
	                buildSolution(result, res, dp, s, from);	            
	                res.remove(res.size()-1);
	            }
	        }
	    }
	    
    public static double sqrt(double x) {
        double init = 0;
        if(x >= 1) {
        return helper(x, init, x);  
        }
        else {
        return helperLessOne(x, init, x);
        }
    }
    
    public static double helperLessOne(double x, double start, double end) {
    	double mid = (start+(end-start)*2);
    	if(Math.abs(mid*mid-x)<0.0001) return mid;
         else if(mid*mid>x) {
            return helper(x, start, mid);   
          }
          else { 
            return helper(x, mid, end);
          }
    }

    public static double helper(double x, double start, double end) {
          double mid = (start+(end-start)/2);
          if(Math.abs(mid*mid-x)<0.0001) return mid;
          else if(mid*mid>x) {
             return helper(x, start, mid);   
           }
           else { 
             return helper(x, mid, end);
           }
    }

	
	public static boolean wordBreak(String s, Set<String> wordDict) {
        return wordBreakHelper(s, wordDict, 0);
    }
	public static boolean wordBreakHelper(String s, Set<String> dict, int start) {
		if(start == s.length()) return true;
		for(String a: dict) {
			int len = a.length();
			int end = start + len;
			if(end > s.length()) continue;
			if(s.substring(start, start+len).equals(a)) 
				if(wordBreakHelper(s, dict, start+len))
					return true;
		}
		return false;
	}
	
	public static boolean wordBreakDP(String s, Set<String> dict) {
		boolean[] t = new boolean[s.length()+1];
		t[0] = true;
		for(int i = 0; i < s.length(); i++) {
			if(!t[i]) continue;
			for(String a: dict) {
				int len = a.length();
				int end = i + len;
				if(end > s.length()) continue;
				if(t[end]) continue;
				if(s.substring(i, end).equals(a)) t[end] = true;
			}
		}
		return t[s.length()];
	}
	
	 public static int maxProfit(int[] prices) {
	        int day = 0;
	        boolean buy = false;
	        int profit = 0;
	        int coolday = -1;
	        for(int i = 0; i < prices.length-1; i++) {
	            if(prices[i+1] > prices[i] && !buy && i!=coolday) {
	                day = i;
	                buy = true;
	            }
	            else if(prices[i+1] <= prices[i] && buy) {
	                buy = false;
	                profit = prices[i]-prices[day];
	                coolday = i+1;
	            }
	        }
	        return profit;
	        
	    }
	 
	 
	
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("cat");
		set.add("cats");
		set.add("and");
		set.add("sand");
		set.add("dog");
		System.out.println(wordBreak2("catsanddog", set));
	}
}
