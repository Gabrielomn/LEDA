package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;
	protected T last;
	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(element != null){

			if(this.top.size() < this.size){
				this.last = element;
				this.top.insert(element);
			}else{
				throw new StackOverflowException();
			}
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if(this.top.size() == 0){
			throw new StackUnderflowException();
		}else{
			this.top.removeLast();
		}

	}

	@Override
	public T top() {
		// TODO Implement the method
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean isEmpty() {
		// TODO Implement the method
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean isFull() {
		// TODO Implement the method
		throw new UnsupportedOperationException("Method not implemented");
	}

}
