#include <stdio.h>

#define MAX 5000

int adj[MAX][MAX];
int vis[MAX];
int cont = 0;

void dfs(int u, int N) {
	vis[u] = 1, cont++;
	for (int v = 1; v <= N; v++)
		if (adj[u][v] && !vis[v])
			dfs(v, N);
}

int atingeTodos(int u, int N) {
	for (int v = 1; v <= N; v++)
		vis[v] = 0;
	cont = 0;
	dfs(u, N);
	return cont == N;
}

int main() {
	int N, M, u, v, p;

	while (scanf("%d %d", &N, &M) && N && M) {
		for (int i = 0; i <= N; i++)
			for (int j = 0; j <= N; j++)
				adj[i][j] = 0;

		for (int i = 0; i < M; i++) {
			scanf("%d %d %d", &u, &v, &p);

			adj[u][v] = 1;
			if (p == 2) {
				adj[v][u] = 1;
			}
		}

		int conectado = 1;
		for (int i = 1; i <= N; i++) {
			if (!atingeTodos(i, N)) {
				conectado = 0;
				break;
			}
		}

		printf("%d\n", conectado);
	}

	return 0;
}
