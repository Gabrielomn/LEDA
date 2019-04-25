package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
			}else{
				((RecursiveDoubleLinkedListImpl) this.getNext()).insertFirst(this.getData());
				this.setData(element);
			}
		}
	}

	@Override
	public void insert(T element){
		if(element != null){
			if(this.isEmpty()){
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
				((RecursiveDoubleLinkedListImpl) this.getNext()).setPrevious(this);
			}else{
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!this.isEmpty()) {
			if(!this.getNext().isEmpty()){
				this.setData(this.getNext().getData());
				((RecursiveDoubleLinkedListImpl) this.getNext()).removeFirst();
			}else{
				this.setData(null);
			}
		}
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty()){
			if(this.getNext().isEmpty()){
				this.setData(null);
			}else{
				((RecursiveDoubleLinkedListImpl) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
