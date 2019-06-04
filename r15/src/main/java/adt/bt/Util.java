package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> rightChild = (BSTNode<T>) node.getRight();//pivot
		BSTNode<T> currFather = (BSTNode<T>) node.getParent();
		if(currFather != null) {
			if(currFather.getRight().equals(node)){
				currFather.setRight(rightChild);
			}else{
				currFather.setLeft(rightChild);
			}
		}
		rightChild.setParent(currFather);
		BSTNode<T> rightChildLeft = (BSTNode<T>)rightChild.getLeft();
		node.setRight(rightChildLeft);
		rightChildLeft.setParent(node);
		rightChild.setLeft(node);
		node.setParent(rightChild);
		return rightChild;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();//pivot
		BSTNode<T> currFather = (BSTNode<T>) node.getParent();
		if(currFather != null) {
			if(currFather.getRight().equals(node)){
				currFather.setRight(leftChild);
			}else{
				currFather.setLeft(leftChild);
			}
		}
		leftChild.setParent(currFather);
		BSTNode<T> leftChildRight = (BSTNode<T>)leftChild.getRight();
		node.setLeft(leftChildRight);
		if(leftChildRight != null) {
			leftChildRight.setParent(node);
		}
		leftChild.setRight(node);
		node.setParent(leftChild);
		return leftChild;

	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
