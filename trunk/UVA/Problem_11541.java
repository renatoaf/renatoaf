/*
 * Decoding
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_11541 {
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		int n = Integer.parseInt(in.readLine());
		for (int caso = 1; caso <= n; caso++) {
			String linha = in.readLine();

			saida.append("Case " + caso + ": ");
			for (int i = 0; i < linha.length();) {
				char c = linha.charAt(i);

				i++;

				String numero = "";
				while (i < linha.length()) {
					if (Character.isDigit(linha.charAt(i))) {
						numero += linha.charAt(i);
						i++;
					} else {
						break;
					}
				}

				int nVezes = Integer.parseInt(numero);
				for (int j = 0; j < nVezes; j++) {
					saida.append(c);
				}
			}

			saida.append(separador);
		}

		System.out.print(saida);
	}
}