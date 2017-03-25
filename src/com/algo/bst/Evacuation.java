package com.algo.bst;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Evacuation {
    private static FastScanner in;
    static Edge[] path;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();

        FlowGraph graph = readGraph();
        System.out.println(maxFlow(graph, 0, graph.size() - 1));
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
    
    
    
    private static int maxFlow(FlowGraph graph, int from, int to) {
        int flow = 0;
        /* your code goes here */
        while(hasPath(graph, from, to)) {
            int path_flow = Integer.MAX_VALUE;
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
    
    static Edge getEdge(List<Edge> edges, int v) {
        for(Edge edge: edges) {
            if(edge.to == v) return edge;
        }
        return null;
    }
    
    static FlowGraph readGraph() throws IOException {
        int vertex_count = in.nextInt();
        int edge_count = in.nextInt();
        FlowGraph graph = new FlowGraph(vertex_count);

        for (int i = 0; i < edge_count; ++i) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, capacity = in.nextInt();
            graph.addEdge(from, to, capacity);
        }
        return graph;
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
            if(edges[from] == null) edges[from] = new ArrayList<Evacuation.Edge>();
            if(edges[to] == null) edges[to] = new ArrayList<Evacuation.Edge>();
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
