/*
 * Zipf's Law
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeMap;

class Problem_10126 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		boolean primeira = true;
		while (true) {
			String linha = in.readLine();
			if (linha == null) {
				break;
			}
			int n = Integer.parseInt(linha);

			if (!primeira) {
				saida.append(separador);
			}
			primeira = false;

			TreeMap<String, Zipf> dic = new TreeMap<String, Zipf>();

			while (true) {
				linha = in.readLine();

				if (linha.equals("EndOfText")) {
					break;
				}

				for (int i = 0; i < linha.length(); i++) {
					while (i < linha.length()
							&& !Character.isLetter(linha.charAt(i))) {
						i++;
					}
					String p = "";
					while (i < linha.length()
							&& Character.isLetter(linha.charAt(i))) {
						p += Character.toLowerCase(linha.charAt(i));
						i++;
					}

					Zipf z = dic.get(p);
					if (z == null) {
						dic.put(p, new Zipf());
					} else {
						z.c++;
					}
				}
			}

			boolean achou = false;
			Set<String> chaves = dic.keySet();
			for (String s : chaves) {
				if (dic.get(s).c == n) {
					saida.append(s + separador);
					achou = true;
				}
			}

			if (!achou) {
				saida.append("There is no such word." + separador);
			}
		}

		System.out.print(saida);
	}
}

class Zipf {
	int c = 1;
}