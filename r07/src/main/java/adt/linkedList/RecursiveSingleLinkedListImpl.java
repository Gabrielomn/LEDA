package adt.linkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if(!this.isEmpty()){
			return 1 + this.getNext().size();
		}else{
			return 0;
		}
	}

	@Override
	public T search(T element) {
		if(this.isEmpty()){
			return null;
		}else{
			if(this.getData().equals(element)){
				return this.getData();
			}else{
				return this.getNext().search(element);
			}
		}

	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<>());
			} else if(this.getNext()!=null){
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null) {
			if (this.getData().equals(element)) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				if (this.getNext() != null) {
					this.getNext().remove(element);
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		ArrayList<T> elements = new ArrayList<T>();
		this.listToArray(elements);
		return (T[]) elements.toArray();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

	public void listToArray(ArrayList<T> array){
		if(!this.isEmpty()){
			array.add(this.getData());
		}
		if(this.getNext() != null){
			this.getNext().listToArray((array));
		}
	}

}
