/*
 * Prime Cuts
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

class Problem_406 {
	static boolean[] primo;

	static boolean[] criba(int tamanho) {
		// criba[i] = false se i eh primo
		boolean criba[] = new boolean[tamanho];
		criba[0] = criba[1] = true;
		for (int i = 4; i < tamanho; i += 2) {
			criba[i] = true;
		}
		for (int i = 3; i * i < tamanho; i += 2) {
			if (!criba[i]) {
				for (int j = i * i; j < tamanho; j += i) {
					criba[j] = true;
				}
			}
		}
		return criba;
	}

	static void crivo(int tamanho) {
		primo = new boolean[tamanho];
		for (int i = 1; i < tamanho; i++) {
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

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		crivo(1000);

		ArrayList<Integer> primos = new ArrayList<Integer>();

		for (int i = 1; i <= 1000; i += 2) {
			if (primo[i]) {
				primos.add(i);
			}
			if (i == 1) {
				primos.add(2);
			}
		}

		while (true) {
			String linha = in.readLine();
			if (linha == null) {
				break;
			}

			String[] config = linha.split("\\s+");

			int N = Integer.parseInt(config[0]);
			int C = Integer.parseInt(config[1]);

			ArrayList<Integer> subLista = new ArrayList<Integer>();
			Iterator<Integer> it = primos.iterator();

			while (it.hasNext()) {
				int num = it.next();
				if (num > N) {
					break;
				}
				subLista.add(num);
			}

			int meio;
			int inicio;
			int fim;
			if (subLista.size() % 2 == 0) {
				meio = subLista.size() / 2;
				inicio = meio - C;
				fim = meio + C - 1;
			} else {
				meio = (subLista.size() / 2) + 1;
				inicio = meio - C;
				fim = meio + C - 2;
			}

			if (inicio < 0) {
				inicio = 0;
			}
			if (fim >= subLista.size()) {
				fim = subLista.size() - 1;
			}

			saida.append(N + " " + C + ":");

			for (int i = inicio; i <= fim; i++) {
				saida.append(" " + subLista.get(i));
			}

			saida.append(separador + separador);
		}

		System.out.print(saida);
	}
}