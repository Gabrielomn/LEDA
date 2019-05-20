package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.getRoot());
	}

	private int height(BSTNode<T> curr){
		if(curr.isEmpty()){
			return 0;
		}else{
			return 1 + maxInteger(height((BSTNode<T>)curr.getLeft()), height((BSTNode<T>) curr.getRight()));
		}
	}
	private int maxInteger(int a, int b){
		if(a > b){
			return a;
		}else{
			return b;
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.getRoot(), element);
	}

	public BSTNode<T> search(BSTNode<T> curr,T element) {
		if(curr.isEmpty()){
			return curr;
		}
		BSTNode<T> res;
		if(curr.getData().equals(element)){
			res = curr;
		}else{
			if(curr.getData().compareTo(element) > 0){
				res = search((BSTNode<T>) curr.getLeft(), element);
			}else {
				res = search((BSTNode<T>) curr.getRight(), element);
			}
		}
		return res;
	}

		@Override
	public void insert(T element) {
		insert(this.getRoot(), element);
	//	if(this.getRoot().getLeft() != null && this.getRoot().getLeft().getLeft() != null) {
	//		System.out.println(this.root.getLeft().getLeft().toString());
	//	}
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
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(this.isEmpty()){
			return null;
		}
		return maximum(this.getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> aux){

		if(aux.getRight().isEmpty()){
			return aux;
		}else{
			return maximum((BSTNode<T>)aux.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> aux){
		if(this.isEmpty()){
			return null;
		}
		if(aux.getLeft().isEmpty()){
			return aux;
		}else{
			return minimum((BSTNode<T>)aux.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
