package com.algo.bst;
import java.io.*;
import java.util.*;

public class SetRangeSum {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Splay tree implementation

    // Vertex of a splay tree
    class Vertex {
        int key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
        if (v.left != null) {
            v.left.parent = v;
        }
        if (v.right != null) {
            v.right.parent = v;
        }
    }

    void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) {
            return;
        }
        Vertex grandparent = v.parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }
    


    void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given vertex and returns the new root.
    Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    class VertexPair {
        Vertex left;
        Vertex right;
        VertexPair() {
        }
        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }
        root = splay(last);
        update(root);
        return new VertexPair(next, root);
    }

    VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
        root = findAndRoot.right;
        result.right = findAndRoot.left;
        if (result.right == null) {
            result.left = root;
            return result;
        }
        result.right = splay(result.right);
        result.left = result.right.left;
        result.right.left = null;
        if (result.left != null) {
            result.left.parent = null;
        }
        update(result.left);
        update(result.right);
        return result;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right = splay(right);
        right.left = left;
        update(right);
        return right;
    }

    // Code that uses splay tree to solve the problem

    Vertex root = null;

    void insert(int x) {
        Vertex left = null;
        Vertex right = null;
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        left = leftRight.left;
        right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null);
        }
        root = merge(merge(left, new_vertex), right);
    }
    Vertex del(Vertex node) {
        if(node.right == null) {
            if(node.left != null) {
             node.key = node.left.key;
             node.left.parent = null;
             node.left = null;
            }
            else {
               node = null;
            }
        }
        else {
            Vertex v = next(node);
            v.left = null;
            node.key = v.key;
            v = v.right;
        }
        update(node);
        return node;
    }
    
    Vertex next(Vertex node) {
       if(node.right != null) return leftDes(node.right);
       else return rightAns(node);
    }
    
    
    Vertex leftDes(Vertex node) {
        if(node.left==null) return node;
        else return leftDes(node.left);
    }
    
    Vertex rightAns(Vertex node) {
        if(node.parent == null) return node;
        if(node.key < node.parent.key) return node.parent;
        else return rightAns(node.parent);
    }
    void erase(int x) {
       VertexPair pair = split(root, x);
       if(pair.right!=null) {
           if(pair.right.key==x) {
               root = merge(pair.left, pair.right.right);
           }
           else {
               root = merge(pair.left, pair.right);
           }
           if(root!=null) {
           update(root);
            root.parent = null;
           }
       }
       else {
           root = merge(pair.left, null);
       }
    }
         /*  if(middle!=null) {
               if(middle.key==x) {
                   VertexPair middleright = split(middle, x+1);
                   middleright.left = null;
                   root = merge(left, middleright.right);
                   if(root!=null) {
                   root.parent = null;
                   update(root);
                   }
               }
           }*/
       
      
    //}
    
    void erase1(int x) {
        VertexPair pair = find(root, x);
        if(pair.right!= null) {
        if(pair.right.key == x) {
        if(pair.left != null) {
        root = splay(pair.left);
        if(root.left == null) root= root.right;
        else {
           Vertex v = root.right;
           root = root.left;
           root.right = v;
           update(root.right);
        }
       }
        if(root != null) {
            root.parent = null;
        }
        update(root);
        }
        }
     }

    boolean find(int x) {
        // Implement find yourself
         VertexPair pair = find(root, x);
         root = splay(pair.right);
         update(root);
         if(root!=null) {
           if(root.key == x) return true;
         }
 
         return false;

        
    }
    
    long getSum(Vertex v) {
       if(v==null) return 0;
       return v.key%MODULO+getSum(v.left)+ getSum(v.right); 
    }
    
    

    long sum(int from, int to) {
        VertexPair leftMiddle = split(root, from);
        Vertex left = leftMiddle.left;
        long ans = 0;

        Vertex middle = leftMiddle.right;

        VertexPair middleRight = split(middle, to + 1);
        middle = middleRight.left;
        Vertex right = middleRight.right;
        // Complete the implementation of sum
        if(middle!=null) ans+=middle.sum;
        
/*        if(left!=null) ans+=left.sum;
        if(right!=null) ans+=right.sum;*/
        root = merge(merge(left,middle), right);
        update(root);
        return ans;
    }

    void solve() throws IOException {
        int n = nextInt();
        int last_sum_result = 0;
        for (int i = 0; i < n; i++) {
            char type = nextChar();
            switch (type) {
                case '+' : {
                    int x = nextInt();
                    insert((x + last_sum_result) % MODULO);
                } break;
                case '-' : {
                    int x = nextInt();
                    erase((x + last_sum_result) % MODULO);
                } break;
                case '?' : {
                    int x = nextInt();
                    out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
                } break;
                case 's' : {
                    int l = nextInt();
                    int r = nextInt();
                    long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
                    out.println(res);
                    last_sum_result = (int)(res % MODULO);
                }
            }
        }
    }
    public static final int MODULO = 1000000001;

    void solve1() throws IOException {
        int n = 1000000;
        int last_sum_result = 0;
        int last_sum_resultbrute = 0;
        //i++;
        Random ra = new Random();
           
            int temp = 0;
            int temp1 = 0;
            
         /*   long sum = sum(0, MODULO);
            long sumbru = brutesum(0, MODULO);
            System.out.println(sum);
            System.out.println(sumbru);
            System.out.println(sum==sumbru);*/
        for (int i = 0; i < n; i++) {
             int num = Math.abs(ra.nextInt(MODULO));
             int typeram = ra.nextInt((4-1)+1)+1;
             char type1 = 0;
             if(typeram==1) {
                type1='+';
             }
             else if(typeram==2) {
                type1 ='-';
             }
             else if(typeram==3) {
                type1='?';
             }
             else if(typeram==4) {
                type1='s';
             }
             else {
                type1 = '?';
             }
             
             int l = Math.abs(ra.nextInt(MODULO));
             long r = Math.abs(ra.nextInt(MODULO))+l;
             if(r>Integer.MAX_VALUE) r=MODULO-1;
             
            char type = type1;
            switch (type) {
                case '+' : {
                    int x = num;
                    insert((x + last_sum_result) % MODULO);
                    bruteinsert((x + last_sum_resultbrute) % MODULO);
                } break;
                case '-' : {
                    int x = num;
                    erase((x + last_sum_result) % MODULO);
                    bruteerase((x + last_sum_resultbrute) % MODULO);

                } break;
                case '?' : {
                    int x = num;
                    //out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
                    //out.println(brutefind((x + last_sum_resultbrute) % MODULO) ? "Found" : "Not found");

                } break;
                case 's' : {
 /*                   int l = nextInt();
                    int r = nextInt();*/
                    temp = last_sum_result;
                    temp1 = last_sum_resultbrute;
                    long res = sum((l + last_sum_result) % MODULO, ((int)r + last_sum_result) % MODULO);
                    //out.println(res);
                    last_sum_result = (int)(res % MODULO);
                    long resbru = brutesum((l + last_sum_resultbrute) % MODULO, ((int)r + last_sum_resultbrute) % MODULO);
                    //out.println(resbru);
                    last_sum_resultbrute = (int)(resbru % MODULO);
                }
            }
            
   
        
            if(last_sum_resultbrute!=last_sum_result) {
                 /*  bru = brutesum(0, MODULO);
                   sumtree = sum(0, MODULO);*/
                System.out.println((l + temp) % MODULO);
                System.out.println(((int)r + temp) % MODULO);
                long res1 = sum((l + temp) % MODULO, ((int)r + temp) % MODULO);
                long resbru1 = brutesum((l + temp1) % MODULO, ((int)r + temp1) % MODULO);
                long res2 = sum((l + temp) % MODULO, ((int)r + temp) % MODULO);
                long resbru2 = brutesum((l + temp1) % MODULO, ((int)r + temp1) % MODULO);
    

                   System.out.println(res1);
                   System.out.println(resbru1);
                printTree();
                bruteprint();
                break;
                
            }
        }
    
    }
    

    void bruteSolve(int num, char type1) throws IOException {
        int n = num;
        int last_sum_resultbrute = 0;
        for (int i = 0; i < n; i++) {
            char type = type1;
            switch (type) {
                case '+' : {
                    int x = nextInt();
                    //System.out.println("brute add: " + (x + last_sum_resultbrute) % MODULO);
                    bruteinsert((x + last_sum_resultbrute) % MODULO);
                } break;
                case '-' : {
                    int x = nextInt();
                    //System.out.println("brute remove: " + (x + last_sum_resultbrute) % MODULO);
                    bruteerase((x + last_sum_resultbrute) % MODULO);
                } break;
                case '?' : {
                    int x = nextInt();
                  //  System.out.println("brute add: " + (x + last_sum_resultbrute) % MODULO);
                    out.println(brutefind((x + last_sum_resultbrute) % MODULO) ? "Found" : "Not found");
                } break;
             /*   case 's' : {
                    int l = nextInt();
                    int r = nextInt();
                    long res = brutesum((l + last_sum_resultbrute) % MODULO, (r + last_sum_resultbrute) % MODULO);
                    out.println(res);
                    last_sum_resultbrute = (int)(res % MODULO);
                }*/
            }
        }
    
    }
    
    Set<Integer> hashSet = new HashSet<Integer>();
    public void bruteinsert(int x) {
        hashSet.add(x);
    }
    
    public void bruteerase(int x) {
        hashSet.remove(x);
    }
    
    public boolean brutefind(int x) {
        if(hashSet.contains(x)) return true;
        else return false;
    }
    
    public long brutesum(int x, int y) {
        long sum = 0;
        for(Integer a: hashSet) {
            if(a >= x && a <= y)
                sum+=a;
        }
        return sum;
    }
    
    public void bruteprint() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(Integer a: hashSet) {
            list.add(a);
        }
        Collections.sort(list);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        for(Integer a: list) {
            System.out.println(a);
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    
   
    

    SetRangeSum() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new SetRangeSum();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }
    
    public void printTree() {
        ArrayList<Integer> res = new ArrayList<Integer>();
        printTreeHelper(res, root);
        Collections.sort(res);
        System.out.println("=======================================");
        for(Integer a: res) {
            System.out.print(a + " ");
        }
        System.out.println("========================================");
    }
    
    public void printTreeHelper(List<Integer> list, Vertex v) {
        if(v==null) return;
        list.add(v.key);
        printTreeHelper(list, v.left);
        printTreeHelper(list, v.right);
    }
    
    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
    char nextChar() throws IOException {
        return nextToken().charAt(0);
    }
}
