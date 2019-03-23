package produto;

import java.util.List;

/**
 * Classe que representa um repositório de produtos usando arrays como estrutura
 * sobrejacente. Alguns métodos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercício, o erro será
 * representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que você deve utilizar a estrutura de dados array e não
 * implementações de array prontas da API Collections de Java (como ArrayList,
 * por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutoNaoPerecivelArray<T extends Produto> {
	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private Produto[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;


	public RepositorioProdutoNaoPerecivelArray(int size) {
		super();
		this.produtos = new Produto[size];
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		int indice = -1;
		for(int i = 0; i < this.produtos.length; i++){
			if(this.produtos[i].getCodigo() == codigo){
				indice = i;
			}
		}
		return indice;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		boolean existe = false;

		for(Produto p: this.produtos){
			if(p.getCodigo() == codigo){
				existe = true;
			}
		}

		return existe;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(ProdutoNaoPerecivel produto) {
		this.produtos[this.index] = produto;
		this.index++;

	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(Produto produto) {

		for(int i = 0; i< this.produtos.length; i++){
			if(this.produtos[i].getCodigo() == produto.getCodigo()){
				this.produtos[i] = produto;
			}
		}
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		int i = 0;
		int achadoEm = 0;
		boolean naoAchou = true;
		while(i < this.produtos.length && naoAchou){
			if(this.produtos[i].getCodigo() == codigo){
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

		for(i = achadoEm; i < this.produtos.length - 1; i++){
			this.swap(i, i+1);
		}
		this.index--;



	}

	private void swap(int indice1, int indice2){
		Produto p1 = this.produtos[indice1];
		this.produtos[indice1] = this.produtos[indice2];
		this.produtos[indice2] = p1;

	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public Produto procurar(int codigo) {
		for(Produto p: this.produtos){
			if(p.getCodigo() == codigo){
				return p;
			}
		}
		throw new IllegalArgumentException("Produto nao encontrado.");
	}

}
