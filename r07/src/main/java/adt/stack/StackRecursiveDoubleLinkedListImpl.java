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

			if(!this.isFull()){
				this.top.insertFirst(element);
			}else{
				throw new StackOverflowException();
			}
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if(this.isEmpty()){
			throw new StackUnderflowException();
		}else{
			T last = this.top.toArray()[0];
			this.top.removeFirst();
			return last;
		}

	}

	@Override
	public T top() {
		if(this.isEmpty()){
			return null;
		}else{
			return this.top.toArray()[0];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.top.size() == 0;
	}

	@Override
	public boolean isFull() {
		return this.top.size() >= this.size;
	}

}
