package projeto;

public class Aresta {
	private int custo;
	private Nodo destino;
	private int chave_origem;
	
	public Aresta(int custo, Nodo destino, int chave_origem) {
		this.custo = custo;
		this.destino = destino;
		this.chave_origem = chave_origem;
	}
	
	public int get_Custo() {
		return custo;
	}
	
	public Nodo get_Destino() {
		return destino;
	}
	
	public int get_Chave_Destino() {
		return chave_origem;
	}
}
