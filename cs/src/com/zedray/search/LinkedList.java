package com.zedray.search;

/***
 * Linked list puzzle.
 */
public final class LinkedList {
    /***
     * Private constructor.
     */
    private LinkedList() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {

        // Problem
        final Node linkedList = new Node(
                new Node(new Node(null, "C"), "B"), "A");

        // Solution
        System.out.println("Data " + getData(linkedList));
        System.out.println("Cost " + getCost(linkedList));
    }

    /***
     * Return the cost of transversing the entire graph.
     *
     * @param node Node.
     * @return Cost of transversing the entire graph.
     */
    private static int getCost(final Node node) {
        if (node == null) {
            return 0;
        } else {
            return Node.cost(node, node.mLink) + getCost(node.mLink);
        }
    }

    /***
     * Return all the data by transversing the entire graph.
     *
     * @param node Node.
     * @return All the data by transversing the entire graph.
     */
    private static String getData(final Node node) {
        if (node == null) {
            return "";
        } else {
            return node.mData + getData(node.mLink);
        }
    }

    /***
     * Node definition.
     */
    public static class Node {
        /** Link to next node in list. **/
        public Node mLink;
        /** Data from node. **/
        public String mData;

        public Node(final Node link, final String data) {
            mLink = link;
            mData = data;
        }

        /***
         * Cost of node link.
         *
         * @param a Node A.
         * @param b Node B.
         * @return Cost of node link.
         */
        public static Integer cost(final Node a, final Node b) {
            if (a.mLink == b) {
                return 1;
            } else if (a == b) {
                return 0;
            } else {
                return null;
            }
        }
    }
}
