#include <stdio.h>

int INF = 1000;
int floyd[102][102][102];

int min(int a, int b) {
	if (a < b) {
		return a;
	}
	return b;
}

void floydWarshall(int n) {
	int i, j, k;
	for (i = 1; i <= n; i++) {
		for (j = 1; j <= n; j++) {
			floyd[1][i][j] = floyd[0][i][j];
		}
	}

	for (k = 1; k <= n; k++) {
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				floyd[k][i][j] = min(floyd[k][i][j], floyd[k][i][k]
						+ floyd[k][k][j]);
				if (k != n) {
					floyd[k + 1][i][j] = floyd[k][i][j];
				}
			}
		}
	}
}

int main() {
	int i, j, k, n, m, caso, u, v, w, o, d, t, consultas;

	caso = 1;
	while (scanf("%d %d", &n, &m) == 2) {
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				if (i != j) {
					floyd[0][i][j] = INF;
				} else {
					floyd[0][i][j] = 0;
				}
			}
		}

		for (i = 0; i < m; i++) {
			scanf("%d %d %d", &u, &v, &w);
			floyd[0][u][v] = min(floyd[0][u][v], w);
		}

		floydWarshall(n);

		printf("Instancia %d\n", caso++);
		scanf("%d", &consultas);
		for (k = 0; k < consultas; k++) {
			scanf("%d%d%d", &o, &d, &t);
			int menorCaminho = floyd[t][o][d];
			if (menorCaminho == INF) {
				printf("-1\n");
			} else {
				printf("%d\n", menorCaminho);
			}
		}

		printf("\n");
	}

	return 0;
}
