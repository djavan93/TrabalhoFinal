package projeto;

public class Aresta {
	private int custo;
	private Nodo destino;

	public Aresta(int custo, Nodo destino) {
		this.custo = custo;
		this.destino = destino;
	}
	
	public int getCusto() {
		return custo;
	}
	
	public Nodo getDestino() {
		return destino;
	}
}
