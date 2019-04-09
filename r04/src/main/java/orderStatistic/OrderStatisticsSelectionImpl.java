package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {


	private T maior;
	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		if(k > array.length){
			return null;
		}
		if(array== null){
			return null;
		}

		if(this.maior == null){
			this.maior = findMaior(array);
		}
		if(k == 1){
			return findMenor(array);
		}else{
			return find(array, getOrderStatistics(array, k -1));

		}
	}

	private T find(T[] array, T limiteInferior){
		T menor = this.maior;

		for (int i = 0; i < array.length; i++){

			if(verificaSeEstaNoIntervalo(array[i], limiteInferior, menor)) {
				menor = array[i];
			}
		}
		return menor;

	}


	private T findMenor(T[] array){
		T menor = array[0];
		for(int i = 0; i < array.length; i++){
			if (array[i].compareTo(menor) < 0){
				menor = array[i];
			}
		}


		return menor;
	}

	private T findMaior(T[] array){
		T maior = array[0];

		for(int i = 0; i < array.length; i++){
			if(array[i].compareTo(maior) > 0){
				maior = array[i];

			}
		}

		return maior;
	}


	private boolean verificaSeEstaNoIntervalo(T elemento, T limiteInferior, T menorAtual){
		return (elemento.compareTo(menorAtual) < 0 && elemento.compareTo(limiteInferior) > 0);
	}

}
