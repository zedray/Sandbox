package com.zedray.search;

import java.util.Stack;

public class StackTest {
	public static void main(String[] args) {

		// Solution
		System.out.println("Data " + getData(getStack()));
	}

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

	private static String getData(Stack<Node> stack) {
		if (stack.isEmpty()) {
			return "";
		} else {
			return stack.pop().mData + getData(stack);
		}
	}
	
	public static class Node {
		public Node mLink;
		public String mData;

		public Node(Node link, String data) {
			mLink = link;
			mData = data;
		}
	}
}
