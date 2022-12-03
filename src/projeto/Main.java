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
		Queue entradas = new ArrayDeque ();
		
		//lê as entradas e envia para Controlador
		entradas = leitor("/home/djavan.costa.088/Documentos/Entradas.txt");
		Controlador controlador = new Controlador(entradas);
	}
	
	
	//Ler entradas
	public static Queue leitor(String path) throws IOException {
		Queue entradas = new ArrayDeque();
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