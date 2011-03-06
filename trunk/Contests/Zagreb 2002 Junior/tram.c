#include <stdio.h>

#define MAXN 101
#define INF 10001

int adj[MAXN][MAXN];

int min(int a, int b) {
	return a < b ? a : b;
}

void floydWarshall(int n) {
	int k, i, j;
	for (k = 0; k < n; k++)
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				adj[i][j] = min(adj[i][k] + adj[k][j], adj[i][j]);
}

int main() {
	int A, B, N, K, u, v, i, j;

	scanf("%d %d %d", &N, &A, &B);
	A--, B--;

	for (i = 0; i < N; i++)
		for (j = 0; j < N; j++)
			adj[i][j] = INF;

	for (u = 0; u < N; u++) {
		scanf("%d", &K);

		for (j = 0; j < K; j++) {
			scanf("%d", &v);
			v--;

			if (j == 0)
				adj[u][v] = 0; // primeira da lista eh onde o switch esta direcionado
			else
				adj[u][v] = 1;
		}
	}

	floydWarshall(N);

	if (adj[A][B] == INF)
		printf("-1\n");
	else
		printf("%d\n", adj[A][B]);

	return 0;
}
