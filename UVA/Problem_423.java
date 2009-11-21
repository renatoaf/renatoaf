/*
 * MPI Maelstrom
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_423 {
	private static final int INF = Integer.MAX_VALUE;

	private static int[] anteriores;
	private static int[] custo;
	private static boolean[] visitados;

	public static void dijkstra(int origem, int destino, int n, int[][] grafo) {
		anteriores = new int[n];
		custo = new int[n];
		visitados = new boolean[n];

		for (int i = 0; i < n; i++) {
			anteriores[i] = -1;
			visitados[i] = false;
			custo[i] = INF;
		}

		custo[origem] = 0;
		int atual = origem;
		while (atual != destino) {
			for (int i = 0; i < n; i++) {
				if (grafo[atual][i] != INF) {
					if (custo[atual] + grafo[atual][i] < custo[i]) {
						custo[i] = custo[atual] + grafo[atual][i];
						anteriores[i] = atual;
					}
				}
			}

			int minimo = INF;
			visitados[atual] = true;

			for (int i = 0; i < n; i++) {
				if ((custo[i] <= minimo) && (!visitados[i])) {
					minimo = custo[i];
					atual = i;
				}
			}
			if (minimo >= INF) {
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String linha = in.readLine();
			if (linha == null) {
				break;
			}
			int n = Integer.parseInt(linha);

			int[][] grafo = new int[n][n];

			grafo[0][0] = 0;
			for (int i = 1; i < n; i++) {
				String[] entradas = in.readLine().split("\\s+");

				for (int j = 0; j <= i; j++) {
					if (j != i) {
						int peso = entradas[j].equals("x") ? INF : Integer
								.parseInt(entradas[j]);
						grafo[i][j] = peso;
						grafo[j][i] = peso;
					} else {
						grafo[i][i] = 0;
					}
				}
			}

			int custoMinimo = Integer.MAX_VALUE;
			for (int i = 1; i < n; i++) {
				dijkstra(0, i, n, grafo);

				boolean visitouTodos = true;
				for (int j = 0; j < n; j++) {
					if (j != i && !visitados[j]) {
						visitouTodos = false;
					}
				}
				if (visitouTodos && custo[i] < custoMinimo) {
					custoMinimo = custo[i];
				}
			}
			saida.append(custoMinimo + separador);
		}

		System.out.print(saida);
	}
}