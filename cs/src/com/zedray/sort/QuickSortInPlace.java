package com.zedray.sort;

/***
 * Quick sort in place.
 *
 * Sort between (n log n) and n² iterations depending on data set and middle
 * value.
 * O(n log n) - O(n²) with recursion.
 */
public final class QuickSortInPlace {

    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private QuickSortInPlace() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args
     *            Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + QuickSortInPlace.class.getName());
        sort(SortUtils.BEST, "BEST");
        sort(SortUtils.WORST, "WORST");
        sort(SortUtils.RANDOM, "RANDOM");
        sort(SortUtils.getRandom(10), "Random size of 10");
        sort(SortUtils.getRandom(100), "Random size of 100");
        sort(SortUtils.getRandom(1000), "Random size of 1,000");
        sort(SortUtils.getRandom(10000), "Random size of 10,000");
        sort(SortUtils.getRandom(100000), "Random size of 100,000");
        sort(SortUtils.getRandom(1000000), "Random size of 1,000,000");
        sort(SortUtils.getRandom(10000000), "Random size of 10,000,000");
//        sort(SortUtils.getRandom(100000000), "Random size of 100,000,000"); OOM
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

        /** Check for empty or null array. **/
        if (data == null || data.length == 0) {
            return null;
        }

        quicksort(data, 0, data.length - 1);
        return data;
    }

    /***
     * Sort between the given values in the member Array "mArray".
     *
     * @param low Low point of sort.
     * @param high High point of sort.
     */
    private static void quicksort(final int[] data, final int low, final int high) {

        /** Define high and low markers. **/
        int i = low;
        int j = high;

        /**
         * Get the pivot element from the middle of the list (while avoiding
         * rounding issues).
         **/
        int pivot = data[low + (high - low) / 2];

        /** Divide into two lists. **/
        while (i <= j) {
            /**
             * If the current value from the left list is smaller then the
             * pivot element then get the next element from the left list.
             */
            while (data[i] < pivot) {
                i++;
            }
            /**
             * If the current value from the right list is larger then the
             * pivot element then get the next element from the right list.
             */
            while (data[j] > pivot) {
                j--;
            }

            /**
             * If we have found a values in the left list which is larger then
             * the pivot element and if we have found a value in the right list
             * which is smaller then the pivot element then we exchange the
             * values.  As we are done we can increase i and j.
             */
            if (i <= j) {
                exchange(data, i, j);
                mIterations++;
                i++;
                j--;
            }
        }

        /** Recursively sort the unsorted low and high parts of the array. **/
        if (low < j) {
            quicksort(data, low, j);
        }
        if (i < high) {
            quicksort(data, i, high);
            }
    }

    /***
     * Exchange elements i and j in the member Array "mArray".
     *
     * @param i Element to exchange.
     * @param j Element to exchange.
     */
    private static void exchange(final int[] data, final int i, final int j) {
        final int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
