package com.zedray.sort;

/***
 * Sort Utilities.
 */
public final class SortUtils {

    /** Best data to sort (i.e. already sorted) **/
    public static final int[] BEST
        = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    /** Worst data to sort (i.e. backwards sorted) **/
    public static final int[] WORST
        = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    /** Random data to sort. **/
    public static final int[] RANDOM
        = new int[]{9, 0, 5, 7, 3, 1, 6, 4, 2, 8};

    /***
     * Private constructor.
     */
    private SortUtils() {
        // Do nothing.
    }

    /***
     * Print the given array.
     *
     * @param array Array to print.
     */
    public static void printArray(final int[] array) {
        for (int value : array) {
            System.out.print(value + ", ");
        }
    }
}
