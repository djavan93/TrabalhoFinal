package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	private static String caminho;
	public static void main(String[] args) throws IOException {
		caminho = "D:/USUARIO/Documentos/";
		//Usamos uma fila aqui
		Queue<Integer> entradas = new ArrayDeque<Integer> ();
		
		//lê as entradas e envia para Controlador
		try {
			entradas = leitor(caminho + "Entradas.txt");
			new Controlador(entradas);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Ler entradas
	public static Queue<Integer> leitor(String path) throws IOException {
		Queue<Integer> entradas = new ArrayDeque<Integer>();
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String[] arrayValores;
		String linha = buffRead.readLine();
		while (true) {
			if (linha != null) {
				arrayValores = linha.split(" ");
		        for (String s: arrayValores) {
		            entradas.add(Integer.valueOf(s));
		        }

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
		return entradas;
	}
	
	public static String getCaminho() {
		return caminho;
	}
}