package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		this.array = (T[]) new Object[size];
		this.tail = -1;
	}

	@Override
	public T head() {
		if (this.tail == -1) {
			return null;
		} else {
			return this.array[0];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == array.length - 1;
	}

	private void shiftLeft() {
			for(int i = 1; i < array.length; i++){
				this.array[i - 1] = this.array[i];

		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (!this.isFull()) {
				this.array[tail + 1] = element;
				this.tail++;
			} else {
				throw new QueueOverflowException();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if (!this.isEmpty()) {
			T element = this.array[0];
			this.tail--;
			this.shiftLeft();
			return element;
		} else {
			throw new QueueUnderflowException();
		}
	}

}
