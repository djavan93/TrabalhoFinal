package projeto;

public class Aresta {
	private int custo;
	private Nodo destino;

	public Aresta(int custo, Nodo destino) {
		this.custo = custo;
		this.destino = destino;
	}
	
	public int get_Custo() {
		return custo;
	}
	
	public Nodo get_Destino() {
		return destino;
	}
}
