package com.algorithm.execute;

import com.algorithm.tree.BinaryTree;

public class AppTest {

	public static void main(String[] args) {
		//Create a new binaryTree
		BinaryTree tree =	createBinaryTree();
		tree.getNodeInOrder(tree.getRoot());
	}

	
	private static BinaryTree createBinaryTree() {
	    BinaryTree btree = new BinaryTree();
	    btree.addNode(6);
	    btree.addNode(4);
	    btree.addNode(8);
	    btree.addNode(3);
	    btree.addNode(5);
	    btree.addNode(12);
	    btree.addNode(9);
	    btree.addNode(7);
	    return btree;
	}
	
}
