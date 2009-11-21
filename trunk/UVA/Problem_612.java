/*
 * DNA Sorting
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Problem_612 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());

		for (int c = 1; c <= casos; c++) {
			if (c != 1) {
				saida.append(separador);
			}

			in.readLine();
			int n = Integer.parseInt(in.readLine().split("\\s+")[1]);

			ArrayList<DNAString> cadeias = new ArrayList<DNAString>();
			for (int i = 0; i < n; i++) {
				cadeias.add(new DNAString(in.readLine()));
			}

			Collections.sort(cadeias);

			for (int i = 0; i < n; i++) {
				saida.append(cadeias.get(i) + separador);
			}
		}

		System.out.print(saida);
	}
}

class DNAString implements Comparable<DNAString> {
	String cadeia;

	public DNAString(String cadeia) {
		this.cadeia = cadeia;
	}

	public int medida() {
		int med = 0;
		for (int i = 0; i < cadeia.length(); i++) {
			for (int j = i + 1; j < cadeia.length(); j++) {
				if (cadeia.charAt(i) > cadeia.charAt(j)) {
					med++;
				}
			}
		}
		return med;
	}

	public int compareTo(DNAString arg0) {
		return medida() - arg0.medida();
	}

	public String toString() {
		return cadeia;
	}
}