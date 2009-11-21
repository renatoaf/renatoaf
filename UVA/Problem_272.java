/*
 * TeX Quotes
 */

import java.util.Scanner;

class Problem_272 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");
		boolean comecouQuote = false;
		while (in.hasNextLine()) {
			String linha = in.nextLine();
			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == '"' && !comecouQuote) {
					saida.append("``");
					comecouQuote = true;
				} else if (linha.charAt(i) == '"' && comecouQuote) {
					saida.append("''");
					comecouQuote = false;
				} else {
					saida.append(linha.charAt(i));
				}
			}
			saida.append(separador);
		}
		System.out.print(saida);
	}
}