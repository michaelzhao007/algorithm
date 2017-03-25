package com.algo.thread;

public class SumThreadEx extends Thread{
    int lo;
    int hi;
    int[] arr;
    
    int ans = 0;
    
    SumThreadEx(int[] a, int lo, int hi) {
        this.lo =lo;
        this.hi = hi;
        arr =a;
    }
    
    public void run() {
     if(hi-lo < 2)
         for(int i = lo; i < hi; i++)
             ans+=arr[i];
     else {
         SumThreadEx left= new SumThreadEx(arr, lo, (hi+lo)/2);
         SumThreadEx right= new SumThreadEx(arr, (hi+lo)/2, hi);
         left.start();
         right.start();
         try {
            left.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         try {
            right.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         ans = left.ans+right.ans;

     }
    }
    
    public static int sum(int[] arr) {
        SumThreadEx t= new SumThreadEx(arr, 0, arr.length);
        t.run();
        return t.ans;
    }
    
    public static void main(String[] args) {
        System.out.println(sum(new int[]{1,2,3,4,5}));
    }
}
