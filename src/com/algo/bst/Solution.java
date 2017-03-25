package com.algo.bst;

public class Solution {
    public static void gameOfLife(int[][] board) {
        int[][] matrix = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                matrix[i][j] = board[i][j];
            }
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                int countlive = 0;
                int countdead = 0;
                if(i-1 >= 0) {
                    if(board[i-1][j]==1) countlive++;
                    else countdead++;
                    
                    if(j+1<board[0].length) {
                       if(board[i-1][j+1]==1) countlive++;
                       else countdead++;
                    }
                    
                    if(j-1>=0) {
                        if(board[i-1][j-1]==1) countlive++;
                        else countdead++;
                    }
                }
                
                if(i+1<board.length) {
                    if(board[i+1][j]==1) countlive++;
                    else countdead++;
                    
                    if(j+1<board[0].length) {
                        if(board[i+1][j+1]==1) countlive++;
                        else countdead++;
                    }
                    
                    if(j-1>=0) {
                        if(board[i+1][j-1]==1) countlive++;
                        else countdead++;
                    }
                }
                
                if(j-1>=0) {
                    if(board[i][j-1]==1) countlive++;
                    else countdead++;
                }
                if(j+1 < board[0].length) {
                    if(board[i][j+1]==1) countlive++;
                    else countdead++;
                }
                
                if(countlive<2) matrix[i][j] = 0;
                else if(countlive>3) matrix[i][j] = 0;
                if(countlive==3&&board[i][j]==0) matrix[i][j] = 1;
                
            }
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] = matrix[i][j];
            }
        }
        
        
    }
    public static void main(String[] args) {
		int[][] board = new int[2][2];
		board[0][0] = 1;
		board[0][1] = 1;
		board[1][0] = 1;
		board[1][1] = 0;
		gameOfLife(board);
	}
}
