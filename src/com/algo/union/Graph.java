package com.algo.union;

import java.util.HashSet;
import java.util.Set;

public class Graph {
	private final int V;
	private int E;
	private Set<Integer>[] adj;

	public Graph(int v) {
		this.V = v;
		this.E = 0;
		adj = (Set<Integer>[])new Set[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new HashSet<Integer>();
		}
	}
	
	public void addEdge(int v, int w) {
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	public int degree(int v) {
		return adj[v].size();
	}
	
	public int size() {
		return V;
	}
	
	public int edgeSize() {
		return E;
	}
}
