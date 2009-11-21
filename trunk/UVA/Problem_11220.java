/*
 * Decoding the Message
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_11220 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());
		in.readLine();

		for (int c = 1; c <= casos; c++) {
			if (c != 1) {
				saida.append(separador);
			}

			saida.append("Case #" + c + ":" + separador);
			while (true) {
				String fraseFalsa = in.readLine();

				if (fraseFalsa == null || fraseFalsa.equals("")) {
					break;
				}

				String palavraVerdadeira = "";
				String[] palavrasFalsas = fraseFalsa.split("\\s+");
				int nth = 0;
				for (int i = 0; i < palavrasFalsas.length; i++) {
					if (nth < palavrasFalsas[i].length()) {
						palavraVerdadeira += palavrasFalsas[i].charAt(nth++);
					}
				}
				saida.append(palavraVerdadeira + separador);
			}
		}

		System.out.print(saida);
	}
}