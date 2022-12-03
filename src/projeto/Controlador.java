package projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Controlador {
	private int num_Nodes;
	private int max_Ligacoes;
	private ConjuntoDisjunto melhor_Conjunto_Disjunto;
	
	public Controlador(Queue entradas) {
		num_Nodes = (int)entradas.poll();
		max_Ligacoes = (int)entradas.poll();
		
		//Aqui gera todos as combinações de binários e envia para ConjuntoDisjunto, 
		//se for um conjunto válido e melhor q o anterior, adiciona em melhor_Conjunto_Disjunto
	}
}
