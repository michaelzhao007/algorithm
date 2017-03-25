package com.algo.union;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prog3 {
  class UnionFind {
	  private int[] id;
	  private int[] score;
	  private int count;
	  private int size;
	  private int contribution;
	  
	  public UnionFind(int size) {
		  count = size;
		  contribution = 0;
		  id = new int[size];
		  score = new int[size];
		  for(int i = 0; i < size; i++) {
			  score[i] = 1;
		      id[i] = i;
		  }
		  this.size = size;
	  }
	  
	  public int size() {
		  return size;
	  }
	  
	  public int count() { 
		  return count;
	  }
	  
	  private int find(int p) {
		  int root = p;
		  while(root!=id[root]) root = id[root];
		  while(p!=root) {
			  int newp = id[p];
			  id[p] = root;
			  p = newp;
		  }
		  return root;
	  }
	  
	  private int max(int p) {
		  int max = 0;
		  while(p!=id[p]) {
			  if(score[p] > max) {
				  max = score[p];
			  }
			  p=id[p];
		  }
		  if(score[p] > max) {
			  max = score[p];
		  }
		  return max;
	  }
	  
	  public boolean connected(int p, int q) {
		  return find(p) == find(q);
	  }
	  
	  public void contribute(int i) {
		  contribution+=score[i];
		  score[i] = 0;
	  }
	  
	  public void addMaxToWinner(int p, int number) {
		  int set = find(p);
		  for(int i = 0; i < size(); i++) {
			  if(find(i)==set) {
				  score[i]+=number;
			  }
		  }
	  }
	  
	  public void totalContribution() {
		  System.out.println(contribution);
	  }
	  
	  public void unionWinner(int p, int q) {
		  int rootP = find(p);
		  int rootQ = find(q);
		  if (rootP == rootQ) return;
		  else{
		  addMaxToWinner(p,max(q));
		  id[rootQ] = rootP;
		  }
		  count--;
	  }
	  
	  public void printCorp() {
		  for(int i = 0; i < size; i++) {
			  System.out.println(i + ": " + find(i));
		  }
	  }
	  public void printScore() {
		  for(int i = 0; i < size; i++) {
			  System.out.println(i + ": " + score[i]);
		  }
	  }
  }
  
  public static void main(String[] args) throws NumberFormatException, IOException {
	Prog3 prog = new Prog3();
	UnionFind uf = null; 
/*	uf = prog.new UnionFind(10);
	uf.unionWinner(0, 1);
	uf.unionWinner(1, 3);
	uf.unionWinner(4, 5);
	uf.unionWinner(3, 2);
	System.out.println(uf.find(2));
	uf.printScore();*/
	BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));
	int count = -1;
	int totalPeople = 0;
	int totalRounds = 0;
	int totalContrib = 0;
	int totalLines = 0;
	String line;
	while ((line = input.readLine()) != null) {
		if (!line.isEmpty()) {
			String[] tokens = line.split(" ");
			if (count == -1) {
				totalPeople = Integer.parseInt(tokens[0]);
				totalRounds = Integer.parseInt(tokens[1]);
				totalContrib = Integer.parseInt(tokens[2]);
				totalLines = totalRounds + totalContrib;
				uf = prog.new UnionFind(totalPeople);
			} else {
				
                  if(tokens.length == 1) {
                	  uf.contribute(Integer.parseInt(tokens[0]));
                      uf.printScore();
                  }
                  else {
                	  uf.unionWinner(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                      System.out.println("=====max=====");
                      System.out.println(uf.max(Integer.parseInt(tokens[1])));
                	  System.out.println("====="+"corp======");
                	  uf.printCorp();
                	  System.out.println("====score====");
                      uf.printScore();
                  }
				
			}
		}

		count++;
		if (count == totalLines) {
			uf.totalContribution();
		}
	}

  }
}
