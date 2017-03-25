package com.algo.bst;

public class NumArray {
    int[] tree;
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
        tree = new int[nums.length*4-1];
        buildTree(tree, 0, nums.length-1, 0);
        
    }
    
    public void buildTree(int[] tree, int lo, int hi, int po) {
        if(lo==hi) { tree[po] = nums[lo]; return; }
        int mid = (lo+hi)/2;
        buildTree(tree, lo, mid, 2*po+1);
        buildTree(tree, mid+1, hi, 2*po+2);
        tree[po] = tree[2*po+1]+tree[2*po+2];
    }

    public int sumRange(int i, int j) {
        return query(i, j, 0, nums.length-1, 0);
    }
    
    public int query(int i, int j, int lo, int hi, int pos) {
        if(i<=lo && j >= hi) return tree[pos];
        if(i>hi || j < lo) return 0;
        int mid = (lo+hi)/2;
        return query(i, j, lo, mid, 2*pos+1)+query(i,j, mid+1, hi, 2*pos+2);
    }
    
    public static void main(String[] args) {
    	int[] a = new int[] {-2, 0, 3, -5, 2, -1};
		NumArray arr = new NumArray(a);
		System.out.println(arr.sumRange(0, 2));
		System.out.println(arr.sumRange(2, 5));
		System.out.println(arr.sumRange(0, 5));
	}
}