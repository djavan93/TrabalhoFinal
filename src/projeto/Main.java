package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		//Usamos uma fila aqui
		Queue<Integer> entradas = new ArrayDeque<Integer> ();
		
		//lê as entradas e envia para Controlador
		entradas = leitor("D:/USUARIO/Área de Trabalho/edb/TrabalhoFinal/src/input/Entradas.txt");
		new Controlador(entradas);
	}
	
	//Ler entradas
	public static Queue<Integer> leitor(String path) throws IOException {
		Queue<Integer> entradas = new ArrayDeque<Integer>();
		try {
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
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return entradas;
	}
}