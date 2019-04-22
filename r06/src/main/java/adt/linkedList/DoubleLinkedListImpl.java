package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<>();
		this.last = newLast;
		this.head = new DoubleLinkedListNode<>(null, null, newLast);
	}

	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.head.setData(element);
				this.head.setNext(this.getLast());
			} else {
				DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<>(null, null, this.getLast());
				this.getLast().setData(element);
				this.getLast().setNext(aux);
				this.setLast(aux);

//				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode) this.head.getNext();
//				while (!aux.isNIL()) {
//					aux = (DoubleLinkedListNode) aux.getNext();
//				}
//
//				aux.setData(element);
//				aux.setNext(new DoubleLinkedListNode<>());
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
				this.getHead().setData(element);
				this.getHead().setNext(this.getLast());
				this.setLast(new DoubleLinkedListNode<T>(null, null, this.getHead()));
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
		
		DoubleLinkedListNode aux = new DoubleLinkedListNode(null, null, this.getLast().getPrevious().getPrevious());
		this.setLast(aux);
		
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
