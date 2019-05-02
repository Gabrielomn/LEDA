package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int probe = 0;
			int hashElemento = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElemento] != null) {

				if (probe == this.table.length) {
					throw new HashtableOverflowException();
				}
				probe++;
				hashElemento = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			}
			this.elements++;
			this.COLLISIONS += probe;
			this.table[hashElemento] = element;

		}
	}

	@Override
	public void remove(T element) {
		boolean found = false;
		if (element != null) {
			int probe = 0;
			int hashElemento = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElemento] != null) {

				if (probe == this.table.length) {
					return;
				}
				if (this.table[hashElemento].equals(element)) {
					found = true;
					this.table[hashElemento] = new DELETED();
					break;
				}
				probe++;
				hashElemento = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			}
			if(found) {
				this.elements--;
				this.COLLISIONS -= probe;
			}
		}
	}

	@Override
	public T search(T element) {
		if (element != null) {
			int probe = 0;
			int hashElemento = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElemento] != null) {

				if (probe == this.table.length) {
					return null;
				}
				if (this.table[hashElemento].equals(element)) {
					break;
				}
				probe++;
				hashElemento = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			}
			return (T) this.table[hashElemento];

		} else {
			return null;
		}

	}

	@Override
	public int indexOf(T element) {
		if (element != null) {
			int probe = 0;
			int hashElemento = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElemento] != null) {

				if (probe == this.table.length) {
					return -1;
				}
				if (this.table[hashElemento].equals(element)) {
					break;
				}
				probe++;
				hashElemento = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			}
			return hashElemento;

		} else {
			return -1;
		}

	}
}
