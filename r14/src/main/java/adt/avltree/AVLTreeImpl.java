package adt.avltree;

import adt.bst.BST;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if(!node.isEmpty()) {
			return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}else{
			return 0;
		}
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if(calculateBalance(node) > 1){//nesse caso, está desbalanceado para a esquerda! rotação RR ou RL
			if(calculateBalance((BSTNode<T>) node.getLeft()) > 0) {//DESBALANCEADO PARA A ESQUERDA DUPLAMENTE ROTACAO RR
				simpleRightRotation(node);
			}else{//FORMOU UM JOELHO, ROTACAO RL
				doubleLeftRotation(node);
			}

		}else if (calculateBalance(node) < -1){//nesse caso, está desbalanceado para a direita! rotação LL ou LR
			if(calculateBalance((BSTNode<T>) node.getRight()) < 0){//DESBALANCEADO PARA A DIREITA DUPLAMENTE ROTACAO LL
				simpleLeftRotation(node);
			}else{//formou o joelho
				doubleRightRotation(node);
			}

		}else{//nao esta desbalanceada

		}
	}

	private void doubleLeftRotation(BSTNode<T> node) {
	}

	private void doubleRightRotation(BSTNode<T> node) {
	}

	private void simpleRightRotation(BSTNode<T> node) {
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
		leftChildRight.setParent(node);
		leftChild.setRight(node);
		node.setParent(leftChild);
		if(this.getRoot().equals(node)){
			this.root = leftChild;
		}

	}

	private void simpleLeftRotation(BSTNode<T> node){
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
		if(this.getRoot().equals(node)){
			this.root = rightChild;
		}

	}
	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
