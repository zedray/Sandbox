package com.zedray.search;

import java.util.Stack;

/***
 * Stack puzzle.
 */
public final class StackTest {


    /***
     * Private constructor.
     */
    private StackTest() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {

        // Solution
        System.out.println("Data " + getData(getStack()));
    }

    /***
     * Return a populated stack.
     *
     * @return Populated stack.
     */
    private static Stack<Node> getStack() {
        Node a = new Node(null, "A");
        Node b = new Node(a, "B");
        Node c = new Node(b, "C");

        Stack<Node> stack = new Stack<Node>();
        stack.add(a);
        stack.add(b);
        stack.add(c);
        return stack;
    }

    /***
     * Return all the data by transversing the entire stack.
     *
     * @param stack Stack.
     * @return All the data by transversing the entire stack.
     */
    private static String getData(final Stack<Node> stack) {
        if (stack.isEmpty()) {
            return "";
        } else {
            return stack.pop().mData + getData(stack);
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
    }
}
