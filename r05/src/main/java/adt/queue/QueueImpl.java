package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		this.array = (T[]) new Object[size];
		this.tail = -1;
		this.head = 0;
	}

	@Override
	public T head() {
		if(this.tail == -1) {
			return null;
		}else {
			return this.array[head];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.head == 0 && this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == array.length - 1;
	}

	private void shiftLeft() {
		int i = 1;
		while(i < this.array.length) {
			this.array[i - 1] = this.array[i];
			i++;
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(!this.isFull()) {
			this.array[tail + 1] = element;
			this.tail++;
		}else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if(!this.isEmpty()) {
			T element= this.array[head];
			this.shiftLeft();
			return element;
		}else {
			throw new QueueUnderflowException();
		}
	}

}
