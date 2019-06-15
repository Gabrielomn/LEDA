package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		if(this.isEmpty()){
			return -1;
		}
		return height(this.root);
	}

	private int height(BNode<T> node) {
		if (node.isLeaf())
			return 0;
		return 1 + height(node.getChildren().getFirst());
	}

	@Override
	public BNode<T>[] depthLeftOrder() {

		BNode<T>[] array = new BNode[this.countNodes(this.root)];

		depthLeftOrder(array, 0, root);

		return array;
	}

	private int countNodes(BNode<T> node) {
		if (node.isEmpty())
			return 0;

		int cont = 1;

		for (int i = 0; i < node.children.size(); i++) {
			cont += countNodes(node.children.get(i));
		}
		return cont;
	}

	public int depthLeftOrder(BNode<T> array[], int index, BNode<T> node) {
		if (!node.isEmpty()) {
			array[index++] = node;

			for (int i = 0; i < node.children.size(); i++) {
				index = depthLeftOrder(array, index, node.children.get(i));
			}
		}
		return index;
	}

	@Override
	public int size() {
		return this.size(this.getRoot());
	}

	private int size(BNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		} else {
			int cont = node.size();
			for(BNode<T> a:node.getChildren()){
				cont += size(a);
			}
			return cont;
		}
	}

	@Override
	public BNodePosition<T> search(T element) {
		if (element != null) {
			return search(this.getRoot(), element);
		}
		return new BNodePosition<>();
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		int i = 0;
		while (i < node.getElements().size() && element.compareTo(node.getElementAt(i)) > 0) {
			i++;
		}

		if (i < node.getElements().size() && element.equals(node.getElementAt(i))) {
			return new BNodePosition<T>(node, i);
		}
		if (node.isLeaf()) {
			return new BNodePosition<T>();
		}

		return search(node.getChildren().get(i), element);
	}

	@Override
	public void insert(T element) {
		if (element == null || this.search(element) == null) {
			return;
		} else {
			insert(this.getRoot(), element);
		}

	}

	private void insert(BNode<T> node, T element) {

		if (node.isLeaf()) {
			node.addElement(element);
		}

		else{
			int index = 0;
			while (index < node.getElements().size() && node.getElementAt(index).compareTo(element) < 0) {
				index++;
			}
			insert(node.getChildren().get(index), element);
		}

		if (node.getMaxKeys() < node.getElements().size()) {
			split(node);
		}

	}

	private void split(BNode<T> node) {

		int median = node.getElements().size() / 2;
		T middle = node.getElements().get(median);

		BNode<T> left = new BNode<T>(this.order);
		BNode<T> right = new BNode<T>(this.order);



		for (int i = 0; i < median; i++){
			left.addElement(node.getElementAt(i));
		}
		for (int i = median + 1; i < node.getElements().size(); i++){
			right.addElement(node.getElementAt(i));
		}

		if (node.equals(this.getRoot())) {
			BNode<T> newRoot = new BNode<T>(order);
			newRoot.addElement(middle);
			node.setParent(newRoot);
			this.root = newRoot;

			for (int i = 0; i < node.getChildren().size(); i++) {
				if (i <= median) {
					left.addChild(i, node.getChildren().get(i));
				} else {
					right.addChild(i - median - 1, node.getChildren().get(i));
				}
			}

			newRoot.addChild(0, left);
			newRoot.addChild(1, right);
			newRoot.getChildren().get(0).setParent(newRoot);
			newRoot.getChildren().get(1).setParent(newRoot);

		}else {

			promote(node, left,right);
		}

	}

	private void promote(BNode<T> node, BNode<T> left, BNode<T> right) {

		node.addChild(0, left);
		node.addChild(1, right);
		T medianElement = findMiddle(node);

		node.getElements().clear();
		node.addElement(medianElement);

		BNode<T> parent = node.getParent();

		if (parent != null) {
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			int index = parent.getChildren().indexOf(node);
			parent.addElement(medianElement);
			parent.addChild(index, node.getChildren().get(0));
			parent.addChild(index + 1, node.getChildren().get(1));
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			parent.getChildren().remove(node);
		}
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	private T findMiddle(BNode<T> node){
		return node.getElements().get((node.getElements().size())/2);
	}

}