package com.zedray.sort;

import java.util.Arrays;

/***
 * Java Sort (i.e. tuned QuickSort).
 *
 * Sorts the specified array of ints into ascending numerical order. The
 * sorting algorithm is a tuned quicksort, adapted from Jon L. Bentley and
 * M. Douglas McIlroy's "Engineering a Sort Function", Software-Practice and
 * Experience, Vol. 23(11) P. 1249-1265 (November 1993). This algorithm offers
 * n*log(n) performance on many data sets that cause other QuickSorts to
 * degrade to quadratic performance.
 */
public final class JavaSort {

    /***
     * Private constructor.
     */
    private JavaSort() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + JavaSort.class.getName());
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
        sort(SortUtils.getRandom(100000000), "Random size of 100,000,000");
        System.out.println("Done");
    }

    /***
     * Sort the given array.
     *
     * @param data Data to sort.
     * @param label Label for output.
     */
    private static void sort(final int[] data, final String label) {
        final long time = System.currentTimeMillis();
        SortUtils.printArray(SortUtils.isSorted(javaSort(data)));
        System.out.println(label + " done " + data.length + " values in "
                + (System.currentTimeMillis() - time) + "ms");
    }

    /***
     * Return values sorted with quick sort.
     *
     * @param data Data to sort.
     * @return Sorted values.
     */
    private static int[] javaSort(final int[] data) {
        Arrays.sort(data);
        return data;
    }
}
