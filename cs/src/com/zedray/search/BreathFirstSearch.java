package com.zedray.search;

import java.util.ArrayList;
import java.util.List;

public class BreathFirstSearch {
	final static int X = 20;
	final static int Y = 20;
	
    public static void main(String[] args) {
    	System.out.println("Start");
    	long time = System.currentTimeMillis();
    	System.out.println(getNumberOfSteps(new Node(0, 0, 19, 19, 0)));
	 	System.out.println("Done " + X * Y + " results in " + (System.currentTimeMillis() - time) + "ms");
    }

	private static int getNumberOfSteps(Node startNode) {
    	boolean[][][][] visited = new boolean[X][Y][X][Y];
    	boolean[][] board = new boolean[X][Y];

    	List<Node> queue = new ArrayList<Node>();
    	queue.add(startNode);

    	while (!queue.isEmpty()) {
    		Node top = queue.remove(0);
    		if (top.mPlayer1X < 0 || top.mPlayer1X >= X || top.mPlayer1Y < 0 || top.mPlayer1Y >= Y) {
    			// Player 1 out of bounds
    			continue;
    		}
    		if (top.mPlayer2X < 0 || top.mPlayer2X >= X || top.mPlayer2Y < 0 || top.mPlayer2Y >= Y) {
    			// Player 2 out of bounds
    			continue;
    		}
    		if (board[top.mPlayer1X][top.mPlayer1Y]) {
    			// Player 1 on an impassable square
    			continue;
    		}
    		if (board[top.mPlayer2X][top.mPlayer2Y]) {
    			// Player 1 on an impassable square
    			continue;
    		}
    		if (visited[top.mPlayer1X][top.mPlayer1Y][top.mPlayer1Y][top.mPlayer1Y]) {
    			// Node visited
    			continue;
    		}
    		if (startNode.mPlayer1X == top.mPlayer2X
    				&& startNode.mPlayer1Y == top.mPlayer2Y
    				&& startNode.mPlayer2X == top.mPlayer1X
    				&& startNode.mPlayer2Y == top.mPlayer1Y) {
    			return top.mSteps;
    		}
    		
    		for (int player1XDelta = -1; player1XDelta <= 1; player1XDelta++) {
        		for (int player1YDelta = -1; player1YDelta <= 1; player1YDelta++) {
            		for (int player2XDelta = -1; player2XDelta <= 1; player2XDelta++) {
                		for (int player2YDelta = -1; player2YDelta <= 1; player2YDelta++) {
                			// Did we swap positions?
                			if (top.mPlayer2X == top.mPlayer1X + player1XDelta
                					|| top.mPlayer2Y == top.mPlayer1Y + player1YDelta
                					|| top.mPlayer1X == top.mPlayer2X + player2XDelta
                					|| top.mPlayer1Y == top.mPlayer2Y + player2YDelta) {
                				continue;
                			}

                			// If not, add to stack.
                			queue.add(new Node(
                					top.mPlayer1X + player1XDelta,
                					top.mPlayer1Y + player1YDelta,
                					top.mPlayer2X + player2XDelta,
                					top.mPlayer2Y + player2YDelta,
                					top.mSteps++
                					));
                		}
            		}
        		}
    		}    		
    	}
    	return -1;
	}

	public static class Node {
		public int mPlayer1X, mPlayer1Y, mPlayer2X, mPlayer2Y; 
		public int mSteps;
		
		public Node(int player1X, int player1Y, int player2X, int player2Y, int steps) {
			mPlayer1X = player1X;
			mPlayer1Y = player1Y;
			mPlayer2X = player2X;
			mPlayer2Y = player2Y;
			mSteps = steps;
    	}
	}
    
    
	private static boolean[][] getFillArray() {
		boolean[][] fill = new boolean[X][Y];
    	Rectangle[] rectangles = {
    			new Rectangle(10, 10, 50, 50),
    			new Rectangle(10, 20, 30, 40)
    	};
    	for (Rectangle rectangle: rectangles) {
    	 	for (int i = rectangle.mTop; i <= rectangle.mBottom; i++) {
    	 		fill[i][rectangle.mLeft] = true;
    	 		fill[i][rectangle.mRight] = true;
    	 	}
    	 	for (int i = rectangle.mLeft; i <= rectangle.mRight; i++) {
    	 		fill[rectangle.mTop][i] = true;
    	 		fill[rectangle.mBottom][i] = true;
    	 	}
    	}
		return fill;
	}

	private static void print(boolean[][] fill) {
		for (int i = 0; i < X; i++) {
	 		StringBuilder x = new StringBuilder();

		 	for (int j = 0; j < Y; j++) {
		 		if (fill[i][j]) {
		 			x.append("T");
		 		} else {
		 			x.append(".");
		 		}
		 	}
		 	System.out.println(x.toString());
	 	}
	} 
    
    public static class Rectangle {
    	int mLeft;
    	int mTop;
    	int mRight;
    	int mBottom;
    
    	public Rectangle(int left, int top, int right, int bottom) {
    	   	mLeft = left;
        	mTop = top;
        	mRight = right;
        	mBottom = bottom;
    	}
    }
}
