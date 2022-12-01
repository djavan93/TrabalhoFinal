package projeto;

public class Aresta {
	private int custo;
	private char origem;
	private char destino;
	
	public Aresta(int custo, char origem, char destino) {
		this.custo = custo;
		this.origem = origem;
		this.destino = destino;
	}
	
	public int get_Custo() {
		return custo;
	}
}
