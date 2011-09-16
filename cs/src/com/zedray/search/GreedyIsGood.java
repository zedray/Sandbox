package com.zedray.search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/***
 * Greedy approach to number of activities.
 */
public final class GreedyIsGood {

    /***
     * Private constructor.
     */
    private GreedyIsGood() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {

        // Problem
        final TreeSet<Activity> priorityQueue
            = new TreeSet<Activity>(new ActivityComparator());
        priorityQueue.add(new Activity(0, 5));
        priorityQueue.add(new Activity(0, 2));
        priorityQueue.add(new Activity(3, 4));
        priorityQueue.add(new Activity(2, 5));
        priorityQueue.add(new Activity(1, 2));

        // Solution
        for (Activity activitiy : getMostActivites(priorityQueue)) {
            System.out.println("Activitiy s" + activitiy.mStartTime
                    + " f" + activitiy.mFinishTime);
        }
    }

    /***
     * Node definition.
     */
    public static class Activity {
        /** Times. **/
        public int mStartTime, mFinishTime;

        /***
         * Activity constructor.
         *
         * @param startTime Player 1 X coordinate.
         * @param finishTime Player 1 Y coordinate.
         */
        public Activity(final int startTime, final int finishTime) {
            mStartTime = startTime;
            mFinishTime = finishTime;
        }
    }

    /***
     * Comparator for comparing Activities.
     */
    public static class ActivityComparator implements Comparator<Activity> {

        @Override
        public final int compare(final Activity activity1,
                final Activity activity2) {
            if (((Activity) activity1).mFinishTime
                    >= ((Activity) activity2).mFinishTime) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /***
     * Return a set of most activities taken.
     *
     * @param priorityQueue Set of Activities.
     * @return Set of most activities taken.
     */
    private static Set<Activity> getMostActivites(
            final TreeSet<Activity> priorityQueue) {
        final Set<Activity> activities = new HashSet<Activity>();

        Activity lastActivitySelected = priorityQueue.pollFirst();
        activities.add(lastActivitySelected);

        while (!priorityQueue.isEmpty()) {
            Activity top = priorityQueue.pollFirst();
            if (lastActivitySelected.mFinishTime <= top.mStartTime) {
                activities.add(top);
                lastActivitySelected = top;
            }
        }
        return activities;
    }
}
