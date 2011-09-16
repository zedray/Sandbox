package com.zedray.search;

/***
 * Minimum number of apples puzzle.
 */
public final class MinimumNumberOfApples {

    /** Board size N. **/
    private static final int N = 4;
    /** Board size M. **/
    private static final int M = 4;

    /***
     * Private constructor.
     */
    private MinimumNumberOfApples() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {

        // Problem
        final int[][] apples = new int[][]{
                {5, 5, 5, 15},
                {0, 0, 0, 5},
                {0, 0, 60, 5},
                {30, 0, 0, 5}
           };

        // Solution
        System.out.println("Maximum number of apples "
                + getMaximumNumberOfApples(apples));
        }

    /***
     * Return the maximum number of collectible apples.
     *
     * @param apples Grid of apples to search.
     * @return Maximum number of collectible apples.
     */
    private static int getMaximumNumberOfApples(final int[][] apples) {

        /** Set the "max" array. **/
        int[][] max = new int[N][M];

        /** For each Row... **/
        for (int i = 0; i < N; i++) {
            /** For each Column... **/
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) {
                    max[i][j] = 0;
                } else if (i == 0) {
                    max[i][j] += max[i][j - 1];
                } else if (j == 0) {
                    max[i][j] += max[i - 1][j];
                } else {
                    max[i][j] = Math.max(max[i - 1][j], max[i][j - 1]);
                }
                max[i][j] += apples[i][j];
            }
        }

        return max[N - 1][M - 1];
    }
}
