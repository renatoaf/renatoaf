#include <stdio.h>
#include <string.h>

#define MAX 1000

int cnt, lbl[MAX], low[MAX], parnt[MAX], adj[MAX][MAX], mantida[MAX][MAX];

void dfs(int v, int N) {
	lbl[v] = cnt++;
	low[v] = lbl[v];
	for (int w = 0; w < N; w++)
		if (adj[v][w]) {
			if (!mantida[w][v]) // mantem uma das direcoes (da questao)
				mantida[v][w] = 1;
			if (lbl[w] == -1) { // nao visitado
				parnt[w] = v;
				dfs(w, N);
				if (low[v] > low[w])
					low[v] = low[w];
				if (low[w] == lbl[w]) { // achou ponte v->w
					mantida[v][w] = mantida[w][v] = 1;
				}
			} else if (w != parnt[v] && low[v] > lbl[w])
				low[v] = lbl[w];
		}
}

void todasAsPontes(int N) {
	cnt = 0;
	for (int v = 0; v < N; v++)
		lbl[v] = -1;
	for (int v = 0; v < N && cnt < N; v++)
		if (lbl[v] == -1) {
			parnt[v] = v;
			dfs(v, N);
		}
}

int main() {
	int N, M, u, v, caso = 1;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0)
			break;

		for (int i = 0; i < M; i++) {
			scanf("%d %d", &u, &v);
			adj[u - 1][v - 1] = 1;
			adj[v - 1][u - 1] = 1;
		}

		todasAsPontes(N);

		printf("%d\n\n", caso++);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (mantida[i][j])
					printf("%d %d\n", i + 1, j + 1);
		puts("#");

		memset(adj, 0, sizeof(adj));
		memset(mantida, 0, sizeof(mantida));
	}

	return 0;
}
