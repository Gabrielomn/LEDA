package adt.bst.extended;

import java.util.Arrays;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {

		this.root = new BSTNode<T>();
		for(T e: array) {
			this.insert(e);
		}

		return this.order();
	}
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

			//	if(curr.getData().compareTo(element) > 0){
			if(this.getComparator().compare(curr.getData(), element) > 0){

				insert((BSTNode<T>)curr.getLeft(), element);
			}
			if(this.getComparator().compare(curr.getData(), element) < 0){
				insert((BSTNode<T>)curr.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		if(element == null || this.isEmpty()){
			return new BSTNode<>();
		}
		return search(this.getRoot(), element);
	}

	public BSTNode<T> search(BSTNode<T> curr,T element) {
		if(curr.isEmpty()){
			return curr;
		}else {
			BSTNode<T> res;
			if (curr.getData().equals(element)) {
				res = curr;
			} else {
				//if (curr.getData().compareTo(element) > 0) {
				if (this.getComparator().compare(curr.getData(), element) > 0) {

					res = search((BSTNode<T>) curr.getLeft(), element);
				} else {
					res = search((BSTNode<T>) curr.getRight(), element);
				}
			}
			return res;
		}
	}

	@Override
	public T[] reverseOrder() {
		return reverse();
	}

	public T[] reverse() {
		T[] res = (T[]) new Comparable[this.size()];
		reverseOrder(this.getRoot(), res, 0);
		return res;
	}

	private int reverseOrder(BSTNode<T> curr, T[] arr, int index) {
		if(!curr.isEmpty()){

			index = reverseOrder((BSTNode<T>)curr.getRight(), arr, index);
			arr[index] = curr.getData();
			index++;
			index = reverseOrder((BSTNode<T>)curr.getLeft(), arr, index);
		}

		return index;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}


}
