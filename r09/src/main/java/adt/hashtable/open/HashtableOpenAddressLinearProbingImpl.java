package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
												 HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int probe = 0;
			int hashElemento = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElemento] != null && this.table[hashElemento].equals(new DELETED())) {

				if (probe == this.table.length) {
					throw new HashtableOverflowException();
				}
				probe++;
				hashElemento = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
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
			int hashElemento = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
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
				hashElemento = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
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
			int hashElemento = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElemento] != null) {

				if (probe == this.table.length) {
					return null;
				}
				if (this.table[hashElemento].equals(element)) {
					break;
				}
				probe++;
				hashElemento = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
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
			int hashElemento = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElemento] != null) {

				if (probe == this.table.length) {
					return -1;
				}
				if (this.table[hashElemento].equals(element)) {
					break;
				}
				probe++;
				hashElemento = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
			}
			return hashElemento;

		} else {
			return -1;
		}

	}
}