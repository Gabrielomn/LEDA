package adt.rbtree;

import adt.bst.BST;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

import java.util.Arrays;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		if(this.isEmpty()){
			return 0;
		}else{
			return blackHeight((RBNode) this.getRoot());
		}
	}

	private int blackHeight(RBNode curr) {
		if(curr == null || curr.isEmpty()){
			return 1;
		}
		if(curr.equals(this.getRoot()) || curr.getColour().equals(Colour.RED)){
			return Math.max(blackHeight((RBNode) curr.getLeft()), blackHeight((RBNode) curr.getRight()));
		}
		return 1 + Math.max(blackHeight((RBNode) curr.getLeft()), blackHeight((RBNode) curr.getRight()));
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) this.getRoot());

	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> current) {
		if (current.isEmpty()){
			return true;
		}else if(current.getColour().equals(Colour.RED) && verifyChildrenOfRedNode(current)){
			return false;
		}else{
			return verifyChildrenOfRedNodes((RBNode<T>) current.getLeft()) && verifyChildrenOfRedNodes((RBNode<T>) current.getRight());
		}
	}

	private boolean verifyChildrenOfRedNode(RBNode<T> red){
		return ((RBNode<T>) red.getLeft()).getColour().equals(Colour.RED) || ((RBNode<T>) red.getRight()).getColour().equals(Colour.RED);
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode) this.getRoot());
	}

	private boolean verifyBlackHeight(RBNode curr) {
		if(curr.isEmpty()) return true;
		if(blackHeight((RBNode) curr.getLeft()) == blackHeight((RBNode) curr.getRight())){
			return verifyBlackHeight((RBNode) curr.getRight()) && verifyBlackHeight((RBNode) curr.getLeft());
		}
		return false;
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			insert((RBNode<T>) this.getRoot(), value);
		}
	}

	public void insert(RBNode<T> current, T value){
		if(current.isEmpty()){
			current.setData(value);
			current.setColour(Colour.RED);
			current.setLeft(new RBNode<T>());
			current.setRight(new RBNode<T>());
			current.getLeft().setParent(current);
			current.getRight().setParent(current);
			fixUpCase1(current);
		}else{
			if(current.getData().compareTo(value) > 0){
				insert((RBNode<T>) current.getLeft(), value);
			}else{
				insert((RBNode<T>) current.getRight(), value);
			}
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		RBNode<T>[] res = new RBNode[this.size()];
		rbPreOrder((RBNode<T>) this.getRoot(), res, 0);
		return res;
	}

	private int rbPreOrder(RBNode<T> curr, RBNode<T>[] res, int index) {
		if(!curr.isEmpty()){
			res[index++] = curr;
			index = rbPreOrder((RBNode<T>) curr.getLeft(), res, index);
			index = rbPreOrder((RBNode<T>) curr.getRight(), res, index);
		}
		return index;
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if(node.equals((RBNode<T>) this.getRoot())){
			node.setColour(Colour.BLACK);
		}else{
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		System.out.println(node.getParent());
		if(!((RBNode<T>)node.getParent()).getColour().equals(Colour.BLACK)){
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> gramps = (RBNode<T>) parent.getParent();
		RBNode<T> uncle = (RBNode<T>) (gramps.getLeft().equals(parent) ? gramps.getRight() : gramps.getLeft()) ;
		if(uncle.getColour().equals(Colour.RED)){
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			gramps.setColour(Colour.RED);
			fixUpCase1(gramps);
		}else{
			fixUpCase4(node);
		}

	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> gramps = (RBNode<T>) parent.getParent();
		if(parent.getRight().equals(node) && gramps.getLeft().equals(parent)){
			leftRotation(parent);
			next = (RBNode<T>) node.getLeft();
		}else if (parent.getLeft().equals(node) && gramps.getRight().equals(parent)){
			rightRotation(parent);
			next = (RBNode<T>) node.getRight();
		}
		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		((RBNode<T>)node.getParent()).setColour(Colour.BLACK);
		((RBNode<T>)node.getParent().getParent()).setColour(Colour.RED);

		if(node.getParent().getLeft().equals(node)){
			rightRotation((RBNode<T>) node.getParent().getParent());
		}else{
			leftRotation((RBNode<T>) node.getParent().getParent());
		}

	}

	public void leftRotation(RBNode<T> node){
		RBNode<T> aux = (RBNode<T>) Util.leftRotation(node);
		if(this.getRoot().equals(aux)){
			this.root = aux;
		}
	}


	public void rightRotation(RBNode<T> node){
		RBNode<T> aux = (RBNode<T>) Util.rightRotation(node);
		if(this.getRoot().equals(aux)){
			this.root = aux;
		}
	}



}
