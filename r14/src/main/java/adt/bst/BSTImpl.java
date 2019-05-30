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
		if(this.isEmpty()){
			return -1;
		}
		return height(this.getRoot()) - 1;
	}

	protected int height(BSTNode<T> curr){
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
				if (curr.getData().compareTo(element) > 0) {
					res = search((BSTNode<T>) curr.getLeft(), element);
				} else {
					res = search((BSTNode<T>) curr.getRight(), element);
				}
			}
			return res;
		}
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
		if(aux.isEmpty()){
			return null;
		}
		if(aux.getRight().isEmpty()){
			return aux;
		}else{
			return maximum((BSTNode<T>)aux.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if(this.isEmpty()){
			return null;
		}
		return minimum(this.getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> aux){
		if(aux.isEmpty()){
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
		BSTNode<T> aux = search(element);
		if(!aux.isEmpty()){
			if(!aux.getRight().isEmpty()){
				return minimum((BSTNode<T>) aux.getRight());
			}else{
				return getUpperSucessor(aux);
			}
		}else{
			return null;
		}


	}

	private BSTNode<T> getUpperSucessor(BSTNode<T> aux) {
		if(this.getRoot().equals(aux)){
			return null;
		}
		BSTNode<T> parent = (BSTNode<T>) aux.getParent();
		if(parent.getLeft().equals(aux)){
			return parent;
		}else{

			return getUpperSucessor(parent);
		}

	}


	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> aux = search(element);
		if(!aux.isEmpty()){
			if(!aux.getLeft().isEmpty()){
				return maximum((BSTNode<T>) aux.getLeft());
			}else{
				return getUpperPredecessor(aux);
			}

		}else{
			return null;
		}

	}

	private BSTNode<T> getUpperPredecessor(BSTNode<T> aux) {
		if(this.getRoot().equals(aux)){
			return null;
		}

		BSTNode<T> parent = (BSTNode<T>) aux.getParent();
		if(parent.getRight().equals(aux)){
			return parent;
		}else{
			return getUpperPredecessor(parent);
		}
	}


	@Override
	public void remove(T element) {
		BSTNode<T> aux = search(element);
		if(!this.isEmpty()){
			if(this.getRoot().equals(aux)){
				aux = sucessor(element);
				if (aux == null){
					aux = predecessor(element);
				}if(aux == null) {
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
		}else if(curr.getRight().isEmpty() || curr.getLeft().isEmpty()){
			changeFatherAndSon(curr);
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
		if(parent.getLeft().equals(curr)){
			parent.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(parent).build());
		}else if(parent.getRight().equals(curr)){
			parent.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(parent).build());
		}
	}

	@Override
	public T[] preOrder() {
		T[] res = (T[]) new Comparable[this.size()];
		preOrder(this.getRoot(), res, 0);
		return res;

	}

	public int preOrder(BSTNode<T> curr, T[] arr, int index){
		if(!curr.isEmpty()){
			arr[index] = curr.getData();
			index++;
			index = preOrder((BSTNode<T>) curr.getLeft(), arr, index);
			index = preOrder((BSTNode<T>) curr.getRight(), arr, index);
		}
		return index;
	}

	@Override
	public T[] order() {
		T[] res = (T[]) new Comparable[this.size()];
		order(this.getRoot(), res, 0);
		return res;
	}

	private int order(BSTNode<T> curr, T[] arr, int index) {
		if(!curr.isEmpty()){

			index = order((BSTNode<T>)curr.getLeft(), arr, index);
			arr[index] = curr.getData();
			index++;
			index = order((BSTNode<T>)curr.getRight(), arr, index);
		}

		return index;
	}

	@Override
	public T[] postOrder() {
		T[] res = (T[]) new Comparable[this.size()];
		postOrder(this.getRoot(), res, 0);
		return res;
	}

	private int postOrder(BSTNode<T> curr, T[] arr, int index) {
		if(!curr.isEmpty()){
			index = postOrder((BSTNode<T>)curr.getLeft(), arr, index);
			index = postOrder((BSTNode<T>)curr.getRight(), arr, index);
			arr[index] = curr.getData();
			index++;
		}
		return index;
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