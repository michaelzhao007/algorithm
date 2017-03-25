package com.algo.union;

public class Factorial {
	
	public int factorial(int n) {
		if(n==0) return 1;
		return factorial(n-1)*n;	
	}
	
	public int factorial2(int n) {
		int sum = 1;
		for(int i = 1; i <=n; i++) {
			sum*=i;
		}
		return sum;
	}
	

	
	
	public static void main(String[] args) {
		Factorial fa = new Factorial();
		System.out.println(fa.factorial(3));
	}

}
