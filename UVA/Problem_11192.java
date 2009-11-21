/*
 * Group Reverse
 */

import java.util.Scanner;

class Problem_11192 {
	public static String inverte(String s) {
		String invertida = "";
		for (int i = 0; i < s.length(); i++) {
			invertida = s.charAt(i) + invertida;
		}
		return invertida;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");
		String linha;
		while (!(linha = in.nextLine()).equals("0")) {
			String[] entrada = linha.split(" ");
			String texto = entrada[1];
			int n = Integer.parseInt(entrada[0]);
			int grupos = texto.length() / n;

			for (int i = 0; i < texto.length(); i += grupos) {
				saida.append(inverte(texto.substring(i, i + grupos)));
			}
			saida.append(separador);
		}
		System.out.print(saida);
	}
}