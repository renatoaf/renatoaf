/*
 * Phone List
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Problem_11362 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine().trim());
		for (int c = 1; c <= casos; c++) {
			int n = Integer.parseInt(in.readLine().trim());

			ArrayList<String> lista = new ArrayList<String>();
			for (int i = 0; i < n; i++) {
				lista.add(in.readLine().trim());
			}
			Collections.sort(lista);

			boolean possivel = true;

			for (int i = 0; i < n - 1; i++) {
				if (lista.get(i + 1).startsWith(lista.get(i))) {
					possivel = false;
					break;
				}
			}

			saida.append(possivel ? "YES" : "NO");
			saida.append(separador);
		}

		System.out.print(saida);
	}
}