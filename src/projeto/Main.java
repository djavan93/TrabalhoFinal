package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> entradas = new ArrayList<>();
		entradas = leitor("D:/USUARIO/Área de Trabalho/edb/TrabalhoFinal/src/input/Entradas.txt");
		
	}
	
	
	//Ler entradas
	public static ArrayList<Integer> leitor(String path) throws IOException {
		ArrayList<Integer> entradas = new ArrayList<>();
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