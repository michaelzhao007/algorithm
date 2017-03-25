package com.algo.bst;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;



public class AirlineCrews {
    private FastScanner in;
    private PrintWriter out;
    static int[] result;
    static int size = 0;
    
    static Edge[] path;
    int num;


    public static void main(String[] args) throws IOException {
       // new AirlineCrews().solve();
        List<Iterator<Integer>> list = new ArrayList<Iterator<Integer>>();
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);


        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(5);
        l2.add(6);
        l2.add(7);
        Iterator<Integer> it1 = l1.iterator();
        Iterator<Integer> it2 = l2.iterator();
        list.add(it1);
        list.add(it2);

        printElem(list);
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        boolean[][] bipartiteGraph = readData();
        int[] matching = findMatching(bipartiteGraph);
        writeResponse(matching);
        out.close();
    }

    boolean[][] readData() throws IOException {
        int numLeft = in.nextInt();
        int numRight = in.nextInt();
        
        boolean[][] adjMatrix = new boolean[numLeft][numRight];
        for (int i = 0; i < numLeft; ++i) {
            for (int j = 0; j < numRight; ++j) {
                adjMatrix[i][j] = (in.nextInt() == 1);
                if(adjMatrix[i][j]) num++;
            }
         }
        return adjMatrix;
    }
    
    public static boolean hasPath(FlowGraph graph, int s, int t) {
        if(graph==null) return false;
        boolean[] visited = new boolean[graph.graph.length];
        path =  new Edge[graph.graph.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        while(queue.size() > 0 && !visited[t]) {
            int top = queue.remove();
            if(graph.neighbors(top)!=null) {
            for(Edge neighbor: graph.neighbors(top)) {
                int to = neighbor.other(top);
                if(neighbor.residualCapacityTo(to) > 0) {
                    if(!visited[to]) {
                        queue.add(to);
                        visited[to] = true;
                        path[to] = neighbor;
                    }
                }             
            }
            }
         }
        return visited[t];
     }
    
    
    public static void printElem(List<Iterator<Integer>> list) {
        for(int i = 0; i <list.size(); i++) {
            while(list.get(i).hasNext()) {
                System.out.println(list.get(i).next());
                i++;
                if(i==list.size()) i=0;
            }
        }
    }

    static class Edge {
        int from, to, capacity, flow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }
        
        public int other(int vertex) {
            if      (vertex == from) return to;
            else if (vertex == to) return from;
            else throw new IllegalArgumentException("Illegal endpoint");
        }
        
        public double residualCapacityTo(int vertex) {
            if      (vertex == from) return flow;              // backward edge
            else if (vertex == to) return capacity - flow;   // forward edge
            else throw new IllegalArgumentException("Illegal endpoint");
        }
        
        public void addResidualFlowTo(int vertex, double delta) {
            if      (vertex == from) flow -= delta;           // backward edge
            else if (vertex == to) flow += delta;           // forward edge
            else throw new IllegalArgumentException("Illegal endpoint");
            if (Double.isNaN(delta)) throw new IllegalArgumentException("Change in flow = NaN");
            if (!(flow >= 0.0))      throw new IllegalArgumentException("Flow is negative");
            if (!(flow <= capacity)) throw new IllegalArgumentException("Flow exceeds capacity");
        }
    }

    /* This class implements a bit unusual scheme to store the graph edges, in order
     * to retrieve the backward edge for a given edge quickly. */
    static class FlowGraph {
        /* List of all - forward and backward - edges */
        private List<Edge>[] edges;

        /* These adjacency lists store only indices of edges from the edges list */
       private List<Integer>[] graph;

        public FlowGraph(int n) {
             this.graph = (ArrayList<Integer>[])new ArrayList[n];
             for (int i = 0; i < n; ++i)
                this.graph[i] = new ArrayList<>();
            this.edges = (ArrayList<Edge>[])new ArrayList[n];
        }

        public void addEdge(int from, int to, int capacity) {
            /* Note that we first append a forward edge and then a backward edge,
             * so all forward edges are stored at even indices (starting from 0),
             * whereas backward edges are stored at odd indices. */
           // Edge forwardEdge = new Edge(from, to, capacity);
          //  Edge backwardEdge = new Edge(to, from, 0);
            //graph[from].add(edges.size());
           // edges.add(forwardEdge);
            //graph[to].add(edges.size());
            if(edges[from] == null) edges[from] = new ArrayList<Edge>();
            if(edges[to] == null) edges[to] = new ArrayList<Edge>();
            Edge edge = new Edge(from, to, capacity);
            edges[from].add(edge);
            edges[to].add(edge);
            //edges[to].add(new Edge(from, to, capacity));

            //edges.add(new Edge(from, to, capacity));
        }
        
        public Iterable<Edge> neighbors(int v) {
            return edges[v];
        }

        public int size() {
            return graph.length;
        }

        //public List<Integer> getIds(int from) {
        //    return graph[from];
        //}


        /*public void addFlow(int id, int flow) {
             To get a backward edge for a true forward edge (i.e id is even), we should get id + 1
             * due to the described above scheme. On the other hand, when we have to get a "backward"
             * edge for a backward edge (i.e. get a forward edge for backward - id is odd), id - 1
             * should be taken.
             *
             * It turns out that id ^ 1 works for both cases. Think this through! 
            edges.get(id).flow += flow;
            //edges.get(id ^ 1).flow -= flow;
        }*/
    }
    
    
    
    private static int maxFlow(FlowGraph graph, int from, int to, int numleft, int numright) {
        int flow = 0;
        /* your code goes here */
        while(hasPath(graph, from, to)) {
            int path_flow = Integer.MAX_VALUE;

            System.out.println("=========================");
            for(int v = to; v != from; v = path[v].other(v)) {
                if(path[v].from!=v) {
                    System.out.println(path[v].from + "->" + v);
                    if(path[v].from > 0 && path[v].from < numleft && size<numleft) result[size++] = path[v].from;
                }
                else break;
               }
            System.out.println("=========================");

            
            for(int v = to; v != from; v = path[v].other(v)) {
               path_flow = (int) Math.min(path_flow, path[v].residualCapacityTo(v));
            }
            
            for(int v = to; v != from; v =  path[v].other(v)) {
                path[v].addResidualFlowTo(v, path_flow); 
            }
            


            flow += path_flow;
        }
        return flow;
    }
    
    private int[] findMatching(boolean[][] bipartiteGraph) {
      int m = bipartiteGraph.length;
      int n = bipartiteGraph[0].length;
      int[] matchL = new int[m];
      int[] matchR = new int[n];
      boolean[] seen = new boolean[n];
      Arrays.fill(matchL, -1);
      Arrays.fill(matchR, -1);
      for(int i = 0; i < m; i++) {
          Arrays.fill(seen, false);
          bpm(i, bipartiteGraph, n, matchL, matchR, seen);
      }
      List<Integer> result = new ArrayList<Integer>();
      for(int j = 0; j < m; j++) {
           if(matchL[j]!=-1) matchL[j]+=1;
           result.add(matchL[j]);
          
      }
      int[] res = new int[result.size()];
      for(int i = 0; i < result.size(); i++) {
          res[i] = result.get(i);
      }
      return res;
    }
    
    private boolean bpm(int u, boolean[][] graph, int n, int[] matchL, int[] matchR, boolean[] seen) {
        for(int v = 0; v < n; v++) {
            if(!graph[u][v] || seen[v]) continue;
            seen[v] = true;
            if(matchR[v] == -1 || bpm(matchR[v], graph, n, matchL, matchR, seen)) {
                matchL[u] = v;
                matchR[v] = u;
                return true;
            }
        }
        return false;
    }

    private int[] findMatchingv2(boolean[][] bipartiteGraph) {
        // Replace this code with an algorithm that finds the maximum
        // matching correctly in all cases.
        FlowGraph graph = new FlowGraph(2+bipartiteGraph.length+bipartiteGraph[0].length);
        for(int i = 1; i <= bipartiteGraph.length; i++) {
            graph.addEdge(0, i, 1);
        }
        for(int i = 0; i < bipartiteGraph.length; i++) //num of flights 
        {
            for(int j = 0; j < bipartiteGraph[0].length; j++) {
                if(bipartiteGraph[i][j] == true) {
                    graph.addEdge(i+1, j+1+bipartiteGraph.length, 1);
                }
            }
        }
   
        
        int start = 1+bipartiteGraph.length;
        int total = 1+bipartiteGraph.length+bipartiteGraph[0].length;
       
        for(int i = start; i < total; i++) {
            graph.addEdge(i, total, 1);
        }
        
        
        int numLeft = bipartiteGraph.length;
        int numRight = bipartiteGraph[0].length;

        result = new int[numLeft];
        Arrays.fill(result, -1);
        System.out.println("maxflow: " +maxFlow(graph, 0, graph.size()-1, numLeft, numRight));
        
        return result;
    }

    private void writeResponse(int[] matching) {
        for (int i = 0; i < matching.length; ++i) {
            if (i > 0) {
                out.print(" ");
            }
            if (matching[i] == -1) {
                out.print("-1");
            } else {
                out.print(matching[i]);
            }
        }
        out.println();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
