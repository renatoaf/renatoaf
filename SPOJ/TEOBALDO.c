#include <stdio.h>
#include <string.h>

#define MAX 215

int adj[MAX][MAX], caminhos[MAX][MAX];

void conta(int passos, int fonte, int n) {
	int j, t, k;
	for (j = 0; j < n; j++)
		caminhos[1][j] = adj[fonte][j];
	for (t = 2; t <= passos; t++)
		for (j = 0; j < n; j++)
			for (k = 0; k < n; k++)
				// trocar |= por += e & por * para buscar o numero de caminhos
				caminhos[t][j] |= (adj[k][j] & caminhos[t - 1][k]);
}

int main() {
	int N, M, u, v, fonte, destino, dias = 0, i;

	while (scanf("%d %d", &N, &M) && (N || M)) {
		memset(adj, 0, sizeof(adj));
		memset(caminhos, 0, sizeof(caminhos));

		for (i = 0; i < M; i++) {
			scanf("%d %d", &u, &v);
			u--, v--;
			adj[u][v] = adj[v][u] = 1;
		}

		scanf("%d %d %d", &fonte, &destino, &dias);
		fonte--, destino--;

		conta(dias, fonte, N);
		if ((dias == 0 && fonte == destino) || caminhos[dias][destino])
			puts("Yes, Teobaldo can travel.");
		else
			puts("No, Teobaldo can not travel.");
	}

	return 0;
}
