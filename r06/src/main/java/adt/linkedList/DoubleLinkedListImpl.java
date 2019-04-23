package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {

		this.head = this.last = new DoubleLinkedListNode<>(null, new DoubleLinkedListNode<>(),
				new DoubleLinkedListNode<>());
	}

	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				DoubleLinkedListNode<T> aux = (new DoubleLinkedListNode<>(element, new DoubleLinkedListNode(),
						new DoubleLinkedListNode<>()));
				this.setHead(aux);
				this.setLast(aux);

			} else {
				DoubleLinkedListNode<T> aux = (new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(),
						this.getLast()));
				this.getLast().setNext(aux);
				this.setLast(aux);
			}
		}

	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {

			if (!this.isEmpty()) {
				SingleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
				DoubleLinkedListNode<T> newSecond = new DoubleLinkedListNode<T>();
				newSecond.setData(this.getHead().getData());
				newSecond.setNext(this.getHead().getNext());
				newSecond.setPrevious(this.getHead());
				newHead.setData(element);
				this.setHead(newHead);
				this.getHead().setNext(newSecond);
			} else {
				this.insert(element);
			}
		}
	}

	@Override
	public void removeFirst() {

		if (!this.isEmpty()) {

			this.head = this.head.getNext();
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			if (this.size() == 1) {
				this.head = this.last = new DoubleLinkedListNode<>(null, new DoubleLinkedListNode<>(),new DoubleLinkedListNode<>());
				} else {
				this.getLast().getPrevious().setNext(new DoubleLinkedListNode(null, new DoubleLinkedListNode<>(), this.getLast().getPrevious()));
				this.setLast(this.getLast().getPrevious());
			}
		}

	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	public DoubleLinkedListNode<T> getHead() {
		return (DoubleLinkedListNode<T>) this.head;
	}

	public boolean isEmpty() {
		return this.head.isNIL();
	}
}
