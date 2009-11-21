#include <stdio.h>

int inf = 10000;
int grafo[501][501];
int custo[501];
int visitados[501];

int dijkstra(int O, int D, int N) {
	int i, atual, minimo;
	for (i = 1; i <= N; i++) {
		visitados[i] = 0;
		custo[i] = inf;
	}
	custo[O] = 0;
	atual = O;
	while (atual != D) {
		for (i = 1; i <= N; i++) {
			if (grafo[atual][i] != inf) {
				if (custo[atual] + grafo[atual][i] < custo[i]) {
					custo[i] = custo[atual] + grafo[atual][i];
				}
			}
		}
		minimo = inf;
		visitados[atual] = 1;
		for (i = 1; i <= N; i++) {
			if ((custo[i] <= minimo) && (!visitados[i])) {
				minimo = custo[i];
				atual = i;
			}
		}
		if (minimo >= inf) {
			return inf;
		}
	}
	return custo[D];
}

int main() {
	int N, E, i, j, X, Y, H, K, O, D, menorCaminho;

	while (1) {
		scanf("%d%d", &N, &E);

		if (N == 0) {
			break;
		}

		for (i = 1; i <= N; i++) {
			for (j = 1; j <= N; j++) {
				grafo[i][j] = inf;
			}
		}

		for (i = 1; i <= E; i++) {
			scanf("%d%d%d", &X, &Y, &H);
			grafo[X][Y] = H;
			if (grafo[Y][X] != inf) {
				grafo[Y][X] = grafo[X][Y] = 0;
			}
		}

		scanf("%d", &K);
		for (i = 1; i <= K; i++) {
			scanf("%d%d", &O, &D);

			if (grafo[O][D] == 0) {
				menorCaminho = 0;
			} else {
				menorCaminho = dijkstra(O, D, N);
			}

			if (menorCaminho == inf) {
				printf("Nao e possivel entregar a carta\n");
			} else {
				printf("%d\n", menorCaminho);
			}
		}
		printf("\n");
	}

	return 0;
}
