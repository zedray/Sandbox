package com.zedray.sort;

import java.util.Comparator;
import java.util.TreeSet;

/***
 * Heap sort.
 *
 * Always sort in (n log n) iterations.
 * O(n log n) with n + heap space requirement.
 */
public final class HeapSort {

    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private HeapSort() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args
     *            Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + HeapSort.class.getName());
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
        SortUtils.printArray(SortUtils.isSorted(heapSort(data)));
        System.out.println(label + " done " + data.length + " values in "
                + (System.currentTimeMillis() - time) + "ms or Iterations["
                + mIterations + "]");
    }

    /***
     * Return values sorted with heap sort.
     *
     * @param data Data to sort.
     * @return Sorted values.
     */
    private static int[] heapSort(final int[] data) {

        /**
         * Populate sorted data structure "Heap" (i.e. TreeSet in Java) with
         * all the input data.
         **/
        final TreeSet<Integer> heap
            = new TreeSet<Integer>(new IntegerComparator());
        for (int i = 0; i < data.length; i++) {
            heap.add(data[i]);
            mIterations++;
        }

        /**
         * Copy the data from the "Heap" back into an array.
         **/
        final int[] result = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = heap.pollFirst();
            mIterations++;
        }

        return result;
    }

    /***
     * Comparator for comparing Integers while avoiding grouping.
     */
    public static class IntegerComparator implements Comparator<Integer> {

        @Override
        public final int compare(final Integer integer1,
                final Integer integer2) {
            if (integer1 < integer2) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
