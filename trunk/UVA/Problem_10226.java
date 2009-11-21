/*
 * Hardwood Species
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Set;
import java.util.TreeMap;

class Problem_10226 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		DecimalFormat format = new DecimalFormat("0.0000");
		DecimalFormatSymbols ponto = new DecimalFormatSymbols();
		ponto.setDecimalSeparator('.');
		format.setDecimalFormatSymbols(ponto);

		int casos = Integer.parseInt(in.readLine());
		in.readLine();

		for (int c = 1; c <= casos; c++) {
			if (c != 1) {
				saida.append(separador);
			}

			double total = 0;

			TreeMap<String, Count> arvores = new TreeMap<String, Count>();
			while (true) {
				String arvore = in.readLine();
				if (arvore == null || arvore.equals("")) {
					break;
				}

				if (arvores.get(arvore) == null) {
					arvores.put(arvore, new Count());
				}
				arvores.get(arvore).c++;
				total++;
			}

			Set<String> keys = arvores.keySet();
			for (String arvore : keys) {
				double ocorrencias = (double) arvores.get(arvore).c;
				saida.append(arvore + " "
						+ format.format((ocorrencias / total) * 100)
						+ separador);
			}
		}

		System.out.print(saida);
	}
}

class Count {
	int c = 0;
}