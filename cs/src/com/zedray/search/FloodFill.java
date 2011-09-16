package com.zedray.search;

import java.util.Stack;

/***
 * Flood fill puzzle.
 */
public final class FloodFill {

    /** Board size X. **/
    private static final int X = 102;
    /** Board size Y. **/
    private static final int Y = 1000;

    /***
     * Private constructor.
     */
    private FloodFill() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start");
        long time = System.currentTimeMillis();
        // print(fillRecursive(getFillArray(), 15, 15));
        // print(fillWithStack(getFillArray(), new Node(15, 15)));
        // print(fillRecursive(getFillArray(), 1, 1));
        fillWithStack(getFillArray(), new Node(1, 1));

        System.out.println("Done " + X * Y + " results in "
                + (System.currentTimeMillis() - time) + "ms");
    }

    /***
     * Return the populated board array.
     *
     * @return Populated board array.
     */
    private static boolean[][] getFillArray() {
        boolean[][] fill = new boolean[X][Y];
        Rectangle[] rectangles = {
                new Rectangle(10, 10, 50, 50),
                new Rectangle(10, 20, 30, 40)
        };
        for (Rectangle rectangle : rectangles) {
            for (int i = rectangle.mTop; i <= rectangle.mBottom; i++) {
                fill[i][rectangle.mLeft] = true;
                fill[i][rectangle.mRight] = true;
            }
            for (int i = rectangle.mLeft; i <= rectangle.mRight; i++) {
                fill[rectangle.mTop][i] = true;
                fill[rectangle.mBottom][i] = true;
            }
        }
        return fill;
    }

    /***
     * Populate the given array with a stack based fill.
     * Memory overload at around 4,202,000 cells.  Thats a 525x improvement on
     * fillRecursive().
     *
     * @param fillArray Array that needs filling.
     * @param node Start node.
     * @return Filled array.
     */
    private static boolean[][] fillWithStack(final boolean[][] fillArray,
            final Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node top = stack.pop();

            if (top.mX < 0 || top.mX >= X || top.mY < 0 || top.mY >= Y) {
                continue;

            } else if (fillArray[top.mX][top.mY]) {
                continue;

            } else {
                fillArray[top.mX][top.mY] = true;
                stack.add(new Node(top.mX + 1, top.mY));
                stack.add(new Node(top.mX - 1, top.mY));
                stack.add(new Node(top.mX, top.mY + 1));
                stack.add(new Node(top.mX, top.mY - 1));
            }
        }

        return fillArray;
    }

    /***
     * Node definition.
     */
    public static class Node {
        /** Node coordinates. **/
        int mX, mY;

        public Node(final int x, final int y) {
            mX = x;
            mY = y;
        }
    }

    /***
     * Fill the given array recursively.
     * Memory overload at around ~8000 cells.
     *
     * @param fillArray Array to fill.
     * @param x Start position x.
     * @param y Start position y.
     * @return Filled array.
     */
    private static boolean[][] fillRecursive(boolean[][] fillArray,
            int x, int y) {
        if (x < 0 || x >= X || y < 0 || y >= Y) {
            return fillArray;

        } else if (fillArray[x][y]) {
            return fillArray;

        } else {
            fillArray[x][y] = true;
            fillRecursive(fillArray, x + 1, y);
            fillRecursive(fillArray, x - 1, y);
            fillRecursive(fillArray, x, y + 1);
            fillRecursive(fillArray, x, y - 1);
            return fillArray;
        }
    }

    /***
     * Print the contents of the fill array.
     *
     * @param fill Fill array.
     */
    private static void print(boolean[][] fill) {
        for (int i = 0; i < X; i++) {
            StringBuilder x = new StringBuilder();

            for (int j = 0; j < Y; j++) {
                if (fill[i][j]) {
                    x.append("T");
                } else {
                    x.append(".");
                }
            }
            System.out.println(x.toString());
        }
    }

    /***
     * Rectangle object to place on board array.
     */
    public static class Rectangle {
        /** Rectangle coordinates. **/
        int mLeft, mTop, mRight, mBottom;

        public Rectangle(final int left, final int top, final int right,
                final int bottom) {
            mLeft = left;
            mTop = top;
            mRight = right;
            mBottom = bottom;
        }
    }
}
