#include <stdio.h>
#include <string.h>

#define NN 210

// adjacency matrix (fill this up)
// If you fill adj[][] yourself, make sure to include both u->v and v->u.
int cap[NN][NN], deg[NN], adj[NN][NN];
// BFS stuff
int q[NN], prev[NN];

int min(int a, int b) {
	return a < b ? a : b;
}

int dinic(int n, int s, int t) {
	int flow = 0, i, v, u, z;
	while (1) {
		for (i = 0; i < n; i++) {
			q[i] = 0;
			prev[i] = -1;
		}
		int qf = 0, qb = 0;
		prev[q[qb++] = s] = -2;
		while (qb > qf && prev[t] == -1)
			for (u = q[qf++], i = 0, v = 0; i < deg[u]; i++)
				if (prev[v = adj[u][i]] == -1 && cap[u][v])
					prev[q[qb++] = v] = u;
		if (prev[t] == -1)
			break;
		for (z = 0; z < n; z++)
			if (cap[z][t] && prev[z] != -1) {
				int bot = cap[z][t];
				for (v = z, u = prev[v]; u >= 0; v = u, u = prev[v])
					bot = min(bot, cap[u][v]);
				if (!bot)
					continue;
				cap[z][t] -= bot;
				cap[t][z] += bot;
				for (v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {
					cap[u][v] -= bot;
					cap[v][u] += bot;
				}
				flow += bot;
			}
	}
	return flow;
}

int main() {
	int n, m, k, ci, u, v, i, j, caso = 1;

	while (scanf("%d %d %d", &n, &m, &k) != EOF) {
		memset(cap, 0, sizeof(cap));
		memset(deg, 0, sizeof(deg));
		memset(adj, 0, sizeof(adj));

		int fonte = 0;
		int destino = n + m + 1;
		int totalVertices = destino + 1; // total de vertices

		for (i = 0; i < totalVertices; i++) {
			for (j = 0; j < deg[i]; j++) {
				adj[i][j] = 0;
			}
			for (j = 0; j < totalVertices; j++) {
				cap[i][j] = 0;
			}
			deg[i] = 0;
		}

		for (i = 1; i <= n; i++) {
			// liga vertice inicial aos cavalos com capacidade ci
			scanf("%d", &ci);
			cap[fonte][i] = ci;
		}

		for (i = 1; i <= m; i++) {
			// liga soldados ao vertice final com capacidade 1
			cap[n + i][destino] = 1;
		}

		for (i = 0; i < k; i++) { // afinidades
			scanf("%d %d", &u, &v); // cavalo u e soldado v
			cap[u][n + v] = 1;
		}

		for (u = 0; u < totalVertices; u++)
			for (v = 0; v < totalVertices; v++)
				if (cap[u][v] || cap[v][u])
					adj[u][deg[u]++] = v;

		printf("Instancia %d\n", caso++);
		printf("%d\n\n", dinic(totalVertices, fonte, destino));
	}

	return 0;
}
