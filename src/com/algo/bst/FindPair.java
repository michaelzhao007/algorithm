package com.algo.bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindPair {
	//Find all pairs that have a specified difference among a set of n integers.  
    //[2, 5, 8, 7, 9, 10]
	public static List<List<Integer>> findAllPairs(int[] arr, int target) {
    	Map<Integer, Integer>  map = new HashMap<Integer, Integer>();
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for(int elem: arr) {
    		if(!map.containsKey(elem)) {
    			map.put(target+elem, elem);
    		}
    		else {
    			List<Integer> res = new ArrayList<Integer>();
    			res.add(map.get(elem));
    			res.add(elem);
    			result.add(res);
    			map.put(target+elem, elem);
    		}
    	}
    	return result;
    }
	//input: word_list = ['dog', 'cats', 'sand', 'cat', 'and'], string = "catsanddog"
	//output: [ ['cat', 'sand', 'dog'], ['cats', 'and', 'dog'] ]  
	public static List<List<String>> partition(HashSet<String> set, String str) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Integer>[] dp = (ArrayList<Integer>[])new ArrayList[str.length()+1];
		dp[0] = new ArrayList<Integer>();
		for(int i = 0; i < str.length(); i++) {
			if(dp[i]!=null) {
				for(int j = i+1; j < str.length()+1; j++) {
					if(dp[j]==null&&set.contains(str.substring(i, j))) {
						dp[j] = new ArrayList<Integer>();
						dp[j].add(i);
					}
					else if(set.contains(str.subSequence(i, j))) {
						dp[j].add(i);
					}
				}
			}
		}
		if(dp[dp.length-1]==null) return result;
		List<String> list = new ArrayList<String>();
		getResult(dp, str, result, list, str.length());
		return result;
	}
	
	public static void getResult(List<Integer>[] dp, String str, List<List<String>> result, List<String> list, int idx) {
		if(idx==0) {
			result.add(new ArrayList<String>(list));
			return;
		}
		else {
			for(int from: dp[idx]) {
				list.add(str.substring(from, idx));
				getResult(dp, str, result, list, from);
				list.remove(list.size()-1);
			}
		}
	}
	
	public static boolean canBreakWord(String s, Set<String> dict) {
		Integer[] dp = new Integer[s.length()+1];
		dp[0] = 0;
		for(int i = 0; i < s.length(); i++) {
			for(int j = i+1; j < s.length()+1; j++) {
				if(dp[i]!=null && dict.contains(s.substring(i, j))) {
					dp[j] = i; 
				}
			}
		}
		return dp[dp.length-1] != null;
	}
	
	public static void main(String[] args) {
		/*int[] arr = new int[]{2, 5, 8, 7, 3, 10};
		List<List<Integer>> res = findAllPairs(arr, 3);
		for(List<Integer> list: res) {
			for(Integer elem: list) {
				System.out.print(elem + " ");
			}
			System.out.println();
		}*/
		//['dog', 'cats', 'sand', 'cat', 'and']
		HashSet<String> set = new HashSet<String>();
		set.add("dog");
		set.add("cats");

		set.add("sand");

		set.add("cat");
		set.add("and");
		List<List<String>> res = partition(set, "catsanddog");
		for(List<String> list: res) {
			for(String elem: list) {
				System.out.print(elem + " ");
			}
			System.out.println();
		}
		HashSet<String> dict = new HashSet<String>();
		dict.add("leet");
		dict.add("code");
		dict.add("ja");
		System.out.println(canBreakWord("leetcodeja", dict));

	}
}
