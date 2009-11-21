/*
 * Prime Frequency
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Problem_10789 {
	public static boolean primo(int n) {
		if (n == 1) {
			return false;
		}
		for (int i = 2; i <= (n / 2); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int n = Integer.parseInt(in.readLine());
		for (int c = 1; c <= n; c++) {
			char[] caracteres = in.readLine().toCharArray();

			Arrays.sort(caracteres);

			int i = 0;

			saida.append("Case " + c + ": ");
			boolean naoAdicionouNada = true;
			while (i < caracteres.length) {
				char caractere = caracteres[i];
				int contagem = 0;

				while (i < caracteres.length && caracteres[i] == caractere) {
					contagem++;
					i++;
				}

				if (primo(contagem)) {
					naoAdicionouNada = false;
					saida.append(caractere);
				}
			}

			if (naoAdicionouNada) {
				saida.append("empty");
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}