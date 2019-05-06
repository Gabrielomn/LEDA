package adt.linkedList;
public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return (this.head.isNIL());
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			aux = aux.getNext();
			size++;
		}
		return size;

	}

	@Override
	public T search(T element) {
		T res = null;
		if (!this.isEmpty() && element != null) {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL()) {
				if (aux.getData().equals(element)) {
					res = aux.getData();
					break;
				} else {
					aux = aux.getNext();
				}
			}

		}
		return res;
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			SingleLinkedListNode<T> aux = this.head;
			while(!aux.isNIL()) {
				aux = aux.getNext();
			}
			aux.setData(element);
			aux.setNext(new SingleLinkedListNode<>());
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !this.isEmpty()) {
			if(this.head.getData().equals(element)) {
				this.head = this.head.getNext();
			}else {
				SingleLinkedListNode<T> aux = this.head;
				while(!aux.isNIL()) {

					if(!aux.getNext().isNIL() && aux.getNext().getData().equals(element)) {
						aux.setNext(aux.getNext().getNext());
						break;

					}else {
						aux = aux.getNext();
					}

				}

			}

		}

	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		int indice = 0;
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.isNIL()) {
			array[indice] = aux.getData();
			aux = aux.getNext();
			indice++;
		}


		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}