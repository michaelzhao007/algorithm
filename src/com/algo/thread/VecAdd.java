package com.algo.thread;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class VecAdd extends RecursiveAction{
    int lo;
    int hi;
    int[] res;
    int[] arr1;
    int[] arr2;
    VecAdd(int l, int h , int[] r, int[] a1, int[] a2) {
        this.lo=l;
        this.hi=h;
        this.res=r;
        this.arr1=a1;
        this.arr2=a2;
    }
    @Override
    protected void compute() {
        // TODO Auto-generated method stub
        if(hi-lo < 2) {
            for(int i = lo; i <hi;i++)
             res[i] = arr1[i] +arr2[i];
        } 
        else{
            int mid =(hi+lo)/2;
            VecAdd left = new VecAdd(lo, mid, res, arr1, arr2);
            VecAdd right = new VecAdd(mid, hi, res, arr1, arr2);
            left.fork();
            right.compute();
            left.join();
        }
    }
    static final ForkJoinPool pool = new ForkJoinPool();
    static int[] add(int[] arr1, int[] arr2) {
        int[] ans =new int[arr1.length];
        pool.invoke(new VecAdd(0, arr1.length, ans, arr1, arr2));
        return ans;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int len = sc.nextLine().split(" ").length;
        for(int t = 0; t < n; t++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
        }
    
        Scanner sc1 = new Scanner(System.in);
        int n1 = Integer.parseInt(sc1.nextLine());
        String[] firstrow = sc1.nextLine().split(" ");
        int colSize = firstrow.length;
        int[][] arr = new int[n1][colSize];

        for(int i = 0; i < colSize; i++) {
            arr[0][i] = Integer.parseInt(firstrow[i]);
        }
        
        for(int t = 1; t < n1; t++) {
            for(int t1 = 0; t1 < colSize; t1++) {
                 arr[t][t1] = sc1.nextInt(); 
            }
        }
    }

}
