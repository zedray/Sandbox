package com.zedray.search;

import java.util.ArrayList;
import java.util.List;

/***
 * Minimum number of apples puzzle.
 */
public final class MinimumCostOfVertices {

    /** Number of nodes. **/
    private static final int NODES = 6;

    /***
     * Private constructor.
     */
    private MinimumCostOfVertices() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {

        // Problem
        final Vertix[] verticies = new Vertix[]{
                new Vertix(0, 3, 6),
                new Vertix(0, 1, 2),
                new Vertix(1, 0, 2),
                new Vertix(1, 2, 1),
                new Vertix(1, 5, 10),
                new Vertix(2, 2, 1),
                new Vertix(2, 3, 1),
                new Vertix(2, 4, 8),
                new Vertix(2, 5, 9),
                new Vertix(3, 0, 6),
                new Vertix(3, 2, 1),
                new Vertix(3, 4, 2),
                new Vertix(4, 3, 2),
                new Vertix(4, 2, 8),
                new Vertix(4, 5, 1),
                new Vertix(5, 1, 10),
                new Vertix(5, 2, 9),
                new Vertix(5, 4, 1)
           };
        final int money = 7;
        final int startNode = 0;
        final int endNode = 5;

        // Solution
        System.out.println("Cheapest path costs "
                + getCheapestPath(verticies, startNode, endNode, money));
        }

    /***
     * Represents a Vertex of a graph.
     */
    public static class Vertix {
        int mNodeA, mNodeB, mCost;
        public Vertix(final int nodeA, final int nodeB, final int cost) {
            mNodeA = nodeA;
            mNodeB = nodeB;
            mCost = cost;
        }

        public static List<Vertix> getVertices(Vertix[] verticies, int k) {
            List<Vertix> result = new ArrayList<Vertix>();
            for (Vertix vertix : verticies) {
                if (vertix.mNodeA == k) {
                    result.add(vertix);
                }
            }
            return result;
        }
    }

    /***
     * Return the cheapest path.
     *
     * @param verticies Graph to search.
     * @param startNode Start node.
     * @param endNode End node.
     * @param money Start money.
     * @return Maximum number of collectible apples.
     */
    private static int getCheapestPath(final Vertix[] verticies,
            final int startNode, final int endNode, final int money) {

        /** Set states array to unvisited. **/
        boolean[][] states = new boolean[NODES][money + 1];
        /** Set minimum distance travelled to MAX. **/
        int[][] min = new int[NODES][money + 1];
        for (int i = 0; i < NODES; i++) {
            for (int j = 0; j < money; j++) {
                min[i][j] = Integer.MAX_VALUE;
            }
        }

        // Set minimum value for start node.
        min[startNode][money] = 0;

        while (true) {
            int k = 0;
            int l = 0;
            int minValue = Integer.MAX_VALUE;
            for (int i = 0; i < NODES; i++) {
                for (int j = 0; j < money + 1; j++) {
                    if (!states[i][j] && min[i][j] < minValue) {
                        k = i;
                        l = j;
                        minValue =  min[i][j];
                    }
                }
            }
            if (minValue == Integer.MAX_VALUE) {
                System.out.println("No solution");
                return -1;
            }

            states[k][l] = true;

            for (Vertix vertix : Vertix.getVertices(verticies, k)) {
                System.out.println("Vertix [" + vertix.mNodeA + "]["
                        + vertix.mNodeB + "][" + vertix.mCost + "]");

                /**
                 * TODO: Don't understand the given solution, would opt for a
                 * Breath First Search with heuristic instead.
                 */
            }
            return 2;
        }
    }
}
