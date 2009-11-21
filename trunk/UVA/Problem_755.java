/*
 * 487-3279
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeMap;

class Problem_755 {
	static int botao(char digito) {
		if (digito == 'A' || digito == 'B' || digito == 'C') {
			return 2;
		} else if (digito == 'D' || digito == 'E' || digito == 'F') {
			return 3;
		} else if (digito == 'G' || digito == 'H' || digito == 'I') {
			return 4;
		} else if (digito == 'J' || digito == 'K' || digito == 'L') {
			return 5;
		} else if (digito == 'M' || digito == 'N' || digito == 'O') {
			return 6;
		} else if (digito == 'P' || digito == 'R' || digito == 'S') {
			return 7;
		} else if (digito == 'T' || digito == 'U' || digito == 'V') {
			return 8;
		} else if (digito == 'W' || digito == 'X' || digito == 'Y') {
			return 9;
		}
		return 1;
	}

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
			int n = Integer.parseInt(in.readLine());

			TreeMap<String, Cont> fones = new TreeMap<String, Cont>();

			for (int i = 0; i < n; i++) {
				String tel = in.readLine();
				String standardTel = "";

				for (int j = 0; j < tel.length(); j++) {
					if (standardTel.length() == 3) {
						standardTel += "-";
					}

					char dig = tel.charAt(j);
					if (Character.isLetter(dig)) {
						standardTel += botao(dig);
					} else if (Character.isDigit(dig)) {
						standardTel += dig;
					}
				}

				if (fones.get(standardTel) == null) {
					fones.put(standardTel, new Cont());
				}
				fones.get(standardTel).c++;
			}

			boolean achou = false;
			Set<String> telefones = fones.keySet();

			for (String tel : telefones) {
				int contagem = fones.get(tel).c;
				if (contagem != 1) {
					achou = true;
					saida.append(tel + " " + contagem + separador);
				}
			}

			if (!achou) {
				saida.append("No duplicates." + separador);
			}
		}

		System.out.print(saida);
	}
}

class Cont {
	int c = 0;
}