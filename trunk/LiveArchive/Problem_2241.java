import java.util.StringTokenizer;

class Problem_2241 {
	static final int INF = 10000;

	static void floydWarshall(int[][] grafo, int n) {
		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					grafo[i][j] = Math.min(grafo[i][j], grafo[i][k]
							+ grafo[k][j]);
				}
			}
		}
		/*
		 * no fim, g[i][j] representa a menor distancia de i a j, passando por
		 * todos os vertices
		 */
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

		while (true) {
			int N = Integer.parseInt(readln());

			if (N == 0) {
				break;
			}

			int[][] grafo = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j) {
						grafo[i][j] = INF;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				StringTokenizer prox = new StringTokenizer(readln());

				int M = Integer.parseInt(prox.nextToken());
				for (int k = 0; k < M; k++) {
					int j = Integer.parseInt(prox.nextToken()) - 1;
					grafo[i][j] = Integer.parseInt(prox.nextToken());
				}
			}

			floydWarshall(grafo, N);

			int menorCusto = INF;
			int verticeOrigem = -1;

			int verticesSemVisitarTodos = 0;
			for (int i = 0; i < N; i++) {
				int ultimoAReceberInfo = -1;
				for (int j = 0; j < N; j++) {
					if (i != j) {
						if (grafo[i][j] > ultimoAReceberInfo) {
							ultimoAReceberInfo = grafo[i][j];
						}
					}
				}

				if (ultimoAReceberInfo == INF) {
					verticesSemVisitarTodos++;
				} else if (ultimoAReceberInfo < menorCusto) {
					menorCusto = ultimoAReceberInfo;
					verticeOrigem = i;
				}
			}

			if (verticesSemVisitarTodos == N) {
				saida.append("disjoint\n");
			} else {
				saida.append((verticeOrigem + 1) + " " + menorCusto + "\n");
			}
		}

		System.out.print(saida);
	}
}