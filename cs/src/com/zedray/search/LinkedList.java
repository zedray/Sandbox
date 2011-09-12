package com.zedray.search;

public class LinkedList {
	public static void main(String[] args) {

		// Problem
		final Node linkedList = new Node(new Node(new Node(null, "C"), "B"), "A");

		// Solution
		System.out.println("Data " + getData(linkedList));
		System.out.println("Cost " + getCost(linkedList));
	}

	private static int getCost(Node node) {
		if (node == null) {
			return 0;
		} else {
			return Node.cost(node, node.mLink) + getCost(node.mLink);
		}
	}

	private static String getData(Node node) {
		if (node == null) {
			return "";
		} else {
			return node.mData + getData(node.mLink);
		}
	}
	
	public static class Node {
		public Node mLink;
		public String mData;

		public Node(Node link, String data) {
			mLink = link;
			mData = data;
		}

		public static Integer cost(Node a, Node b) {
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
