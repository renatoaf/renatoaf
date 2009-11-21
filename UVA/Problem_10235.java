/*
 * Simply Emirp
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10235 {
	static boolean[] primo;

	static void crivo(int tamanho) {
		primo = new boolean[tamanho];
		for (int i = 2; i < tamanho; i++) {
			primo[i] = true;
		}
		for (int i = 2; i * i < tamanho; i++) {
			if (primo[i]) {
				for (int j = i; i * j < tamanho; j++) {
					primo[i * j] = false;
				}
			}
		}
	}

	static String inverte(String n) {
		return new StringBuilder(n).reverse().toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		crivo(1000000);

		while (true) {
			String n = in.readLine();

			if (n == null) {
				break;
			}

			int num = Integer.parseInt(n);
			if (primo[num]) {
				int numInv = Integer.parseInt(inverte(num + ""));
				if (num != numInv && primo[numInv]) {
					saida.append(num + " is emirp.");
				} else {
					saida.append(num + " is prime.");
				}
			} else {
				saida.append(num + " is not prime.");
			}

			saida.append(separador);
		}

		System.out.print(saida);
	}
}