#include <stdio.h>
#include <string.h>

#define NN 210

int cap[NN][NN];
int fnet[NN][NN];
int q[NN], qf, qb, prev[NN];

int min(int a, int b) {
	return a < b ? a : b;
}

int fordFulkerson(int n, int s, int t) {
	memset(fnet, 0, sizeof(fnet));
	int flow = 0;
	while (true) {
		memset(prev, -1, sizeof(prev));
		qf = qb = 0;
		prev[q[qb++] = s] = -2;
		while (qb > qf && prev[t] == -1)
			for (int u = q[qf++], v = 0; v < n; v++)
				if (prev[v] == -1 && fnet[u][v] - fnet[v][u] < cap[u][v])
					prev[q[qb++] = v] = u;
		if (prev[t] == -1)
			break;
		int bot = 0x7FFFFFFF;
		for (int v = t, u = prev[v]; u >= 0; v = u, u = prev[v])
			bot = min(bot, cap[u][v] - fnet[u][v] + fnet[v][u]);
		for (int v = t, u = prev[v]; u >= 0; v = u, u = prev[v])
			fnet[u][v] += bot;
		flow += bot;
	}
	return flow;
}

int main() {
	int n, casos, caso, p, i, j, pi;

	scanf("%d", &casos);
	for (caso = 1; caso <= casos; caso++) {
		scanf("%d", &n);

		int fonte = 0;
		int destino = (2* n ) + 1;
		int totalVertices = destino + 1; // total de vertices

		for (i = 0; i < totalVertices; i++) {
			for (j = 0; j < totalVertices; j++) {
				cap[i][j] = 0;
			}
		}

		for (i = 1; i <= n; i++) {
			// liga vertice inicial aos programadores do conj. 1 com capacidade 1
			cap[fonte][i] = 1;
			// liga programadores do conj. 2 ao vertice final com capacidade 1
			cap[n + i][destino] = 1;
		}

		for (i = 1; i <= n; i++) { // afinidades
			scanf("%d", &p);
			for (j = 0; j < p; j++) {
				scanf("%d", &pi);
				cap[i][n + pi] = 1;
			}
		}

		int emparelhamentoMaximo = fordFulkerson(totalVertices, fonte, destino);
		printf("Instancia %d\n", caso);
		if (emparelhamentoMaximo == n) {
			puts("pair programming\n");
		} else {
			puts("extreme programming\n");
		}
	}

	return 0;
}
