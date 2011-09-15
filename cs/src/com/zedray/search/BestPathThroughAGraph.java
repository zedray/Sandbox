package com.zedray.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.zedray.search.BreathFirstSearch.Rectangle;

/***
 * Breath First Search puzzle.
 */
public final class BestPathThroughAGraph {

    /** Board size X. **/
    private static final int X = 20;
    /** Board size Y. **/
    private static final int Y = 20;
    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private BestPathThroughAGraph() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + BestPathThroughAGraph.class.getName());
        long time = System.currentTimeMillis();
        mIterations = 0;
        System.out.println(getNumberOfSteps(new Node(0, 0, X - 1, Y - 1, 0),
                getFillArray()));
        System.out.println("Done " + X * Y + " results in "
                + (System.currentTimeMillis() - time) + "ms Iterations["
                + mIterations + "]");
    }


    public static class NodeComparator implements Comparator<Node> {

    	@Override
    	public int compare(Node node1, Node node2) {
    		if (((Node) node1).getDistance() >= ((Node) node2).getDistance()) {
    			return +1;
    		} else {
    			return 1;
    		}
    	}
    }
    
    /***
     * Return number of steps required to reach final state.
     *
     * @param startNode Starting node in graph.
     * @param board Array of unreachable squares.
     * @return Number of steps required to reach final state.
     */
    private static int getNumberOfSteps(final Node startNode,
            final boolean[][] board) {
        boolean[][][][] visited = new boolean[X][Y][X][Y];
        
        TreeSet<Node> priorityQueue = new TreeSet<Node>(new NodeComparator());
        priorityQueue.add(startNode);
        
        while (!priorityQueue.isEmpty()) {
            Node top = priorityQueue.pollFirst();
            if (top.mPlayer1X < 0 || top.mPlayer1X >= X
                    || top.mPlayer1Y < 0 || top.mPlayer1Y >= Y) {
                // Player 1 out of bounds
                continue;
            }
            if (top.mPlayer2X < 0 || top.mPlayer2X >= X
                    || top.mPlayer2Y < 0 || top.mPlayer2Y >= Y) {
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
            if (visited[top.mPlayer1X][top.mPlayer1Y]
                                       [top.mPlayer2X][top.mPlayer2Y]) {
                // Node visited
                continue;
            } else {
                visited[top.mPlayer1X][top.mPlayer1Y]
                                       [top.mPlayer2X][top.mPlayer2Y] = true;
            }
//            System.out.println("Check " + top.toString());
        	mIterations++;
            if (startNode.mPlayer1X == top.mPlayer2X
                    && startNode.mPlayer1Y == top.mPlayer2Y
                    && startNode.mPlayer2X == top.mPlayer1X
                    && startNode.mPlayer2Y == top.mPlayer1Y) {
                System.out.println("Nodes remaing [" + priorityQueue.size() + "]");
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
                            priorityQueue.add(new Node(
                                    top.mPlayer1X + player1XDelta,
                                    top.mPlayer1Y + player1YDelta,
                                    top.mPlayer2X + player2XDelta,
                                    top.mPlayer2Y + player2YDelta,
                                    top.mSteps + 1));
                        }
                    }
                }
            }
        }
        return -1;
    }

    /***
     * Node definition.
     */
    public static class Node {
        /** Player coordinates. **/
        public int mPlayer1X, mPlayer1Y, mPlayer2X, mPlayer2Y;

        /** Number of steps taken. **/
        public int mSteps;

        /***
         * Node constructor.
         *
         * @param player1X Player 1 X coordinate.
         * @param player1Y Player 1 Y coordinate.
         * @param player2X Player 2 X coordinate.
         * @param player2Y Player 2 Y coordinate.
         * @param steps Number of steps taken
         */
        public Node(int player1X, int player1Y, int player2X, int player2Y,
                int steps) {
            mPlayer1X = player1X;
            mPlayer1Y = player1Y;
            mPlayer2X = player2X;
            mPlayer2Y = player2Y;
            mSteps = steps;
        }

        public int getDistance() {
        	return X - mPlayer1X + Y - mPlayer1Y + mPlayer2X + mPlayer2Y;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node p1[");
            sb.append(mPlayer1X);
            sb.append(",");
            sb.append(mPlayer1Y);
            sb.append("] p2[");
            sb.append(mPlayer2X);
            sb.append(",");
            sb.append(mPlayer2Y);
            sb.append("] steps[");
            sb.append(mSteps);
            sb.append("]");
            return sb.toString();
        }
    }

    /***
     * Return the populated board array.
     *
     * @return Populated board array.
     */
    private static boolean[][] getFillArray() {
        boolean[][] fill = new boolean[X][Y];
        Rectangle[] rectangles = {
                new Rectangle(2, 0, 8, 16),
                new Rectangle(10, 2, 13, 19),
                new Rectangle(15, 0, 19, 18)
        };
        for (Rectangle rectangle : rectangles) {
            for (int i = rectangle.mTop; i <= rectangle.mBottom; i++) {
                fill[i][rectangle.mLeft] = true;
                fill[i][rectangle.mRight] = true;
            }
            for (int i = rectangle.mLeft; i <= rectangle.mRight; i++) {
                fill[rectangle.mTop][i] = true;
                fill[rectangle.mBottom][i] = true;
            }
        }
        print(fill);
        return fill;
    }

    /***
     * Print the contents of the fill array.
     *
     * @param fill Fill array.
     */
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

    /***
     * Rectangle object to place on board array.
     */
    public static class Rectangle {
        /** Rectangle coordinates. **/
        int mLeft, mTop, mRight, mBottom;

        public Rectangle(int left, int top, int right, int bottom) {
            mLeft = left;
            mTop = top;
            mRight = right;
            mBottom = bottom;
        }
    }
}
