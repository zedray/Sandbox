package com.zedray.sort;

import java.util.Random;

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
     * Return a array of random int values of given size.
     *
     * @param size Size if output array.
     * @return Array of random int values of given size.
     */
    public static int[] getRandom(final int size) {
        final int[] result = new int[size];
        final Random random = new Random(1);
        for (int i = 0; i < size; i++) {
            result[i] = random.nextInt(size);
        }

        return result;
    }

    /***
     * Print the given array.
     *
     * @param array Array to print.
     */
    public static void printArray(final int[] array) {
        int max = 5;
        for (int value : array) {
            System.out.print(value + ", ");
            max--;
            if (max < 0) {
                break;
            }
        }
    }

    /***
     * Throw RuntimeException if the array is not correctly sorted.
     *
     * @param array Array to check.
     * @return Given array or RuntimeException if the array is not correctly
     *          sorted.
     */
    public static int[] isSorted(final int[] array) {
        int last = array[0];
        for (int value : array) {
            if (value < last) {
                throw new RuntimeException("Data is not correctly sorted");
            }
            last = value;
        }
        return array;
    }
}
