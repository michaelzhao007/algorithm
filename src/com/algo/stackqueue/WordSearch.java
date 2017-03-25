package com.algo.stackqueue;

public class WordSearch {
    
    public static boolean searchWord(char[][] board, String target) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(check(board, target, 0,  0,  0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean check(char[][] board, String target, int idx, int i, int j, boolean[][] visited) {
        if(i >= board.length || j >= board[0].length || i < 0 || j < 0) {return false;}
        if(visited[i][j]) return false;
        if(board[i][j]!=target.charAt(idx)) return false;
        if(idx == target.length()) return true;        
        visited[i][j] = true;
        boolean ret = check(board, target, idx+1, i+1, j, visited)
                           ||check(board, target, idx+1, i-1, j, visited)
                             ||check(board, target, idx+1, i, j+1, visited)
                              ||check(board, target, idx+1, i, j-1, visited);
        visited[i][j] = false;
        return ret;
    }
    
    public static void main(String[] args) {
        char[][] board = new char[][]{
          {'A', 'B', 'C', 'E'},
          {'S', 'F', 'C', 'S'},
          {'A', 'D', 'E', 'E'}
        };
        System.out.println(searchWord(board, "SEE"));
    }

}
