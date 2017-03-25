package com.algo.union;

import java.util.Arrays;
import java.util.List;

public class TargetSum {
	
	public boolean hasSumTo(int[] numbers, int target) {
		Arrays.sort(numbers);
		boolean flag = true;
		int i = 0;
		int j = numbers.length-1;
		while(flag) {
			if(i==j) flag=false;
			else if(numbers[i]+numbers[j]>target) {j--;}
			else if(numbers[i]+numbers[j]<target) {i++;}
			else if(numbers[i]+numbers[j]==target) return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		TargetSum target = new TargetSum();
		int[] numbers={2,5,4,3,1,9};
		System.out.println(target.hasSumTo(numbers, 117));
	}

}
