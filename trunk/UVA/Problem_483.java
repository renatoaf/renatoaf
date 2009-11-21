/*
 * World Scramble
 */

import java.util.Scanner;

class Problem_483 {
	public static String inverte(String palavra) {
		StringBuffer invertida = new StringBuffer();
		invertida.append(palavra);
		return invertida.reverse().toString();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = "";
		while (in.hasNextLine()) {
			saida.append(separador);
			String[] linha = in.nextLine().split(" ");
			String espaco = "";
			for (int i = 0; i < linha.length; i++) {
				saida.append(espaco + inverte(linha[i]));
				espaco = " ";
			}
			separador = System.getProperty("line.separator");
		}
		System.out.println(saida);
	}
}