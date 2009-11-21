#include <string.h>
#include <limits.h>
#include <stdio.h>

using namespace std;

#define INF (INT_MAX/2)
#define NN 1024
#define CLR(a, x) memset(a, x, sizeof(a))

int cap[NN][NN]; // capacity
int cost[NN][NN]; // cost
int fnet[NN][NN], adj[NN][NN], deg[NN]; // flow, adjacency list, degree
int par[NN], d[NN]; // for dijkstra
int pi[NN]; // labelling

int min(int a, int b) {
	return a < b ? a : b;
}

bool dijkstra(int n, int s, int t) {
	for (int i = 0; i < n; i++)
		d[i] = INF, par[i] = -1;
	d[s] = 0;
	par[s] = -n - 1;
	while (1) {
		int u = -1, bestD = INF;
		for (int i = 0; i < n; i++)
			if (par[i] < 0 && d[i] < bestD)
				bestD = d[u = i];
		if (bestD == INF)
			break;
		par[u] = -par[u] - 1;
		for (int i = 0; i < deg[u]; i++) {
			int v = adj[u][i];
			if (par[v] >= 0)
				continue;
			int pot = (d[u] + pi[u] - pi[v]);
			if (fnet[v][u] && d[v] > pot - cost[v][u])
				d[v] = pot - cost[v][u], par[v] = -u - 1;
			pot = (d[u] + pi[u] - pi[v]);
			if (fnet[u][v] < cap[u][v] && d[v] > pot + cost[u][v])
				d[v] = pot + cost[u][v], par[v] = -u - 1;
		}
	}
	for (int i = 0; i < n; i++)
		if (pi[i] < INF)
			pi[i] += d[i];
	return par[t] >= 0;
}

/*
 * Colocar demanda = INF se quiser pegar o maior fluxo enquanto houver caminho
 */
int minCostMaxFlow(int n, int s, int t, int &fcost, int demanda) {
	CLR(adj, 0);
	CLR(deg, 0);
	CLR(fnet, 0);
	CLR(pi, 0);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			if (cap[i][j] || cap[j][i])
				adj[i][deg[i]++] = j;
	int flow = fcost = 0;
	// repeatedly, find a cheapest path from s to t
	while (dijkstra(n, s, t) && flow < demanda) {
		int bot = INT_MAX;
		for (int v = t, u = par[v]; v != s; u = par[v = u]) {
			bot = min(bot, fnet[v][u] ? fnet[v][u] : (cap[u][v] - fnet[u][v]));
		}
		if ((flow + bot) > demanda) { // se for maior que a demanda, ajustar
			bot = demanda - flow;
		}
		for (int v = t, u = par[v]; v != s; u = par[v = u])
			if (fnet[v][u]) {
				fnet[v][u] -= bot;
				fcost -= bot * cost[v][u];
			} else {
				fnet[u][v] += bot;
				fcost += bot * cost[u][v];
			}
		flow += bot;
	}
	return flow;
}

int main() {
	int i, j, N, M, A, B, C, D, K, caso = 1;

	while (scanf("%d %d", &N, &M) != EOF) {
		CLR(cap, 0);
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				cost[i][j] = INF;
			}
		}

		for (i = 0; i < M; i++) {
			scanf("%d %d %d", &A, &B, &C);
			A--;
			B--;
			cost[A][B] = C;
			cost[B][A] = C;
		}

		scanf("%d %d", &D, &K);
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				if (cost[i][j] != INF) {
					cap[i][j] = K;
				}
			}
		}

		int fcost;
		int flow = minCostMaxFlow(N, 0, N - 1, fcost, D);

		printf("Instancia %d\n", caso++);
		if (flow < D) { // nao atingiu todo o numero de amigos
			printf("impossivel\n\n");
		} else {
			printf("%d\n\n", fcost);
		}
	}

	return 0;
}
