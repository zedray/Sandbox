package com.zedray.search;

/***
 * Bob go home puzzle.
 */
public final class BobGoHome {

    /***
     * Private constructor.
     */
    private BobGoHome() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {

        // Problem.
        String[][] neighbourhood = {
                {"", "", "", "X", "", "", "B"},
                {"", "X", "", "X", "", "X", "X"},
                {"", "H", "", "", "", "", ""},
                {"", "", "", "X", "", "", ""},
                {"", "", "", "", "", "X", ""},
        };

        // Solution.
        int numberOfSteps = Integer.MAX_VALUE;

        System.out.println("Hello World!");
    }
}
