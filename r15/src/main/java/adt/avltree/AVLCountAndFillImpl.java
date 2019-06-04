package adt.avltree;

import adt.bst.BSTNode;

import java.util.*;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}


	// AUXILIARY
	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (balance > 1) {
			if (calculateBalance((BSTNode<T>)node.getLeft()) < 0) {
				simpleLeftRotation((BSTNode<T>) node.getLeft());
				simpleRightRotation(node);
				this.LRcounter++;
			}else {
				simpleRightRotation(node);
				this.LLcounter++;
			}
		} else if (balance < -1) {
			if (calculateBalance((BSTNode<T>)node.getRight()) > 0) {
				simpleRightRotation((BSTNode<T>)node.getRight());
				simpleLeftRotation(node);
				this.RLcounter++;
			}
			else {
				simpleLeftRotation(node);
				this.RRcounter++;
			}
		}
	}
	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		List<T> list = Arrays.asList(array);
		list.addAll(Arrays.asList(this.postOrder()));
		Collections.sort(list);
		this.root = new BSTNode<T>();

		Map<Integer, List<T>> niveis = new TreeMap<>();
		binaryInsert(list, 0, list.size()-1, niveis, 0);
		for(Integer i: niveis.keySet()){
			List<T> aux = niveis.get(i);
			insertAll(aux);
		}
	}


	/*A ideia aqui é criar um mapa onde cada chave corresponde a um nivel da AVL, assim, eu posso ter a garantia
		de inserir elementos sem nunca ter que rebalancear
	*/
	private void binaryInsert(List<T> aux, int left, int right, Map<Integer, List<T>> niveis, int nivelAtual) {
		if(left > right){
			return;
		}
		int meio = (left+right)/2;
		if(niveis.containsKey(nivelAtual)){
			niveis.get(nivelAtual).add(aux.get(meio));
		}else{
			niveis.put(nivelAtual, new ArrayList<T>());
			niveis.get(nivelAtual).add(aux.get(meio));
		}

		binaryInsert(aux, meio+1, right, niveis, nivelAtual+1);
		binaryInsert(aux, left, meio -1, niveis, nivelAtual+1);
	}

	private void insertAll(List<T> aux){
		for(T e: aux){
			this.insert(e);
		}
	}
}
