package com.zedray.search;

/***
 * Minimum number of coins required for the given sum puzzle.
 */
public final class MinimumNumberOfCoins {
    /***
     * Private constructor.
     */
    private MinimumNumberOfCoins() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {

        // Problem
        final int[] coins = new int[] {
                2, 4, 8
        };
        final int sum = 11;

        // Solution
        System.out.println("Number of coins " + getNumberOfCoins(coins, sum)
                + " required for sum of " + sum);
    }

    /***
     * Return the minimum number of coins required for the given sum.
     *
     * @param coins Set of coins to use.
     * @param sum Sum to reach.
     * @return Minimum number of coins required.
     */
    private static int getNumberOfCoins(final int[] coins, final int sum) {

        /** Set the "min" array to maximum integer values. **/
        int[] min = new int[sum + 1];
        for (int i = 0; i < min.length; i++) {
            min[i] = Integer.MAX_VALUE;
        }

        /** Set the first value to zero. **/
        min[0] = 0;

        /** For each min value... **/
        for (int i = 0; i < min.length; i++) {
            /** For each coin... **/
            for (int j = 0; j < coins.length; j++) {
                if (
                /**
                 * --Coin is valid-- Coin value is less than or equal to the
                 * current sum.
                 */
                coins[j] <= i
                /**
                 * --Coin can be added to get this value-- Add this coin to
                 * historical min value to get target "i" value. Accept if less
                 * than min[i].
                 */
                && min[i - coins[j]] + 1 < min[i]

                && min[i - coins[j]] < Integer.MAX_VALUE) {
                    /**
                     * Increment minimum number of coins.
                     */
                    min[i] = min[i - coins[j]] + 1;
                }
            }
        }

        for (int i = 0; i < min.length; i++) {
            if (min[i] < Integer.MAX_VALUE) {
                System.out.println("Values [" + i + "] " + min[i]);
            } else {
                System.out.println("Values [" + i + "]");
            }
        }

        return min[sum - 1];
    }
}
