package com.zedray.search;

/***
 * Linked list puzzle.
 */
public final class LinkedList {

    /** Number of nodes in the LinkedList. **/
    private static final int NUMBER_OF_NODES = 10;

    /** Number of iterations. **/
    private static int mIterations;

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

        /** Raw LinkedList. **/
        System.out.println("Data " + getData(getLinkedList(NUMBER_OF_NODES)));
        System.out.println("Cost " + getCost(getLinkedList(NUMBER_OF_NODES)));

        /** Reverse the linked list (non-recursive solution). **/
        mIterations = 0;
        final Node reverse = reverse(getLinkedList(NUMBER_OF_NODES));

        System.out.println("Iterations " + mIterations);
        System.out.println("Reverse Data " + getData(reverse));
        System.out.println("Reverse Cost " + getCost(reverse));

        /** Reverse the linked list(recursive solution . **/
        mIterations = 0;
        final Node reverseRecursive
            = reverseRecursive(getLinkedList(NUMBER_OF_NODES), null);

        System.out.println("Iterations " + mIterations);
        System.out.println("Reverse recursive Data "
                + getData(reverseRecursive));
        System.out.println("Reverse recursive Cost "
                + getCost(reverseRecursive));
    }

    /***
     * Return a LinknedList of the given size.
     *
     * @param nodes Required number of nodes.
     * @return LinknedList of the given size.
     */
    private static Node getLinkedList(final int nodes) {
        Node result = null;
        for (int i = 0; i < nodes; i++) {
            result = new Node(result, "[Node " + (nodes - i) + "]");
        }
        return result;
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
     * Reverse the given LinkedList (non-recursive solution).
     *
     * @param node Head node.
     * @return Reversed LinkedList.
     */
    private static Node reverse(final Node node) {

        /*
         * Start with the given node as headNode, and the result as a NULL
         * node.
         */
        Node headNode = node;
        Node result = null;

        /*
         * Keep iterating until the headNode is NULL.
         */
        while (headNode != null) {

            /* Cache the next linked node. */
            final Node tempNode = headNode.mLink;

            /*
             * Remove the headNode and point it to the first node of the
             * result.
             */
            headNode.mLink = result;
            result = headNode;

            /* Replace the headNode with the cached next linked node. */
            headNode = tempNode;

            mIterations++;
        }

        return result;
    }

    /***
     * Reverse the given LinkedList (recursive solution).
     *
     * @param input Current head node.
     * @param result Current result (or NULL for first call).
     * @return Reversed LinkedList.
     */
    private static Node reverseRecursive(final Node input, final Node result) {
        mIterations++;

        if (input != null) {
            /*
             * Input queue is not empty, so remove it from the input and make
             * it the first node of the result.
             */
            final Node nextInput = input.mLink;
            input.mLink = result;
            final Node nextResult = input;

            /*
             * Process the remainder of the input.
             */
            return reverseRecursive(nextInput, nextResult);

        } else {
            return result;
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
