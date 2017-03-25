package com.algo.graph;
import java.io.*;
import java.util.*;

public class Diet {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    int solveDietProblem(int n, int m, double A[][], double[] b, double[] c, double[] x) {
      Arrays.fill(x, 1);
      double result = Double.NEGATIVE_INFINITY;
      boolean isBounded = false;
      int isFeasible = -1;
      // Write your code here
      double[][] B = new double[n+m+1][m+1];
      for(int i = 0; i < A.length; i++) {
          for(int j = 0; j < A[0].length; j++) {
              B[i][j] = A[i][j];
          }
      }
      
      //set up row after n
      int j = 0;
      for(int i = A.length;  i < B.length-1; i++) {
          B[i][j]=-1;
          j++;
      }
      
      for(int i = 0; i < B[0].length-1; i++) {
          B[B.length-1][i] = 1;
      }
      
      
      //set up last col
      for(int i = 0; i < b.length; i++) {
          B[i][B[0].length-1]=b[i];
      }
      double[] re = new double[c.length];
      
      B[B.length-1][B[0].length-1]=Math.pow(10.0, 9.0);
      List<ArrayList<Integer>> list = subsets(getArr(n+m), m);
      for(ArrayList ind: list) {
          double[][] mat = createMatrix(ind, B);
          double[][] rem = createRem(ind, B);
          Equation eq = new Equation(coeff(mat), constraint(mat));
          double[] dou = SolveEquation(eq);
          if(dou.length>0) {
              if(satisfyCons(matMul(dou, coeff(rem)), constraint(rem))) {
                  if(consMul(dou, c)>result) {
                      result = consMul(dou, c);
                      re = dou;
                      if(!belongToMat(coeff(mat), coeffinf(B[B.length-1]))) {
                          isBounded = true;
                      }
                      else isBounded = false;
                      
                  }
              }
              
          }
          System.out.println("");
          
      }
      
      System.out.println(re);
      
      
      
      return 0;
    }
    
