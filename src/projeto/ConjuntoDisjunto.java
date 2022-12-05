package projeto;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoDisjunto {
	private List<Nodo> nodos;
	private int max_Ligacoes;
	private int custo_Total;
	
	public ConjuntoDisjunto(int num_Nodes) {
		nodos = new ArrayList<Nodo>();
		max_Ligacoes = 0;
		custo_Total = 0;
		

		//Inicia os nós
		for(int i = 0; i < num_Nodes; i++) {
			nodos.add(gerar(i));
		}
		
		//aqui trata os binarios recebidos e gera o Conjunto Disjunto
		for(int i = 0; i < Combinacoes.num_ligacoes(); i++) {
			fundir(nodos.get(Combinacoes.getLigacao(i).getNodo1()), nodos.get(Combinacoes.getLigacao(i).getNodo2()), Combinacoes.getLigacao(i).getCusto());	
		}
		for(var x : nodos) {
			if(x.get_Pai() != null) {
				x.adicionar_Num_Ligacoes(1);
			}
		}
		
		atualiza_Conj_Dis();
	}
	
	//Operações de Conjunto Disjunto
	private Nodo gerar(int chave) {
		return new Nodo(chave);	
	}
	
	private Nodo buscar(Nodo nodo) {
		if(nodo.get_Pai() == null) {
			return nodo;
		}
		else {
			return buscar(nodo.get_Pai().get_Destino());
		}
	}
	
	private void fundir(Nodo nodo1, Nodo nodo2, int custo) {
		if(buscar(nodo1) != buscar(nodo2)) {
			if(buscar(nodo1).get_Ordem() > buscar(nodo2).get_Ordem()) {
				//System.out.println("1");
				mudar_Destinos(nodo1, nodo2, custo);
			}
			else if(buscar(nodo1).get_Ordem() < buscar(nodo2).get_Ordem()) {
				//System.out.println("2");
				mudar_Destinos(nodo2, nodo1, custo);
			}
			else {
				if(nodo1.get_Chave() < nodo2.get_Chave()) {
					//System.out.println("3");
					mudar_Destinos(nodo1, nodo2, custo);
				}
				else {
					//System.out.println("4");
					mudar_Destinos(nodo2, nodo1, custo);
				}
			}
		}
	}
	
	//faz nodo2 apontar para nodo1
	private void mudar_Destinos(Nodo nodo1, Nodo nodo2, int custo) {
		if(nodo2.get_Pai() != null) {
			mudar_Pais(nodo2.get_Pai().get_Destino(), nodo2);
			if(nodo2.get_Ordem() <= nodo2.get_Pai().get_Destino().get_Ordem()) {
				nodo2.set_Ordem(nodo2.get_Pai().get_Destino().get_Ordem() + 1);
			}
		}
		nodo2.set_Pai(new Aresta(custo, nodo1));
		nodo1.adicionar_Num_Ligacoes(1);
		if(nodo1.get_Ordem() <= nodo2.get_Ordem()) {
			nodo1.set_Ordem(nodo2.get_Ordem() + 1);
		}
		if(nodo1.get_Pai() != null) {
			alterar_Ordens(nodo1.get_Pai().get_Destino(), nodo1.get_Ordem());
		}
	}
	
	//inverte a direção das arestas no menor conjunto disjunto
	private void mudar_Pais(Nodo nodo, Nodo nodo_Anterior) {
		if(nodo.get_Pai() == null) {
			nodo.set_Ordem(0);
			nodo.adicionar_Num_Ligacoes(-1);
		}
		else {
			mudar_Pais(nodo.get_Pai().get_Destino(), nodo);
			if(nodo.get_Ordem() <= nodo.get_Pai().get_Destino().get_Ordem()) {
				nodo.set_Ordem(nodo.get_Pai().get_Destino().get_Ordem() + 1);
				
			}
		}
		nodo.set_Pai(new Aresta(nodo_Anterior.get_Pai().get_Custo(), nodo_Anterior));
		nodo_Anterior.adicionar_Num_Ligacoes(1);
	}
	
	private void alterar_Ordens(Nodo nodo, int ordem) {
		if(nodo.get_Ordem() <= ordem) {
			nodo.set_Ordem(ordem + 1);
		}
		if(nodo.get_Pai() != null) {
			alterar_Ordens(nodo.get_Pai().get_Destino(), nodo.get_Ordem());
		}
	}
	
	public int get_Max_Ligacoes() {
		return max_Ligacoes;
	}
	
	
	public void imprimir() {
		String retorno = "";
		for(int i = 0; i < nodos.size(); i++) {
			if(nodos.get(i).get_Pai() != null) {
				retorno = "[" + nodos.get(i).get_Chave() + "-" + nodos.get(i).get_Pai().get_Destino().get_Chave() + "] = " + nodos.get(i).get_Pai().get_Custo() + "\n" + retorno;
			}
		}
		retorno = retorno + "Custo Total = " + custo_Total + "\n";
		retorno = retorno + "Máximo de ligações = " + max_Ligacoes;
		retorno = "[Origem - Destino] = Custo\n" + retorno;
		System.out.println(retorno);
	}
	
	public void atualiza_Conj_Dis() {
		for(int i = 0; i < nodos.size(); i++) {
			if(nodos.get(i).get_Num_ligacoes() > max_Ligacoes) {
				max_Ligacoes = nodos.get(i).get_Num_ligacoes();
			}
			if(nodos.get(i).get_Pai() != null) {
				custo_Total += nodos.get(i).get_Pai().get_Custo();
			}
		}
	}
	
	public int get_Custo_Total() {
		return custo_Total;
	}
	
	public boolean validar_Conjunto() {
		int contador = 0;
		for(int i = 0; i < nodos.size(); i++) {
			if(nodos.get(i).get_Pai() == null) {
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
