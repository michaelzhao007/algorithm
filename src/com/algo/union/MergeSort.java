package com.algo.union;

public class MergeSort {
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a,aux,0,a.length-1);
	}
	
	public static void sort(Comparable[] a,Comparable[] aux, int lo, int hi) {
		if(hi<=lo) return;
		int mid = lo+(hi-lo)/2;
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		merge(a,aux,lo,mid,hi);
	}
	
	public static void merge(Comparable[] a, Comparable[] aux,int lo, int mid, int hi) {
		for(int i = lo; i < a.length; i++) {
			aux[i] = a[i];
		}
		int k = lo;
		int p = mid+1;
		for(int j = lo; j < aux.length; j++) {
			if(k>mid) a[j] = aux[p++];
			else if(p>hi) a[j] = aux[k++];
			else if(aux[k].compareTo(aux[p]) > 0) a[j] = aux[p++];
			else a[j] = aux[k++];
		}
	}
	
	
	public static void main(String[] args) {
		Integer[] arr = {3,2,5,8,7,4,9};
		MergeSort.sort(arr);
		for(Integer elem: arr) {
			System.out.println(elem);
		}
		
	}

}
