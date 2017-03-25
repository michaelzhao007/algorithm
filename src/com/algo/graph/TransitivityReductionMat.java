package com.algo.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * This class is about showing to find transition closure and transition
 * reduction. Using both Hsu's algorithm and topological sort methods.
 * 
 * @author michaelzhao
 *
 */

public class TransitivityReductionMat {
	public static void main(String[] args) throws IOException {
		TransitivityReductionMat tr = new TransitivityReductionMat();
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		int count = -1;
		int totalLine = 0;
		Graph g = null;
		String line;
		while ((line = input.readLine()) != null) {
			if (!line.isEmpty()) {
				String[] tokens = line.split(" ");
				if (count == -1) {
					totalLine = Integer.parseInt(tokens[0]);
					g = tr.new Graph(totalLine);
				} else {
					for (String token : tokens) {
						g.addEdge(count, Integer.parseInt(token));
					}
				}
			}

			count++;
			if (count == totalLine) {
				g.matrix = g.getPathMatrix();
				g.transitivityReduction();
				g.printOutput();

			}
		}
	}

	class Graph {
		int[][] matrix;
		int[][] pathmatrix;
		boolean[] visited;
		int size;
		private Stack<Integer> postorder;

		Graph(int num) {
			matrix = new int[num][num];
			size = num;
			visited = new boolean[num];
			pathmatrix = new int[num][num];
			postorder = new Stack<Integer>();
		}

		public int[][] transitivityReduction() {
			for (int j = 0; j < size(); j++)
				for (int i = 0; i < size(); i++)
					if (pathmatrix[i][j] == 1)
						for (int k = 0; k < size(); k++)
							if (pathmatrix[j][k] == 1)
								pathmatrix[i][k] = 0;
			return pathmatrix;
		}

		public int[][] getPathMatrix() {
			for (int i = 0; i < size(); i++)
				for (int j = 0; j < size(); j++)
					pathmatrix[i][j] = matrix[i][j];
			for (int k = 0; k < size(); k++)
				for (int i = 0; i < size(); i++)
					for (int j = 0; j < size(); j++)
						if (pathmatrix[i][k] == 1 && pathmatrix[k][j] == 1)
							pathmatrix[i][j] = 1;
			return pathmatrix;
		}

		public List<Integer> adj(int v) {
			List<Integer> adj = new ArrayList<Integer>();
			for (int i = 0; i < size(); i++) {
				if (matrix[v][i] == 1) {
					adj.add(i);
				}
			}
			return adj;
		}

		public void printOutput() {
			for (int i = 0; i < size(); i++) {
				for (int j = 0; j < size(); j++) {
					if (pathmatrix[i][j] != 0) {
						System.out.print(j + " ");
					}
				}
				System.out.println();
			}
		}

		public void printGraph() {
			System.out.println("=========================");
			for (int i = 0; i < size(); i++) {
				for (int j = 0; j < size(); j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
		}

		public int size() {
			return size;
		}

		public void searchGraphTopSort() {
			visited = new boolean[size()];
			int co = 0;
			int[] arr = new int[size()];
			for (int s : postorder) {
				arr[co] = s;
				co++;
			}
			for (int i = 0; i < size(); i++) {
				int s = arr[i];
				if (!visited[s]) {
					checkPath(s, s);
				}
			}
		}

		public void searchGraph() {
			for (int i = 0; i < size(); i++) {
				for (int j = 0; j < size(); j++)
					if (!visited[i]) {
						checkAllPath(i, j);
					}
			}
		}

		public void checkPath(int oriNum, int v1) {
			for (Integer adjNum : adj(v1)) {
				if (visited[adjNum] != true) {
					visited[adjNum] = true;
					checkPath(oriNum, adjNum);
					// postorder.push(adjNum);
				} else {
					if (hasEdge(oriNum, adjNum))
						matrix[oriNum][adjNum] = 0;
				}
			}
		}

		public void checkAllPath(int v1, int v2) {
			/*
			 * preorder[v1] = clock; clock++;
			 */
			// visited[v1] = true;
			visited[v1] = true;
			for (Integer adjNum : adj(v1)) {
				if (visited[adjNum] != true) {
					checkAllPath(adjNum, v2);
				}
			}
			postorder.push(v1);
		}

		public void printPostOrder() {
			for (Integer a : postorder) {
				System.out.print(a + " ");
			}
		}

		public boolean hasPath(int v1, int v2) {
			return visited[v2];
		}

		public boolean hasEdge(int v1, int v2) {
			return matrix[v1][v2] == 1;
		}

		public void addEdge(int v1, int v2) {
			matrix[v1][v2] = 1;
		}

	

	}
}
