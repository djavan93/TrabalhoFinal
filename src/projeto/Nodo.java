package projeto;


//O nó aponta para a aresta e a aresta aponta para o nó destino
public class Nodo {
	private int chave;
	private Aresta pai;
	private int ordem;
	private int numLigacoes; //Quantos nós apontam para este nó
	
	public Nodo(int chave) {
		this.chave = chave;
		pai = null;
		ordem = 0;
		numLigacoes = 0;
		
	}
	
	public int getChave() {
		return chave;
	}
	
	public Aresta getPai() {
		return pai;
	}
	
	public void setPai(Aresta pai) {
		this.pai = pai;
	}
	
	public int getOrdem() {
		return ordem;
	}
	
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	
	public int getNumligacoes() {
		return numLigacoes;
	}
	
	public void adicionarNumLigacoes(int x) {
		numLigacoes += x;
	}
	
}
