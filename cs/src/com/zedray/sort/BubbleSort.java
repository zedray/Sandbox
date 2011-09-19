package com.zedray.sort;

/***
 * Bubble sort.
 *
 * Always sort in (n-1)² iterations.
 * O(n²) in-place (i.e. with no extra memory).
 */
public final class BubbleSort {

    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private BubbleSort() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + BubbleSort.class.getName());
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
        SortUtils.printArray(SortUtils.isSorted(bubbleSort(data)));
        System.out.println(label + " done " + data.length + " values in "
                + (System.currentTimeMillis() - time)
                + "ms or Iterations[" + mIterations + "]");
    }

    /***
     * Return values sorted with BubbleSort.
     *
     * @param data Start data.
     * @return Values sorted with BubbleSort.
     */
    private static int[] bubbleSort(final int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                mIterations++;
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }

        return data;
    }
}
