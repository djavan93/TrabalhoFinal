package projeto;

import java.util.ArrayList;
import java.util.List;

public class Combinacoes {
	private static List<Ligacao> ligacoes;
	
	public Combinacoes() {
		ligacoes = new ArrayList<Ligacao>();
	}
	
	public static void setLigacao(Ligacao ligacao) {
		ligacoes.add(ligacao);
	}
	
	public static void mudarLigacao(int i, Ligacao ligacao) {
		ligacoes.set(i, ligacao);
	}
	
	public static Ligacao getLigacao(int i) {
		return ligacoes.get(i);
	}
	
	public static int num_ligacoes() {
		return ligacoes.size();
	}
}