    public double[] coeffinf(double[] arr) {
        double[] res = new double[arr.length-1];
        for(int i = 0; i < arr.length-1; i++) res[i] = arr[i];
        return res;
    }
    public static boolean belongToMat(double[][] mat, double[] inf) {
       
        for(int i = 0; i < mat.length; i++) {
            boolean temp = true;
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j]!=inf[j]) temp=false;
            }
            if(temp) return true;
        }
        return false;
    }
    
    public static double consMul(double[] dou, double[] cons) {
        double result = 0;
        for(int i = 0; i < dou.length; i++) {
            result+=(dou[i]*cons[i]);
        }
        return result;
        
    }
    public static double[] matMul(double[] dou, double[][] coeff) {
        double[] result = new double[coeff.length];
        for(int i = 0; i < coeff.length; i++) {
            double res = 0;
            for(int j = 0; j < coeff[0].length; j++) {
                res+=coeff[i][j]*dou[j];
            }
            result[i] = res;
        }
        return result;        
    }
    
    public static boolean satisfyCons(double[] result, double[] conses) {
        for(int i = 0; i < result.length; i++) {
            if(result[i] > (conses[i]+Math.pow(10, -3))) return false;
        }
        return true;
    }
    public static double[][] createRem(List<Integer> row, double[][] B) {
        double[][] newmat = new double[B.length-row.size()][B[0].length];
        int j = 0;
        for(int i = 0; i < B.length; i++) {
            if(!row.contains(i)) {
                newmat[j] = B[i];
                j++;
            }
        }
        return newmat;
    }
    
    public static double[][] coeff(double[][] mat) {
        double[][] coeff = new double[mat.length][mat[0].length-1];
        for(int i = 0; i < mat[0].length-1; i++) {
            for(int j = 0; j < mat.length; j++) {
              coeff[j][i] = mat[j][i];
            }
        }
        return coeff;
    }
    
    public static double[] constraint(double[][] mat) {
        double[] cons = new double[mat.length];
        for(int i = 0; i < mat.length; i++) {
              cons[i] = mat[i][mat[0].length-1];
            }
        return cons;
   }
    

    public static double[][] createMatrix(List<Integer> row, double[][] B) {
        double[][] newmat = new double[row.size()][B[0].length];
        int i = 0;
        for(Integer j: row) {
            newmat[i] = B[j];  
            i++;
        }
        return newmat;
    }
    
    public int[] getArr(int num) {
        int[] arr = new int[num];
        for(int i = 0; i < num; i++) {
            arr[i] = i;
        }
        return arr;
    }
   
    public ArrayList<ArrayList<Integer>> subsets(int[] num, int m) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(num);  
        subsetsHelper(result, list, num, 0, m);

        return result;
    }


    private void subsetsHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> list, int[] num, int pos, int m) {
        if(list.size()==m) {
          result.add(new ArrayList<Integer>(list));
          return;
        }
        for (int i = pos; i < num.length; i++) {

            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1, m);
            list.remove(list.size() - 1);
        }
    }

   
    static class Equation {
        Equation(double a[][], double b[]) {
            this.a = a;
            this.b = b;
        }

        double a[][];
        double b[];
    }

    static class Position {
        Position(int column, int raw) {
            this.column = column;
            this.raw = raw;
        }

        int column;
        int raw;
    }


    static Position SelectPivotElement(double a[][], boolean used_raws[], boolean used_columns[], int p) {
        // This algorithm selects the first free element.
        // You'll need to improve it to pass the problem.
        /*Position pivot_element = new Position(0, 0);
        while (used_raws[pivot_element.raw])
            ++pivot_element.raw;
        while (used_columns[pivot_element.column])
            ++pivot_element.column;*/
        int max = p;
        for(int i = p+1; i < a.length; i++) {
            if(Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                max = i;
            }
        }
        Position pivot_element = new Position(max, p);
        
        return pivot_element;
    }

    static void SwapLines(double a[][], double b[], boolean used_raws[], Position pivot_element) {
        int size = a.length;

        for (int column = 0; column < size; ++column) {
            double tmpa = a[pivot_element.column][column];
            a[pivot_element.column][column] = a[pivot_element.raw][column];
            a[pivot_element.raw][column] = tmpa;
        }

        double tmpb = b[pivot_element.column];
        b[pivot_element.column] = b[pivot_element.raw];
        b[pivot_element.raw] = tmpb;

        boolean tmpu = used_raws[pivot_element.column];
        used_raws[pivot_element.column] = used_raws[pivot_element.raw];
        used_raws[pivot_element.raw] = tmpu;

        pivot_element.raw = pivot_element.column;
    }

    static void ProcessPivotElement(double a[][], double b[], Position pivot_element) {
        // Write your code here
        int p = pivot_element.raw;
        for(int i = p+1; i < a.length; i++) {
            double alpha = a[i][p]/a[p][p];
            b[i]-=alpha*b[p];
            for(int j = pivot_element.column; j < a[0].length; j++) {
                a[i][j] -= alpha*a[p][j];
            }
        }
        
        
    }

    static void MarkPivotElementUsed(Position pivot_element, boolean used_raws[], boolean used_columns[]) {
        used_raws[pivot_element.raw] = true;
        used_columns[pivot_element.column] = true;
    }

    static double[] SolveEquation(Equation equation) {
        double a[][] = equation.a;
        double b[] = equation.b;
        double[] result = new double[b.length];
        int N = a.length;

        //boolean[] used_columns = new boolean[size];
        //boolean[] used_raws = new boolean[size];
        for (int p = 0; p < N; ++p) {
           /* Position pivot_element = SelectPivotElement(a, used_raws, used_columns, step);
            SwapLines(a, b, used_raws, pivot_element);
            ProcessPivotElement(a, b, pivot_element);
            MarkPivotElementUsed(pivot_element, used_raws, used_columns);*/
            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                    max = i;
                }
            }
            double[] temp = a[p]; a[p] = a[max]; a[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            // singular or nearly singular
            if (Math.abs(a[p][p]) <= 0.00000001) {
                return result;
            }

            // pivot within A and b
            for (int i = p + 1; i < N; i++) {
                double alpha = a[i][p] / a[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    a[i][j] -= alpha * a[p][j];
                }
            }
        }
        
        for(int i = b.length-1; i>=0; i--) {
            double sum = 0.0;
            for(int j = i+1; j < a[0].length; j++) {
                sum+=a[i][j]*result[j];
            }
            result[i] = (b[i]-sum)/a[i][i];
        }

        return result;
    }
    void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        double[][] A = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = nextInt();
            }
        }
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = nextInt();
        }
        double[] c = new double[m];
        for (int i = 0; i < m; i++) {
            c[i] = nextInt();
        }
        double[] ansx = new double[m];
        int anst = solveDietProblem(n, m, A, b, c, ansx);
        if (anst == -1) {
            out.printf("No solution\n");
            return;
        }
        if (anst == 0) {
            out.printf("Bounded solution\n");
            for (int i = 0; i < m; i++) {
                out.printf("%.18f%c", ansx[i], i + 1 == m ? '\n' : ' ');
            }
            return;
        }
        if (anst == 1) {
            out.printf("Infinity\n");
            return;
        }
    }

    Diet() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Diet();
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

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
}
