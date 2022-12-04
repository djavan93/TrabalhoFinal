package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		//Usamos uma fila aqui
		List<Integer> entradas = new ArrayList<Integer> ();
		
		//lê as entradas e envia para Controlador
		entradas = leitor("D:/USUARIO/Área de Trabalho/edb/TrabalhoFinal/src/input/Entradas.txt");
		Controlador controlador = new Controlador(entradas);
	}
	
	
	//Ler entradas
	public static List<Integer> leitor(String path) throws IOException {
		List<Integer> entradas = new ArrayList<Integer>();
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