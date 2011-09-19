package com.zedray.search;

/***
 * Binary search.
 *
 * Search takes (log N) iterations.
 * O(1) best or O(log N) worst.
 */
public final class BinarySearch {

    /** Number of iterations. **/
    private static int mIterations;

    /***
     * Private constructor.
     */
    private BinarySearch() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + BinarySearch.class.getName());
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
        mIterations = 0;
        if (actualLocation != binarySearch(data, value)) {
            throw new RuntimeException("Incorrect value locatoin returned");
        }
        System.out.println("Value found from " + data.length + " values in "
                + (System.currentTimeMillis() - time)
                + "ms or Iterations[" + mIterations + "]");
    }

    /***
     * Search for value using a Binary Search.
     *
     * @param data Data to search.
     * @param value Value to search.
     * @return Found value location, or -1 if not found.
     */
    private static int binarySearch(final int[] data, final int value) {
        /** Define lowest search point. **/
        int low = 0;
        /** Define highest search point. **/
        int high = data.length - 1;

        /** While lowest search point is less or equal to the highest. **/
        while (low <= high) {
            /** Calculate a useful mid point. **/
            final int mid = low + (high - low) / 2;

            /** If mid point contains correct value then return location. **/
            if (data[mid] == value) {
                return mid;

            } else if (data[mid] < value) {
                /**
                 * Value is greater than mid point, so search the highest side
                 * of the array.
                 **/
                low = mid + 1;

            } else {
                /**
                 * Value is smaller than mid point, so search the lowest side
                 * of the array.
                 **/
                high = mid - 1;
            }

            mIterations++;
        }
        return -1;
    }
}
