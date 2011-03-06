#include <stdio.h>
#include <string.h>

#define MAX 101

int adj[MAX][MAX], vis[MAX], dependencias;

int min(int a, int b) {
	return a < b ? a : b;
}

void dfs(int v, int n) {
	vis[v] = 1;
	for (int i = 0; i < n; i++)
		if (adj[v][i] && !vis[i]) {
			dependencias++;
			dfs(i, n);
		}
}

int getDependencias(int v, int n) {
	dependencias = 0;
	memset(vis, 0, sizeof(vis));
	dfs(v, n);
	return dependencias;
}

int main() {
	int N, T, lig, vencedor, max, count;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;

		memset(adj, 0, sizeof(adj));

		for (int i = 0; i < N; i++) {
			scanf("%d", &T);

			for (int j = 0; j < T; j++) {
				scanf("%d", &lig);
				adj[i][lig - 1] = 1;
			}
		}

		vencedor = max = -1;
		for (int i = 0; i < N; i++) {
			count = getDependencias(i, N);
			if (count > max)
				max = count, vencedor = i;
		}

		printf("%d\n", vencedor + 1);
	}

	return 0;
}
