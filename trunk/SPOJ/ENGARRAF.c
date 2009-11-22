#include <stdio.h>

#define INF 1000000
#define MAX 100

int grafo[MAX][MAX], custo[MAX], visitados[MAX];

int dijkstra(int O, int D, int N) {
	int i, atual, minimo;
	for (i = 1; i <= N; i++)
		visitados[i] = 0, custo[i] = INF;
	custo[O] = 0;
	atual = O;
	while (atual != D) {
		for (i = 1; i <= N; i++)
			if (grafo[atual][i] != INF)
				if (custo[atual] + grafo[atual][i] < custo[i])
					custo[i] = custo[atual] + grafo[atual][i];
		minimo = INF;
		visitados[atual] = 1;
		for (i = 1; i <= N; i++)
			if ((custo[i] <= minimo) && (!visitados[i]))
				minimo = custo[i], atual = i;
		if (minimo >= INF)
			return INF;
	}
	return custo[D];
}

int main() {
	int N, M, S, D, u, v, w, i, j;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0) {
			break;
		}

		for (i = 1; i <= N; i++)
			for (j = 1; j <= N; j++)
				grafo[i][j] = INF;

		for (i = 1; i <= M; i++) {
			scanf("%d %d %d", &u, &v, &w);
			grafo[u][v] = w;
		}

		scanf("%d %d", &S, &D);
		int min = dijkstra(S, D, N);
		printf("%d\n", min == INF ? -1 : min);
	}

	return 0;
}
