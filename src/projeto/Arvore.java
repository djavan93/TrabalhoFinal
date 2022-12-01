package projeto;

import java.util.ArrayList;
import java.util.List;

public class Arvore {
	private List<Aresta> arestas;
	private int custo_Total;
	
	public Arvore() {
		arestas = new ArrayList<Aresta>();
	}
	
	public void adicionar_Aresta(Aresta aresta) {
		arestas.add(aresta);
		calcular_custo();
	}
	
	public int get_Custo_Total() {
		return custo_Total;
	}
	
	private void calcular_custo() {
		int custo = 0;
		for(Aresta x : arestas) {
			custo += x.get_Custo();
		}
		custo_Total = custo;
	}
}
