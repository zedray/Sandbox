package com.zedray.sort;

import java.util.ArrayList;
import java.util.List;

/***
 * Quick sort.
 *
 * Sort between (n log n) and n² iterations depending on data set and middle value.
 * O(n log n) - O(n²) with recursion.
 */
public final class QuickSort {

    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private QuickSort() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args
     *            Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + QuickSort.class.getName());
        sort(SortUtils.BEST, "BEST");
        sort(SortUtils.WORST, "WORST");
        sort(SortUtils.RANDOM, "RANDOM");
        sort(SortUtils.getRandom(10), "Random size of 10");
        sort(SortUtils.getRandom(100), "Random size of 100");
        sort(SortUtils.getRandom(1000), "Random size of 1,000");
        sort(SortUtils.getRandom(10000), "Random size of 10,000");

        sort(SortUtils.getRandom(100000), "Random size of 100,000");
        sort(SortUtils.getRandom(1000000), "Random size of 1,000,000");
//        sort(SortUtils.getRandom(10000000), "Random size of 10,000,000"); OOM
        System.out.println("Done");
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
        SortUtils.printArray(SortUtils.isSorted(quickSort(data)));
        System.out.println(label + " done " + data.length + " values in "
                + (System.currentTimeMillis() - time) + "ms or Iterations["
                + mIterations + "]");
    }

    /***
     * Return values sorted with quick sort.
     *
     * @param data Data to sort.
     * @return Sorted values.
     */
    private static int[] quickSort(final int[] data) {

        /**
         * Return any data set smaller than 1 element intact, i.e. actual
         * sorting not required.
         **/
        if (data.length <= 1) {
            return data;
        }

        /**
         * "Middle" value for splitting rest of data.  Complexity of sort is
         * dependent on how central this value is how well the input data can
         * be divided.
         */
        int middleValue = data[data.length / 2];

        /**
         * Create expandable lists for left and right half of data.
         */
        final List<Integer> leftArray = new ArrayList<Integer>();
        final List<Integer> rightArray = new ArrayList<Integer>();

        /**
         * Split data into left and right Lists using middle value.
         */
        for (int i = 0; i < data.length; i++) {
            if (i != data.length / 2) {
                if (data[i] < middleValue) {
                    leftArray.add(data[i]);
                } else {
                    rightArray.add(data[i]);
                }
            }

            mIterations++;
        }

        /**
         * Sort left and right Lists recursively, and concatenate the arrays to
         * get the final result:
         *
         * resultArray = "leftArray" + "middleValue" + "rightArray"
         */
        int[] finalArray = new int[leftArray.size() + 1 + rightArray.size()];
        System.arraycopy(quickSort(leftArray),
                0, finalArray, 0, leftArray.size());
        finalArray[leftArray.size()] = middleValue;
        System.arraycopy(quickSort(rightArray),
                0, finalArray, leftArray.size() + 1, rightArray.size());

        return finalArray;
    }

    /***
     * Return values sorted with quick sort.
     *
     * @param data List of data to sort.
     * @return Sorted values.
     */
    private static int[] quickSort(final List<Integer> data) {
        final int[] result = new int[data.size()];
        int i = 0;
        for (int value : data) {
            result[i] = value;
            i++;
        }

        return quickSort(result);
    }
}
