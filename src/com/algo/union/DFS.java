package com.algo.union;

public class DFS {
	private boolean marked[];
	private Graph g;
	public DFS(Graph g) {
		this.g = g;
		marked = new boolean[g.size()];
	}
	public void dfs(int v) {
		marked[v] = true;
		System.out.println("visited: " + v);
		for(int w : g.adj(v)) {
			if(!marked[w]) {
				dfs(w);
			}
		}
	}
	
	public void alldfs() {
		for(int i = 0; i < g.edgeSize(); i++) {
			dfs(i);
		}
	}
	public static void main(String[] args) {
		Graph g = new Graph(20);
		g.addEdge(1,2);
		g.addEdge(1, 3);
		g.addEdge(3, 5);
		g.addEdge(5, 7);
		DFS d = new DFS(g);
		d.alldfs();
	}

}
