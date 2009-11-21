/*
 * What's Cryptanalysis
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Problem_10008 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		ArrayList<Par> pares = new ArrayList<Par>();
		for (char base = 'a'; base <= 'z'; base++) {
			Par par = new Par();
			par.c = base;
			pares.add(par);
		}

		int n = Integer.parseInt(in.readLine().trim());
		for (int i = 0; i < n; i++) {
			String linha = in.readLine().trim();
			for (int j = 0; j < linha.length(); j++) {
				char c = linha.charAt(j);

				if (Character.isLetter(c)) {
					c = Character.toLowerCase(c);
					pares.get(c - 'a').n++;
				}
			}
		}

		Collections.sort(pares);
		for (int i = 0; i < pares.size(); i++) {
			Par p = pares.get(i);
			if (p.n > 0) {
				saida.append(p + separador);
			}
		}

		System.out.print(saida);
	}
}

class Par implements Comparable<Par> {
	char c;
	int n;

	public int compareTo(Par o) {
		if (o.n == n) {
			return o.c < n ? 1 : -1;
		}
		return o.n > n ? 1 : -1;
	}

	public String toString() {
		return Character.toUpperCase(c) + " " + n;
	}
}