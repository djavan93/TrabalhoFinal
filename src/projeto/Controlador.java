package projeto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Controlador {
	private int numNodes;
	private int maxLigacoes;
	private ConjuntoDisjunto melhorConjuntoDisjunto;
	private int numCombinacoesPossiveis;
	private List<Ligacao> ligacoes;
	private ConjuntoDisjunto conjuntoDisjunto;
	
	
	public Controlador(Queue<Integer> entradas) {
		new Combinacoes();
		numCombinacoesPossiveis = 0;
		ligacoes = new ArrayList<Ligacao>();
		
		
		//o metodo poll() puxa os primeiros elementos da fila, ou seja, o numero de nós e o o maximo de ligações "d"
		numNodes = (int)entradas.poll();
		maxLigacoes = (int)entradas.poll();
		
		//Cria "modelos" de arestas, contendo o nó de origem, custo e nó destino,
		//Que serão modificadas mais a frente.
		//Elas serão combinadas e enviadas para o conjunto disjunto
		for(int i = 0; i < numNodes - 1; i++) {
			for(int j = i + 1; j < numNodes; j++) {
				ligacoes.add(new Ligacao(i, entradas.poll(), j));
			}
			Combinacoes.setLigacao(new Ligacao(-1, -1, -1));
		}
		
		//Aqui gera todos as combinações com num e envia para ConjuntoDisjunto, 
		//se for um conjunto válido e melhor q o anterior, adiciona em melhor_Conjunto_Disjunto
		gerarCombinacoes(0, 0);
		
		
		System.out.println(melhorConjuntoDisjunto.toString()); 
		System.out.println("Combinações possíveis = " + numCombinacoesPossiveis + "\nNumero de arestas = " + numArestas());
		escreverArquivo(melhorConjuntoDisjunto.toString());
	}
	
	//Somatório de 1 até num_Nodes - 1, representa a quantidade
	//de conexões possíveis
	private int numArestas() {
		int x = 0;
		for(int i = 1; i <= numNodes - 1; i++) {
			x = x + i;
		}
		return x;
	}
	
	
	//Verifica se aparecem num_Nodes - 1 ligações, pois é o numero necessario para 
	//todos os nós serem conectados
	
	public void gerarCombinacoes(int id, int inicio) {
		if(id == numNodes - 1) {
			int vetor[] = new int[numNodes];
			for(int i = 0; i < numNodes - 1; i++) {
				vetor[Combinacoes.getLigacao(i).getNodo1()]++;
				vetor[Combinacoes.getLigacao(i).getNodo2()]++;
			}
			for(int i = 0; i < numNodes; i++ ) {
				if(vetor[i] == 0) {
					return;
				}
			}
			conjuntoDisjunto = new ConjuntoDisjunto(numNodes);
			if(conjuntoDisjunto.get_Max_Ligacoes() <= maxLigacoes) {
				if(melhorConjuntoDisjunto == null) {
					melhorConjuntoDisjunto = conjuntoDisjunto;
				}
				else {
					if(conjuntoDisjunto.validarConjunto()) {
						if(conjuntoDisjunto.getCustoTotal() < melhorConjuntoDisjunto.getCustoTotal()) {
							melhorConjuntoDisjunto = conjuntoDisjunto;
						}	
					}
				}
				numCombinacoesPossiveis++;
			}
			return;
		}
		else {
			for(int i = inicio; i < ligacoes.size() - (numNodes - 2 - id); i++) {
				Combinacoes.mudarLigacao(id, ligacoes.get(i));
				gerarCombinacoes(id + 1, i + 1);
			}
		}
	}
	
	private void escreverArquivo(String retorno) {
		File arquivo = new File(Main.getCaminho() + "Saida.txt");
		try {
			FileWriter fw = new FileWriter( arquivo );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write( retorno );
			bw.newLine();
			bw.write("Combinações possíveis = " + numCombinacoesPossiveis + "\nNumero de arestas = " + numArestas());
			bw.close();
			fw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}	
}