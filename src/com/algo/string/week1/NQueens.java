package com.algo.string.week1;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    
    public static List<String[]> nQueens(int n) {
        List<String[]> res = new ArrayList<String[]>();
        if(n<=0) return res;
        int[] colVal = new int[n];
        dfs(n, res, 0, colVal);
        return res;
    }
    
    public static void dfs(int n, List<String[]> res, int row, int[] colVal) {
        if(row==n) {
            String[] unit = new String[n];
            for(int i = 0; i < n; i++) {
                StringBuilder s = new StringBuilder();
                for(int j = 0; j  < n; j++) {
                    if(j == colVal[i]) s.append("Q");
                    else s.append(".");
                }
                unit[i] = s.toString();
            }
            res.add(unit);
        }
        else {
            for(int i = 0; i < n; i++) {
                colVal[row] = i;
                if(isValid(row, colVal)) dfs(n, res, row+1, colVal);
            }
        }
    }
    
    public static boolean isValid(int row, int[] colVal) {
        for(int i = 0; i < row; i++) {
            if(colVal[row] == colVal[i] || Math.abs(colVal[row] - colVal[i]) == row-i) 
                return false;
        }
        return true;
    }
    
    
    public static void helper(int n, List<String> list, List<List<String>> result, String str, int col) {
        if(list.size() == n) {
            result.add(new ArrayList<String>(list));
            list = new ArrayList<String>();
            return;
        }
        else if(str.length() == n) {
            list.add(str);
            return;
        }
        else {
            for(int i = 0; i < n; i++) {
                    if(isLegal(list, i, col)) {
                        str=str+'Q';                        
                    }
                    else {
                        str=str+'.';
                    }
                }
                helper(n, list, result, str, ++col);
                str="";             
            }
        }
    

    public static boolean isLegal(List<String> list, int row, int col) {
        for(int i = 0; i < row-1; i++) {
            if(!list.isEmpty()) {
            String str = list.get(i);
            if(str.charAt(col)=='Q') return false;
            for(int j = 0; j < col; j++) {
                if(Math.abs(i-row) == Math.abs(j-col) && str.charAt(j)=='Q') return false;
            }
            }
        }
        if(!list.isEmpty() && list.get(row-1).contains("Q")) return false;
        return true;
    }
    
    public static void main(String[] args) {
        nQueens(8);
        
    }

}
