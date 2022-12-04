package projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Controlador {
	private int num_Nodes;
	private int max_Ligacoes;
	private ConjuntoDisjunto melhor_Conjunto_Disjunto;
	private int num_Combinacoes_possiveis;
	
	
	
	public Controlador(List<Integer> entradas) {
		num_Combinacoes_possiveis = 0;
		ConjuntoDisjunto conjDis;
		
		//o metodo poll() puxa os primeiros elementos da fila, ou seja, o numero de nós e o o maximo de ligações "d"
		num_Nodes = (int)entradas.remove(0);
		max_Ligacoes = (int)entradas.remove(0);
		
		//Aqui gera todos as combinações de binários e envia para ConjuntoDisjunto, 
		//se for um conjunto válido e melhor q o anterior, adiciona em melhor_Conjunto_Disjunto
		
		long binario = (long) Math.pow(2, num_Arestas());
		for(long i = 0; i < (long) Math.pow(2, num_Arestas()) - 1; i++) {
			binario++;
			String str_bin = Long.toBinaryString(binario);
			if(validar_Combinacao(str_bin)) {
				num_Combinacoes_possiveis++;
				conjDis = new ConjuntoDisjunto(str_bin, num_Nodes, entradas);
				if(melhor_Conjunto_Disjunto == null) {
					melhor_Conjunto_Disjunto = conjDis;
				}
				else {
					if(conjDis.get_Max_Ligacoes() <= max_Ligacoes) {
						if(conjDis.get_Custo_Total() < melhor_Conjunto_Disjunto.get_Custo_Total()) {
							if(conjDis.validar_Conjunto()) {
								melhor_Conjunto_Disjunto = conjDis;
								//System.out.println(binario + " - " + Long.toBinaryString(binario));
							}
							
						}
					}
				}
			}
		}
		melhor_Conjunto_Disjunto.imprimir();
	}
	
	
	//Somatório de 1 até num_Nodes - 1, representa a quantidade
	//de conexões possíveis
	private int num_Arestas() {
		int x = 0;
		for(int i = 1; i <= num_Nodes - 1; i++) {
			x = x + i;
		}
		return x;
	}
	
	//Verifica se aparecem num_Nodes - 1 ligações, pois é o numero necessario para 
	//todos os nós serem conectados
	private boolean validar_Combinacao(String str_Bin) {
		long quantidadeDeUns = 0;
		for(long i = 1; i < str_Bin.length(); i++) {
			if(str_Bin.charAt((int)i) == '1') {
				quantidadeDeUns++;
			}
		}
		
		
		
		if(quantidadeDeUns == num_Nodes - 1) {
			//return true;
			return verifica_Nodos_Presentes(str_Bin);
		}
		else {
			return false;
		}
	}
	
	private boolean verifica_Nodos_Presentes(String str_Bin) {
		long contador = 1;
		int vetor[] = new int[num_Nodes + 1];
		for(int i = 1; i <= num_Nodes; i++) {
			for(int j = i + 1; j <= num_Nodes; j++) {
				//System.out.println(contador);
				if(str_Bin.charAt((int)contador) == '1') {
					vetor[i]++;
					vetor[j]++;
				}
				contador++;
			}
		}
		for(int i = 1; i <= num_Nodes; i++ ) {
			if(vetor[i] == 0) {
				return false;
			}
		}
		return true;
	}
}
