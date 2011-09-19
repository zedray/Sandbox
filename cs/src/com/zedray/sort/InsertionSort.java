package com.zedray.sort;

/***
 * Insertion sort.
 *
 * Sort between n and (n-1)² iterations.
 * O(n) best or O(n²) worst, in-place with no extra memory.
 */
public final class InsertionSort {

    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private InsertionSort() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + InsertionSort.class.getName());
        sort(SortUtils.BEST, "BEST");
        sort(SortUtils.WORST, "WORST");
        sort(SortUtils.RANDOM, "RANDOM");
        sort(SortUtils.getRandom(10), "Random size of 10");
        sort(SortUtils.getRandom(100), "Random size of 100");
        sort(SortUtils.getRandom(1000), "Random size of 1,000");
        sort(SortUtils.getRandom(10000), "Random size of 10,000");

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
        SortUtils.printArray(SortUtils.isSorted(insertionSort(data)));
        System.out.println(label + " done " + data.length + " values in "
                + (System.currentTimeMillis() - time)
                + "ms or Iterations[" + mIterations + "]");
    }

    /***
     * Return values sorted with insertion sort.
     *
     * @param data Start data.
     * @return Values sorted with insertion sort.
     */
    private static int[] insertionSort(final int[] data) {

        /**
         * Range 0-i is sorted.
         * i-N is unsorted.
         * Value i is under consideration.
         */
        for (int i = 0; i < data.length; i++) {
            int j = i;

            /**
             * Find an element in the sorted part of the array smaller than the
             * value under consideration.
             */
            while (j > 0 && data[i] < data[j - 1]) {
                mIterations++;
                j--;
            }

            /**
             * Add the value under consideration to the sorted array, but first
             * move all the other values along one to make space.
             */
            int temp = data[i]; // Store.
            for (int k = i; k > j; k--) {
                mIterations++;
                data[k] = data[k - 1]; // Move along.
            }
            data[j] = temp; // Place value under consideration.
        }

        return data;
    }
}
