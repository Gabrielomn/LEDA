package vetor;

import javax.management.relation.RoleUnresolved;
import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T> {

	// O array interno onde os objetos manipulados são guardados
	private Object[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = -1;
		this.arrayInterno = new Object[tamanho];
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		if(!this.isCheio()){
			this.arrayInterno[this.indice] = o;
		}
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		int i = 0;
		int achadoEm = 0;
		boolean naoAchou = true;
		while(i < this.arrayInterno.length && naoAchou){
			if(this.arrayInterno[i].equals(o)){
				achadoEm = i;
				naoAchou = false;
			}
			else{
				i++;
			}
		}

		if(naoAchou){
			throw new RuntimeException();
		}

		for(i = achadoEm; i < this.arrayInterno.length - 1; i++){
			this.swap(i, i+1);
		}
		this.indice--;
		return (T) this.arrayInterno[this.indice];
	}

	private void swap(int indice1, int indice2){
		Object p1 = this.arrayInterno[indice1];
		this.arrayInterno[indice1] = this.arrayInterno[indice2];
		this.arrayInterno[indice2] = p1;

	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		for(Object obj: this.arrayInterno){
			if (o.equals(obj)){
				return (T) obj;
			}
		}
		throw new RuntimeException();
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		boolean vazio = false;
		if(this.indice == 0) {
			vazio = true;
		}

		return vazio;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		boolean cheio = false;
		if(this.indice == this.tamanho){
			cheio = true;
		}
		return cheio;
	}

}
