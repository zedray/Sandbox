package com.zedray.sort;

/***
 * Bubble sort.
 *
 * Sort in (n-1)² iterations or O(n²) in place with no extra memory.
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
        long time = System.currentTimeMillis();
        mIterations = 0;

        // Problem.
        int[] data = new int[]{9, 0, 5, 7, 3, 1, 6, 4, 2, 8};

        // Solution.
        int[] result = bubbleSort(data);
        for (int value : result) {
            System.out.print(value + ", ");
        }

        System.out.println("Done " + data.length + " values in "
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
