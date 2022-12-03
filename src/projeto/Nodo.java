package projeto;


//O nó aponta para a aresta e a aresta aponta para o nó
public class Nodo {
	private int chave;
	private Aresta pai;
	private int num_ligacoes;
	
	public Nodo(char chave) {
		this.chave = chave;
		pai = null;
		num_ligacoes = 0;
	}
	
	public void set_Pai(Aresta pai) {
		this.pai = pai;
	}
	
	public Aresta get_Pai() {
		return pai;
	}
	
	public int get_Chave() {
		return chave;
	}
	
	public int get_Num_ligacoes() {
		return num_ligacoes;
	}
	
}
