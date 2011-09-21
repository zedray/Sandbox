package com.zedray.search;

import com.zedray.sort.SortUtils;

/***
 * No division puzzle.
 */
public final class NoDivision {

    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private NoDivision() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        generate(getArray(10), "Size of 10");
        generate(getArray(100), "Size of 100");
        generate(getArray(1000), "Size of 1,000");
        generate(getArray(10000), "Size of 10,000");
    }

    /***
     * Generate an array of the given size.
     *
     * @param size Size of array.
     * @return Array of the given size.
     */
    private static int[] getArray(final int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    /***
     * Sort the given array.
     *
     * @param data Data to sort.
     * @param label Label for output.
     */
    private static void generate(final int[] data, final String label) {
        long time = System.currentTimeMillis();
        mIterations = 0;
        SortUtils.printArray(arrayMultiplier(data));
        System.out.println(label + " done " + data.length + " values in "
                + (System.currentTimeMillis() - time) + "ms or Iterations["
                + mIterations + "]");
    }

    /***
     * Return an array where A[i] is equal to multiplication of all the
     * elements of A[N] except A[i]. For example Output[0] will be
     * multiplication of A[1] to A[N-1] and Output[1] will be multiplication of
     * A[0] and from A[2] to A[N-1].

     * @param input Input Array.
     * @return Result Array.
     */
    private static int[] arrayMultiplier(final int[] input) {

        /*
         * Create a new array as follows:
         *
         * input  = a  b  c
         * xArray = 1  a  a*b
         */
        int[] xArray = new int[input.length];
        xArray[0] = 1;
        for (int i = 1; i < input.length; ++i) {
            xArray[i] = xArray[i - 1] * input[i - 1];
            mIterations++;
        }

        /*
         * Create a new array as follows:
         *
         * input  = a    b  c
         * yArray = b*c  c  1
         */
        int[] yArray = new int[input.length];
        yArray[input.length - 1] = 1;
        for (int i = input.length - 2; i >= 0; --i) {
            yArray[i] = yArray[i + 1] * input[i + 1];
            mIterations++;
        }

        /*
         * Multiply xArray by yArray:
         *
         * input  = a    b    c
         * xArray = 1    a    a*b
         * yArray = b*c  c    1
         * result = b*c  a*c  a*b
         */
        final int[] result = new int[input.length];
        for (int i = 0; i < input.length; ++i) {
            result[i] = xArray[i] * yArray[i];
            mIterations++;
        }

        return result;
    }
}
