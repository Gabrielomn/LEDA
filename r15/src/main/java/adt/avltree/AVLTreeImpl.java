package adt.avltree;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.Arrays;

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

	@Override
	public void remove(T element) {

		BSTNode<T> node = this.search(element);
		node = super.remove(node);
		this.rebalanceUp(node);
	}

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	@Override
	public void insert(T element) {
		super.insert(element);
		BSTNode<T> node = search(element);
		rebalanceUp(node);

	}

	protected int calculateBalance(BSTNode<T> node) {
		if(node == null || node.isEmpty()){
			return 0;
		}else{
			return height((BSTNode<T>)node.getLeft()) - height((BSTNode<T>)node.getRight());
		}
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (balance > 1) {
			if (calculateBalance((BSTNode<T>)node.getLeft()) < 0) {
				simpleLeftRotation((BSTNode<T>) node.getLeft());
			}
			simpleRightRotation(node);
		} else if (balance < -1) {
			if (calculateBalance((BSTNode<T>)node.getRight()) > 0) {
				simpleRightRotation((BSTNode<T>)node.getRight());
			}
			simpleLeftRotation(node);
		}
	}

	protected void simpleRightRotation(BSTNode<T> node) {
		BSTNode<T> aux = Util.rightRotation(node);
		if(this.getRoot().equals(node)){
			this.root = aux;
		}

	}

	protected void simpleLeftRotation(BSTNode<T> node){
		BSTNode<T> aux = Util.leftRotation(node);
		if(this.getRoot().equals(node)){
			this.root = aux;
		}

	}
	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while(parent != null){
			rebalance(parent);
			parent =(BSTNode<T>) parent.getParent();
		}

	}
}
