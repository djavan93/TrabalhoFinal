package projeto;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	private char valor;
	private Nodo pai;
	private List<Nodo> ligacoes;
	
	public Nodo(char valor) {
		this.valor = valor;
		pai = null;
		ligacoes = new ArrayList<Nodo>();
	}
	
	//retorna se cabe mais ligações
	public boolean verificar_ligacoes(int d) {
		if(pai == null) {
			if(ligacoes.size() < d) {
				return true;
			}
			return false;
		}
		else {
			if(ligacoes.size() + 1 < d) {
				return true;
			}
			return false;
		}
	}
	
	public void set_Pai(Nodo pai) {
		this.pai = pai;
	}
	
	public Nodo get_pai() {
		return pai;
	}
	
	public void adicionar_ligacao(Nodo ligacao) {
		ligacoes.add(ligacao);
	}
	
}
