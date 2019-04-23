package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}
 
	@Override
	public void push(T element) throws StackOverflowException {
		if(element != null) {
			if(!this.isFull()) {
				this.top.insertFirst(element);
			}else {
				throw new StackOverflowException();
			}
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		T element;
		if(!this.isEmpty()) {
			element = ((DoubleLinkedListImpl<T>) this.top).getHead().getData();
			this.top.removeFirst();
		}else {
			throw new StackUnderflowException();
		}
		return element;
		
	}

	@Override
	public T top() {
		T element = ((DoubleLinkedListImpl<T>) this.top).getHead().getData();
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
		
	}

	@Override
	public boolean isFull() {
		return this.size <= this.top.size();
	}

}
