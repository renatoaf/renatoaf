/*
 * Age Sort
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Problem_11462 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			int n = Integer.parseInt(in.readLine());
			if (n == 0) {
				break;
			}

			int[] idades = new int[n];
			String[] linha = in.readLine().split("\\s+");
			for (int i = 0; i < n; i++) {
				idades[i] = Integer.parseInt(linha[i]);
			}
			Arrays.sort(idades);
			String sep = "";
			for (int i = 0; i < n; i++) {
				saida.append(sep + idades[i]);
				sep = " ";
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}