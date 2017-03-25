package com.algo.bst;

import java.util.ArrayList;
import java.util.List;

public class Animal {
	 public static int numSquares(int n) {
	        int res  = n;
	        int num  = 2;
            while(num*num<=n) {	       
	            int a = n/(num*num);
	            int b = n%(num*num);
	            res = Math.min(res, a+numSquares(b));
	            ++num;
	        }
	        return res;
	        
	    }
	 
	  public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
	        List<String> result = new ArrayList<String>();
	        if(nums==null||nums.length==0) {
	            if(lower==upper) result.add(lower+"");
	            else result.add((lower)+"->"+upper);
	            return result;
	        }
	        if(nums[0] > lower) {
	            result.add((lower+1)+"->"+(nums[0]-1));
	        }
	        for(int i = 0; i < nums.length-1 && nums[i] <= upper; i++) {
	            if(nums[i] < lower) i++; 
	            else if(nums[i+1]-nums[i] > 2) result.add((nums[i]+1) + "->" + (nums[i+1]-1));
	            else if(nums[i+1]-nums[i] == 2) result.add((nums[i]+1)+""); 
	        }
	        if(nums[nums.length-1] < upper) {
	        	if(nums[nums.length]+1==upper) result.add(upper+"");
	            result.add((nums[nums.length-1]+1)+"->"+(upper));
	        }
	        return result;
	        
	    }
	int age;
	
	public static Animal passAge(Animal ani) {
		ani.age = 10;
		Animal ani2 = ani;
		ani2.age = 20;
		return ani;
	}
	
	public static void main(String[] args) {
		System.out.println(Animal.numSquares(12));
		findMissingRanges(new int[]{}, -3, -1);
	}

}
