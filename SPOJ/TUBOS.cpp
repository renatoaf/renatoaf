#include <stdio.h>

#define MAX 1000

int cnt, lbl[MAX], low[MAX], parnt[MAX], adj[MAX][MAX], possivel;

void dfs(int v, int N) {
	if (!possivel)
		return; // poda
	lbl[v] = cnt++;
	low[v] = lbl[v];
	for (int w = 0; w < N; w++)
		if (adj[v][w]) {
			if (lbl[w] == -1) { // nao visitado
				parnt[w] = v;
				dfs(w, N);
				if (low[v] > low[w])
					low[v] = low[w];
				if (low[w] == lbl[w]) { // achou ponte v->w
					possivel = 0;
					break;
				}
			} else if (w != parnt[v] && low[v] > lbl[w])
				low[v] = lbl[w];
		}
}

bool ehPossivel(int N) {
	cnt = 0;
	for (int v = 0; v < N; v++)
		lbl[v] = -1;
	possivel = 1;
	for (int v = 0; v < N && cnt < N && possivel; v++)
		if (lbl[v] == -1) {
			parnt[v] = v;
			dfs(v, N);
		}
	return possivel;
}

int main() {
	int N, M, A, B;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0)
			break;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				adj[i][j] = 0;

		for (int i = 0; i < M; i++) {
			scanf("%d %d", &A, &B);
			A--, B--;
			adj[A][B] = adj[B][A] = 1;
		}

		puts(ehPossivel(N) ? "S" : "N");
	}

	return 0;
}
