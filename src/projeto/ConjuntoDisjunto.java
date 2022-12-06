package projeto;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoDisjunto {
	private List<Nodo> nodos;
	private int maxLigacoes;
	private int custoTotal;
	
	public ConjuntoDisjunto(int num_Nodes) {
		nodos = new ArrayList<Nodo>();
		maxLigacoes = 0;
		custoTotal = 0;
		

		//Inicia os nós
		for(int i = 0; i < num_Nodes; i++) {
			nodos.add(gerar(i));
		}
		
		//aqui gera o Conjunto Disjunto com a combinação recebida
		for(int i = 0; i < Combinacoes.numLigacoes(); i++) {
			fundir(nodos.get(Combinacoes.getLigacao(i).getNodo1()), nodos.get(Combinacoes.getLigacao(i).getNodo2()), Combinacoes.getLigacao(i).getCusto());	
		}
		
		//adiciona uma ligação para todos os nós, exceto o nó raiza
		for(var x : nodos) {
			if(x.getPai() != null) {
				x.adicionarNumLigacoes(1);
			}
		}
		
		atualizaConjDis();
	}
	
	//Operações de Conjunto Disjunto:
	//Gera um novo nó
	private Nodo gerar(int chave) {
		return new Nodo(chave);	
	}
	
	//Retorna o nó raiz
	private Nodo buscar(Nodo nodo) {
		if(nodo.getPai() == null) {
			return nodo;
		}
		else {
			return buscar(nodo.getPai().getDestino());
		}
	}
	
	private void fundir(Nodo nodo1, Nodo nodo2, int custo) {
		//Primeiro vefifica se eles não estão no mesmo conjunto
		if(buscar(nodo1) != buscar(nodo2)) {
			//O conjunto com a ordem menor aponta para o de ordem maior
			if(buscar(nodo1).getOrdem() > buscar(nodo2).getOrdem()) {
				mudarDestinos(nodo1, nodo2, custo);
			}
			else if(buscar(nodo1).getOrdem() < buscar(nodo2).getOrdem()) {
				mudarDestinos(nodo2, nodo1, custo);
			}
			else {
				//No caso em que eles são de mesma ordem, o nó com a chave maior
				//aponta para o nó com chave menor
				if(nodo1.getChave() < nodo2.getChave()) {
					mudarDestinos(nodo1, nodo2, custo);
				}
				else {
					mudarDestinos(nodo2, nodo1, custo);
				}
			}
		}
	}
	
	//faz nodo2 apontar para nodo1
	private void mudarDestinos(Nodo nodo1, Nodo nodo2, int custo) {
		//Caso o nodo2 não seja o nó raiz, ele inverte o destino da aresta
		if(nodo2.getPai() != null) {
			mudarPais(nodo2.getPai().getDestino(), nodo2);
			if(nodo2.getOrdem() <= nodo2.getPai().getDestino().getOrdem()) {
				nodo2.setOrdem(nodo2.getPai().getDestino().getOrdem() + 1);
			}
		}
		nodo2.setPai(new Aresta(custo, nodo1));
		nodo1.adicionarNumLigacoes(1);
		if(nodo1.getOrdem() <= nodo2.getOrdem()) {
			nodo1.setOrdem(nodo2.getOrdem() + 1);
		}
		//Se o nodo1 não for o nó representante do conjunto, ele atualiza
		//a ordem de seus pais
		if(nodo1.getPai() != null) {
			alterarOrdens(nodo1.getPai().getDestino(), nodo1.getOrdem());
		}
	}
	
	//inverte a direção das arestas no menor conjunto disjunto
	private void mudarPais(Nodo nodo, Nodo nodo_Anterior) {
		if(nodo.getPai() == null) {
			nodo.setOrdem(0);
			nodo.adicionarNumLigacoes(-1);
		}
		else {
			mudarPais(nodo.getPai().getDestino(), nodo);
			if(nodo.getOrdem() <= nodo.getPai().getDestino().getOrdem()) {
				nodo.setOrdem(nodo.getPai().getDestino().getOrdem() + 1);
				
			}
		}
		nodo.setPai(new Aresta(nodo_Anterior.getPai().getCusto(), nodo_Anterior));
		nodo_Anterior.adicionarNumLigacoes(1);
	}
	
	//Altera a ordem do novo conjunto disjunto
	private void alterarOrdens(Nodo nodo, int ordem) {
		if(nodo.getOrdem() <= ordem) {
			nodo.setOrdem(ordem + 1);
		}
		if(nodo.getPai() != null) {
			alterarOrdens(nodo.getPai().getDestino(), nodo.getOrdem());
		}
	}
	
	public int get_Max_Ligacoes() {
		return maxLigacoes;
	}
	
	public int getCustoTotal() {
		return custoTotal;
	}
	
	public void imprimir() {
		String retorno = "";
		for(int i = 0; i < nodos.size(); i++) {
			if(nodos.get(i).getPai() != null) {
				retorno = "[" + nodos.get(i).getChave() + "-" + nodos.get(i).getPai().getDestino().getChave() + "] = " + nodos.get(i).getPai().getCusto() + "\n" + retorno;
			}
		}
		retorno = retorno + "Custo Total = " + custoTotal + "\n";
		retorno = retorno + "Máximo de ligações = " + maxLigacoes;
		retorno = "[Origem - Destino] = Custo\n" + retorno;
		System.out.println(retorno);
	}
	
	//Atualiza o maxLigacoes e o custoTotal do conjunto disjunto
	public void atualizaConjDis() {
		for(int i = 0; i < nodos.size(); i++) {
			if(nodos.get(i).getNumligacoes() > maxLigacoes) {
				maxLigacoes = nodos.get(i).getNumligacoes();
			}
			if(nodos.get(i).getPai() != null) {
				custoTotal += nodos.get(i).getPai().getCusto();
			}
		}
	}
	
	//Verifica se existe apenas um nó raiz
	public boolean validarConjunto() {
		int contador = 0;
		for(int i = 0; i < nodos.size(); i++) {
			if(nodos.get(i).getPai() == null) {
				contador++;
			}
		}
		if(contador > 1) {
			return false;
		}
		else {
			return true;
		}
	}
}
