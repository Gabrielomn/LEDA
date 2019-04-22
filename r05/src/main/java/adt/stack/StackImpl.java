package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (this.top != -1) {
			return this.array[top];
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		if (this.top == -1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (this.top == this.array.length - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (!this.isFull()) {
				this.array[this.top + 1] = element;
				this.top++;
			} else {
				throw new StackOverflowException();
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!this.isEmpty()) {
			this.top--;
			return this.array[top + 1];
		} else {
			throw new StackUnderflowException();
		}
	}

}
