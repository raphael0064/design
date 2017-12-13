package datatype.binnarytree;

/**
 * Created by marlon on 2017/12/13.
 */
public class Node {
	Node leftChild;
	Node rightChild;
	int data;

	Node(int newData) {
		leftChild = null;
		rightChild = null;
		data = newData;
	}
}
