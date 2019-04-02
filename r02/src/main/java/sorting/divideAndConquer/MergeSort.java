package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null){
			return;
		}


		if(leftIndex >= rightIndex){
			return;
		} else	{
			int middle = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);
			merge(array, leftIndex, middle, rightIndex);
		}
	}


	private void merge(T[] array, int leftIndex,int middle, int rightIndex){
		T[] aux = (T[]) new Comparable[array.length];

		for (int i = leftIndex; i<= rightIndex; i++ ){
			aux[i] = array[i];
		}

		int i = leftIndex; //indice que aponta o começo da primeira parte do array
		int j = middle + 1; //indice que aponta o começo da segunda parte do array
		int n = leftIndex; //indice que vai indicar aonde estamos atualizando os elementos do array

		while(true){

			/*caso um desses seja verdade significa que um dos "lados" ja nao contem mais elementos a serem inseridos
			assim sendo, basta inserir o resto dos elementos do lado restante.*/
			if(i > middle || j > rightIndex){
				break;
			}

			if(aux[i].compareTo(aux[j]) < 0){
				array[n] = aux[i];
				i++;
			}else{
				array[n] = aux[j];
				j++;
			}
			n++;

		}

		while (i<= middle){
			array[n] = aux[i];
			i++;
			n++;
		}
		while (j<= rightIndex){
			array[n] = aux[j];
			j++;
			n++;
		}
	}


}
