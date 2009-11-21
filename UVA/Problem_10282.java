/*
 * Babelfish
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Problem_10282 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		HashMap<String, String> dic = new HashMap<String, String>();
		while (true) {
			String linha = in.readLine().trim();
			if (linha.equals("")) {
				break;
			}
			String[] d = linha.split("\\s+");
			dic.put(d[1].toLowerCase(), d[0].toLowerCase());
		}

		while (true) {
			String linha = in.readLine();

			if (linha == null) {
				break;
			}

			String traduzida = dic.get(linha);
			saida.append(traduzida == null ? "eh" : traduzida);
			saida.append(separador);
		}

		System.out.print(saida);
	}
}