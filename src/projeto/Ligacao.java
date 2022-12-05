package projeto;

public class Ligacao {
	private int nodo1;
	private int custo;
	private int nodo2;
	
	public Ligacao(int nodo1, int custo, int nodo2) {
		this.nodo1 = nodo1;
		this.custo = custo;
		this.nodo2 = nodo2;
	}

	public int getNodo1() {
		return nodo1;
	}

	public void setNodo1(int nodo1) {
		this.nodo1 = nodo1;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public int getNodo2() {
		return nodo2;
	}

	public void setNodo2(int nodo2) {
		this.nodo2 = nodo2;
	}
	
	
}
