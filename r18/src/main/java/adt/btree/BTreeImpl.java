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
			int saida = node.size();
			for (int i = 0; i < node.children.size(); i++) {
				saida += size(node.getChildren().get(i));
			}

			return saida;
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
		int index = 0;

		while (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) > 0) {
			index++;
		}

		if (node.isLeaf()) {
			node.addElement(element);
			node.addChild(index, new BNode<T>(this.order));

			if (node.getMaxKeys() < node.size()) {
				node.split();

				while (node.parent != null) {
					node = node.parent;
				}
				this.root = node;
			}
		} else {
			insert(node.getChildren().get(index), element);
		}

	}

	private void split(BNode<T> node) {

		node.split();

	}

	private void promote(BNode<T> node) {
		node.promote();
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

}