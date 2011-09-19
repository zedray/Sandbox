package com.zedray.search;

/***
 * Birthday Problem.
 */
public final class BirthdayProblem {

    /** Maximum number of people to check. **/
    private static final int MAX_NUMBER_OF_PEOPLE = 50;
    /** Number of days in a year. **/
    private static final int DAYS_IN_A_YEAR = 365;

    /***
     * Private constructor.
     */
    private BirthdayProblem() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + BirthdayProblem.class.getName());
        check(MAX_NUMBER_OF_PEOPLE);
        System.out.println("Done");
    }

    /***
     * Print the probability that amongst a group of N people, the birthdays
     * (a) do not match, or (b) do match.
     *
     * @param people Maximum number of people.
     */
    private static void check(final int people) {
        float result = 1;
        for (int n = 2; n < people; n++) {
            final float top = (DAYS_IN_A_YEAR - (n - 1));
            result *= top / DAYS_IN_A_YEAR;
            System.out.println("n[" + n + "] (a) no matches:" + result
                    + " (b) one match:" + (1 - result));
        }
    }
}
