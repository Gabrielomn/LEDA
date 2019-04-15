package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(this.isEmpty() ) {
			this.head = 0; 
			this.tail = 0;
		}
		
		
		if(!this.isFull()) {
			this.array[(tail) % this.array.length] = element;
			this.elements++;
			this.tail++;

		}else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(!this.isEmpty()) {
			T element = this.array[(head)%this.array.length];
			this.elements--;
			this.head++;
			return element;
		}else {
			throw new QueueUnderflowException();
		}
	}

	@Override
	public T head() {
		if (!this.isEmpty()) {
			return this.array[head];
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}

}
