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

	@Override
	public void remove(T element) {
		BSTNode<T> aux = search(element);
		if(!this.isEmpty()){
			if(this.getRoot().equals(aux)){
				aux = sucessor(element);
				System.out.println(aux.getData());
				if (aux == null){
					aux = predecessor(element);
				}
				if(aux == null) {
					this.getRoot().setData(null);
				}else {
					T auxData = element;
					this.getRoot().setData(aux.getData());
					aux.setData(auxData);
					remove(aux);
				}
			}
			else if(!aux.isEmpty()){
				remove(aux);
			}
		}

	}

	private void remove(BSTNode<T> curr){
		if(curr.isLeaf()){
			setFatherNil(curr);
			rebalanceUp(curr);
		}else if(curr.getRight().isEmpty() || curr.getLeft().isEmpty()){
			changeFatherAndSon(curr);
			rebalanceUp(curr);

		}else{
			BSTNode<T> sucessor = sucessor(curr.getData());
			remove(sucessor.getData());
			curr.setData(sucessor.getData());
		}
	}

	private void changeFatherAndSon(BSTNode<T> curr) {
		if(curr.getLeft().isEmpty()){

			BSTNode<T> newCurr = (BSTNode) curr.getRight();
			BSTNode<T> parent = (BSTNode) curr.getParent();
			if(parent.getRight().equals(curr)){
				parent.setRight(newCurr);
			}else if(parent.getLeft().equals(curr)){
				parent.setLeft(newCurr);
			}
			newCurr.setParent(parent);
		}else{
			BSTNode<T> newCurr = (BSTNode) curr.getLeft();
			BSTNode<T> parent = (BSTNode) curr.getParent();
			if(parent.getRight().equals(curr)){
				parent.setRight(newCurr);
			}else if(parent.getLeft().equals(curr)){
				parent.setLeft(newCurr);
			}
			newCurr.setParent(parent);
		}
	}

	private void setFatherNil(BSTNode<T> curr) {
		BSTNode<T> parent = (BSTNode<T>) curr.getParent();
		if(parent.getLeft() != null && parent.getLeft().equals(curr)){
			parent.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(parent).build());
		}else if(parent.getRight() != null && parent.getRight().equals(curr)){
			parent.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(parent).build());
		}
	}


	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.getRoot(), element);
		}

	}

	private void insert(BSTNode<T> curr, T element){
		if(curr.isEmpty()){
			curr.setData(element);
			curr.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(curr).build());
			curr.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(curr).build());
		}else{
			if(curr.getData().compareTo(element) > 0){
				insert((BSTNode<T>)curr.getLeft(), element);
			}
			if(curr.getData().compareTo(element) < 0){
				insert((BSTNode<T>)curr.getRight(), element);
			}
			rebalance(curr);
		}
	}
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
				doubleRightRotation(node);
			}

		}else if (calculateBalance(node) < -1){//nesse caso, está desbalanceado para a direita! rotação LL ou LR
			if(calculateBalance((BSTNode<T>) node.getRight()) < 0){//DESBALANCEADO PARA A DIREITA DUPLAMENTE ROTACAO LL
				simpleLeftRotation(node);
			}else{//formou o joelho
				doubleLeftRotation(node);
			}

		}else{//nao esta desbalanceada

		}
	}

	private void doubleLeftRotation(BSTNode<T> node) {
		BSTNode<T> son = (BSTNode<T>) node.getRight();
		BSTNode<T> grandSon = (BSTNode<T>) son.getLeft();
		BSTNode<T> grandSonRightChild = (BSTNode<T>) grandSon.getRight();
		BSTNode<T> grandSonLeftChild = (BSTNode<T>) grandSon.getLeft();

		son.setLeft(grandSonRightChild);
		node.setRight(grandSonLeftChild);
		grandSon.setParent(node.getParent());
		grandSon.setRight(son);
		grandSon.setLeft(node);
		if(node.equals(this.getRoot())){
			this.root = grandSon;
		}
	}

	private void doubleRightRotation(BSTNode<T> node) {
		BSTNode<T> son = (BSTNode<T>) node.getLeft();
		BSTNode<T> grandSon = (BSTNode<T>) son.getRight();
		BSTNode<T> grandSonRightChild = (BSTNode<T>) grandSon.getRight();
		BSTNode<T> grandSonLeftChild = (BSTNode<T>) grandSon.getLeft();

		son.setRight(grandSonLeftChild);
		node.setLeft(grandSonRightChild);
		grandSon.setParent(node.getParent());
		grandSon.setLeft(son);
		grandSon.setRight(node);
		if(node.equals(this.getRoot())){
			this.root = grandSon;
		}
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
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while(parent != null){
			rebalance(parent);
			parent =(BSTNode<T>) parent.getParent();
		}

	}
}
