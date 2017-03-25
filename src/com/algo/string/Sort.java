package com.algo.string;

import java.util.Random;

public class Sort {
    
    public static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length];
        sort(arr, aux, 0 , arr.length-1);
    }
    
    public static void quickSort(int[] arr) {
        quickS(arr,0,arr.length-1);
    }
    
    public static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void quickS(int[] arr, int lo, int hi) {
        if(hi<=lo) return;
        int j = partition(arr,lo,hi);
        quickS(arr,lo,j-1);
        quickS(arr,j+1,lo);
    }
    
    public static int partition(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        int pivot = arr[lo];
        while(true) {
            while(arr[++i]<pivot) if(i==hi) break;
            while(arr[--j]>pivot) if(j==lo) break;
            if(i>=j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    public static void sort(int[] arr, int[] aux, int lo, int hi) {
        if(hi<=lo) return;
        int mid  = lo+(hi-lo)/2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid+1, hi);
        merge(arr,aux,lo,mid,hi);
    }
    
    public static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        for(int i = lo; i <= hi; i++) {
            aux[i] = arr[i];
        }
        
        int i = 0;
        int j = mid+1;
        for(int  k = lo; k <= hi; k++) {
            if(i>mid) arr[k] = aux[j++];
            else if(j > hi) arr[k] = aux[i++];
            else if(aux[i] > aux[j]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }
    
    public static void reverseWords(char[] s) {
        reverse(s,0,s.length-1);
        int prevspace = -1;
        for(int i = 0; i < s.length; i++) {
            if(s[i] == ' ') {
                swap(s,prevspace+1,i-1);
                prevspace = i;
            }
        }
    }
    
    public static void reverse(char[] s, int lo, int hi) {
        int i = lo;
        int j = hi;
        while(i<=j) {
            swap(s,i++,j--);
        }
    }
    
    public static void swap(char[]  s, int i , int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
    
    
    public static int random_partition(int[] arr, int start, int end) {
        Random rd = new Random();
        int pivotIdx = start + rd.nextInt(end-start+1);
        int pivot = arr[pivotIdx];
        exch(arr, pivotIdx, start);
        int i = start;
        int j = end;
        while(true) {
            while(arr[++i] <= pivot) if(i==end) break;
            while(arr[--j] >= pivot) if(j==start) break;
            if(i>=j) break;
            exch(arr, i, j);
        }
        exch(arr, start, j);
        return j;
    }
    
    public static int rSelect(int[] arr,int start, int end, int k) {
       if(start==end) return arr[start];
       else if(k==0) return -1;
       else if(start<end) {
           int mid = random_partition(arr,start,end);
           int i = mid-start+1;
           if(i==k) return arr[mid];
           else if(i>k) return rSelect(arr, start, mid-1, k);
           else return rSelect(arr, mid+1, end, k-i);
       }
       else return -1;
    }
    
    public static int partition1(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        int pivot = arr[lo];
        while(true) {
            while(arr[i++] <= pivot) if(i==hi) break;
            while(arr[j--] >= pivot) if(j==lo) break;
            if(i>=j) break;
            exch(arr, i, j);
        }
        exch(arr,lo,j);
        return j;
    }
    
    public static void main(String[] args) {
        int[] arr = new int[]{5,7,3,1,2};
   
       
       System.out.println(rSelect(arr,0,arr.length-1,4));
    }
}
