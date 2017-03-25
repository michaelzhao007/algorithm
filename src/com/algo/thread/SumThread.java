package com.algo.thread;

public class SumThread extends Thread{
  int lo;
  int hi;
  int[] arr;
  
  int ans = 0;
  
  SumThread(int[] a, int lo, int hi) {
      this.lo =lo;
      this.hi = hi;
      arr =a;
  }
  
  public void run() {
      for(int i = lo; i < hi; i++) {
          ans+=arr[i];
      }
  }
  
  public static int sum(int[] arr, int num) throws InterruptedException {
      int len  = arr.length;
      int ans = 0;
      SumThread[] ts = new SumThread[num];
      for(int i = 0; i < num; i++) {
          ts[i] = new SumThread(arr, i*len/num, (i+1)*len/num);
          ts[i].start();
      }
      for(int i = 0; i < num; i++) {
          ts[i].join();
          //System.out.println(ts[i].ans);
          ans+=ts[i].ans;
      }
      return ans;
  }
  
  public static void main(String[] args) throws InterruptedException {
     System.out.println(sum(new int[]{7,8,9,10,11},5)); 
   }
}
