package com.zedray.search;

import java.util.Arrays;

/***
 * Binary search.
 *
 * Search takes (log N) iterations.
 * O(1) best or O(log N) worst.
 */
public final class JavaBinarySearch {

    /***
     * Private constructor.
     */
    private JavaBinarySearch() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + JavaBinarySearch.class.getName());
        search(new int[]{}, 5, -1);
        search(new int[]{5}, 5, 0);
        search(new int[]{0, 5}, 5, 1);
        search(new int[]{0, 5, 13, 19, 22, 41, 55, 68, 72, 81, 98}, 81, 9);
        search(new int[]{0, 5, 13, 19, 22, 41, 55, 68, 72, 81, 98}, 111, -1);

        System.out.println("Done");
    }

    /***
     * Search the given array for the given value.
     *
     * @param data Data to search.
     * @param value Value to search.
     * @param actualLocation Actual location of value (for testing).
     */
    private static void search(final int[] data, final int value,
            final int actualLocation) {
        long time = System.currentTimeMillis();
        if (actualLocation == -1 && 0 < Arrays.binarySearch(data, value)) {
            throw new RuntimeException("Incorrect value locatoin returned");
        }
        System.out.println("Value found from " + data.length + " values in "
                + (System.currentTimeMillis() - time) + "ms");
    }
}
