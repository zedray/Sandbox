package com.zedray.sort;

import java.util.Arrays;

/***
 * Merge sort.
 *
 * Always sort in (n log n) iterations.
 * O(n log n) with around 2*n space requirement or more with recursion
 * overhead.
 */
public final class MergeSort {

    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private MergeSort() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args
     *            Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + MergeSort.class.getName());
        sort(SortUtils.BEST, "BEST");
        sort(SortUtils.WORST, "WORST");
        sort(SortUtils.RANDOM, "RANDOM");
    }

    /***
     * Sort the given array.
     *
     * @param data Data to sort.
     * @param label Label for output.
     */
    private static void sort(final int[] data, final String label) {
        long time = System.currentTimeMillis();
        mIterations = 0;
        SortUtils.printArray(mergeSort(data));
        System.out.println(label + " done " + data.length + " values in "
                + (System.currentTimeMillis() - time) + "ms or Iterations["
                + mIterations + "]");
    }

    /***
     * Return values sorted with merge sort.
     *
     * @param data Data to sort.
     * @return Sorted values.
     */
    private static int[] mergeSort(final int[] data) {

        /**
         * Return any data set smaller than 1 element intact, i.e. actual
         * sorting not required.
         **/
        if (data.length == 1) {
            return data;
        }

        /**
         * Find the mid-point in the given array.
         */
        int middle = data.length / 2;

        /**
         * Recursively sort left and right parts of array.
         */
        int[] left = mergeSort(Arrays.copyOfRange(data, 0, middle));
        int[] right = mergeSort(Arrays.copyOfRange(data, middle, data.length));

        /***
         * Create a new result array (not actually required).
         */
        int[] result = new int[data.length];

        /**
         * Merge left and right sorted arrays.
         */
        int resultPointer = 0;
        int leftPointer = 0;
        int rightPointer = 0;

        /**
         * For each element in the result array.
         */
        while (resultPointer < result.length) {

            if (leftPointer == left.length) {
                // Left array finished, so add right element.
                result[resultPointer] = right[rightPointer];
                rightPointer++;

            } else if (rightPointer == right.length) {
                // Right array finished, so add left element.
                result[resultPointer] = left[leftPointer];
                leftPointer++;

            } else if (left[leftPointer] < right[rightPointer]) {
                // Right element larger, so add left element first.
                result[resultPointer] = left[leftPointer];
                leftPointer++;

            } else {
                // Left element larger, so add right element first.
                result[resultPointer] = right[rightPointer];
                rightPointer++;
            }
            mIterations++;
            resultPointer++;
        }

        return result;
    }
}
