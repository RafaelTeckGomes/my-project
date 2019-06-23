package com.algorithm.tree;

public class BinaryTree {

	private Node root;

	public void addNode(int value) {
		root = addRecursive(root, value);
	}

	private Node addRecursive(Node current, int value) {

		if (current == null) {
			return new Node(value);
		}

		if (value < current.value) {
			current.left = addRecursive(current.left, value);
		} else if (value > current.value) {
			current.right = addRecursive(current.right, value);
		}

		return current;
	}

	public void getNodeInOrder(Node node) {
		if (node != null) {
			getNodeInOrder(node.left);
			System.out.println("node value: " + node.value);
			getNodeInOrder(node.right);
		}
	}

	public boolean find(int id) {
		Node current = root;
		while (current != null) {
			if (current.value == id) {
				return true;
			} else if (current.value > id) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	public Node getRoot() {
		return root;
	}
	
	

}
