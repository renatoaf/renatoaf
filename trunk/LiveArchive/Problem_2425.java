import java.util.StringTokenizer;

class Problem_2425 {
	static final int INF = Integer.MAX_VALUE;

	static int dijkstra(int origem, int destino, int n, int[][] grafo) {
		int[] anteriores = new int[n];
		int[] custo = new int[n];
		boolean[] visitados = new boolean[n];

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
				return INF;
			}
		}
		return custo[destino];
	}

	static String readln() {
		String newLine = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();
		int car = -1;
		try {
			car = System.in.read();
			while ((car > 0) && (car != newLine.charAt(0))) {
				buffer.append((char) car);
				car = System.in.read();
			}
			if (car == newLine.charAt(0))
				System.in.skip(newLine.length() - 1);
		} catch (java.io.IOException e) {
			return (null);
		}
		if ((car < 0) && (buffer.length() == 0))
			return (null); /* eof */
		if (buffer.length() == 0)
			return ""; // string vazia
		return (buffer.toString()).trim();
	}

	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();

		int casos = Integer.parseInt(readln().trim());

		for (int c = 1; c <= casos; c++) {
			readln();

			int N = Integer.parseInt(readln().trim());
			int E = Integer.parseInt(readln().trim()) - 1;
			int T = Integer.parseInt(readln().trim());
			int M = Integer.parseInt(readln().trim());

			int[][] grafo = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j) {
						grafo[i][j] = INF;
					}
				}
			}

			for (int i = 0; i < M; i++) {
				StringTokenizer token = new StringTokenizer(readln());
				int a = Integer.parseInt(token.nextToken()) - 1;
				int b = Integer.parseInt(token.nextToken()) - 1;
				int t = Integer.parseInt(token.nextToken());
				grafo[a][b] = t;
			}

			if (c != 1) {
				saida.append("\n");
			}

			int count = 0;
			for (int i = 0; i < N; i++) {
				int tempoGasto = dijkstra(i, E, N, grafo);
				if (tempoGasto != INF && tempoGasto <= T) {
					count++;
				}
			}
			saida.append(count + "\n");
		}

		System.out.print(saida);
	}
}