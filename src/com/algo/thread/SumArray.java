package com.algo.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumArray extends RecursiveTask<Integer>{
    int lo;
    int hi;
    int[] arr;
    SumArray(int[] a, int l, int h) {
        this.lo = l;
        this.arr = a;
        this.hi = h;
    }
    @Override
    protected Integer compute() {
        if(hi-lo<2) {
            int ans = 0;
            for(int i = lo; i < hi; i++ ){
                ans+=arr[i];
            }
            return ans;
        }
        else {
            SumArray left = new SumArray(arr, lo, (lo+hi)/2);
            SumArray right = new SumArray(arr, (lo+hi)/2, hi);
            left.fork();
            int rightAns = right.compute();
            int leftAns = left.join();
            return leftAns+rightAns;
        }
    }
    
      static final ForkJoinPool fjpool = new ForkJoinPool();
       
    static int sum(int[] arr) {
        return fjpool.invoke(new SumArray(arr, 0, arr.length));
    }
    
    public static void main(String[] args) {
        System.out.println(sum(new int[]{1,2,3,4}));
    }
}
