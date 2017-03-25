package com.algo.bst;

import java.util.ArrayList;
import java.util.List;

import sun.applet.Main;

public class SnakeGame {
    private int width;
    private int height;
    private int[][] food;
    int score = 0;
    List<Vertex> snake = new ArrayList<Vertex>();
    
    private class Vertex {
        private int x;
        private int y;
        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
    	snake.add(new Vertex(0, 0));
        this.width = width;
        this.height = height;
        this.food = food;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        Vertex head = snake.get(snake.size()-1);
        if(direction.equals("D")) {
            snake.add(new Vertex(head.x+1, head.y));
        }
        else if(direction.equals("U")) {
            snake.add(new Vertex(head.x-1, head.y));
        }
        else if(direction.equals("L")) {
            snake.add(new Vertex(head.x, head.y-1));
        }
        else if(direction.equals("R")) {
            snake.add(new Vertex(head.x, head.y+1));
        }
        Vertex newHead = snake.get(snake.size()-1);
        boolean isFood = false;
        for(int i = 0; i < food.length; i++) {
                if(food[i][0] == newHead.x && food[i][1] == newHead.y)  {
                  isFood = true;
                  food[i][0] = -1;
                  food[i][1] = -1;
                }
                else if(food[i][0] == -1 && food[i][1] == -1) continue;
                else break;
        }
        if(!isFood) {
            snake.remove(0);
        }
        else {
            score++;
        }
        if(isCross()) {score=-1; return score;}
        return score;
    }
    
    public void getScore() {
    	System.out.println(score);
    }
    
    public boolean isCross() {
        for(Vertex v: snake) {
            if(v.x < 0 || v.x > height-1 || v.y < 0 || v.y > width-1) {
                return true;
            }
        }
        Vertex head = snake.get(snake.size()-1);
        for(int i = 0; i < snake.size()-1; i++) {
            if(head.x == snake.get(i).x && head.y == snake.get(i).y) return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
		SnakeGame game = new SnakeGame(3, 3, new int[][]{{2,0},{0,0}, {0, 2}, {2, 2}});
		game.move("D");
		game.getScore();

		game.move("D");
		game.getScore();

		game.move("R");
		game.getScore();

		game.move("U");
		game.getScore();

		game.move("U");
		game.getScore();

		game.move("L");
		game.getScore();
		
		game.move("D");
		game.getScore();

		game.move("R");
		game.getScore();

		game.move("R");
		game.getScore();
		
		game.move("U");
		game.getScore();

		game.move("L");
		game.getScore();
		
		game.move("D");
		game.getScore();

	}
}
