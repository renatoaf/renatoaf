/*
 * Deli Deli
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Problem_11233 {
	public static boolean ehConsoante(char c) {
		return c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u';
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		String[] linha = in.readLine().split("\\s+");
		int L = Integer.parseInt(linha[0]);
		int N = Integer.parseInt(linha[1]);

		HashMap<String, String> irregulares = new HashMap<String, String>();

		for (int i = 0; i < L; i++) {
			linha = in.readLine().split("\\s+");
			irregulares.put(linha[0], linha[1]);
		}

		for (int i = 0; i < N; i++) {
			String palavra = in.readLine().trim();

			if (irregulares.get(palavra) != null) {
				saida.append(irregulares.get(palavra) + separador);

			} else if (palavra.length() >= 2
					&& palavra.charAt(palavra.length() - 1) == 'y'
					&& ehConsoante(palavra.charAt(palavra.length() - 2))) {
				saida.append(palavra.substring(0, palavra.length() - 1) + "ies"
						+ separador);

			} else if (palavra.endsWith("o") || palavra.endsWith("s")
					|| palavra.endsWith("ch") || palavra.endsWith("sh")
					|| palavra.endsWith("x")) {
				saida.append(palavra + "es" + separador);

			} else {
				saida.append(palavra + "s" + separador);
			}
		}

		System.out.print(saida);
	}
}