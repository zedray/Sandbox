package com.zedray.search;

import java.util.Stack;

public class FloodFill {
	final static int X = 4202;
	final static int Y = 1000;
	
    public static void main(String[] args) {
    	System.out.println("Start");
    	long time = System.currentTimeMillis();
//	 	print(fillRecursive(getFillArray(), 15, 15));
//	 	print(fillWithStack(getFillArray(), new Node(15, 15)));
	 	
//	 	print(fillRecursive(getFillArray(), 1, 1));
	 	fillWithStack(getFillArray(), new Node(1, 1));
	 	System.out.println("Done " + X * Y + " results in " + (System.currentTimeMillis() - time) + "ms");
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

	/***
	 * 4202000 max   525x better maximum size!!!
	 * @param fillArray
	 * @param node
	 * @return
	 */
	private static boolean[][] fillWithStack(boolean[][] fillArray, Node node) {
		Stack<Node> stack = new Stack<Node>();
		stack.push(node);
		
		while (!stack.isEmpty()) {
			Node top = stack.pop();

			if (top.mX < 0 || top.mX >= X || top.mY < 0 || top.mY >= Y) {
				continue;

			} else if (fillArray[top.mX][top.mY]) {
				continue;

			} else {
				fillArray[top.mX][top.mY] = true;
				stack.add(new Node(top.mX + 1, top.mY));
				stack.add(new Node(top.mX - 1, top.mY));
				stack.add(new Node(top.mX, top.mY + 1));
				stack.add(new Node(top.mX, top.mY - 1));
			}
		}

		return fillArray;
	}

	public static class Node {
		int mX;
		int mY;

		public Node(int x, int y) {
			mX = x;
			mY = y;
		} 
	}
	
    /***
     * Max ~8000 cells.
     * @return
     */
	private static boolean[][] fillRecursive(boolean[][] fillArray, int x, int y) {
		if (x < 0 || x >= X || y < 0 || y >= Y) {
			return fillArray;

		} else if (fillArray[x][y]) {
			return fillArray;

		} else {
			fillArray[x][y] = true;
			fillRecursive(fillArray, x + 1, y);
			fillRecursive(fillArray, x - 1, y);
			fillRecursive(fillArray, x, y + 1);
			fillRecursive(fillArray, x, y - 1);
			return fillArray;
		}
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
