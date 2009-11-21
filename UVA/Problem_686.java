/*
 * Goldach's Conjecture
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_686 {
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

	static int goldbach(int n) {
		int a = 1;
		int meio = n / 2;
		int cont = 0;

		if (primo[n - 2]) { // unico par primo eh 2
			cont++;
		}

		while (a <= meio) {
			int b = n - a;
			if (primo[a] && primo[b]) {
				cont++;
			}
			a += 2;
		}
		return cont;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		crivo(32768);

		while (true) {
			int n = Integer.parseInt(in.readLine());
			if (n == 0) {
				break;
			}
			saida.append(goldbach(n) + separador);
		}

		System.out.print(saida);
	}
}