/*
 * Haunted Graveyard
 */

#include <stdio.h>
#include <vector>
#include <string.h>

using namespace std;

#define INF (INT_MAX/3)
#define MAX 30
#define MAXVERT (MAX*MAX)
#define MAXEDGE (MAXVERT*4)
#define GRAMA 0
#define GRAVEYARD 1

/* bellman ford */

struct Edge {
	int u, v, w;

	Edge() {
	}
	Edge(int i, int j, int t) {
		u = i, v = j, w = t;
	}
};

int dist[MAXVERT];
Edge edges[MAXEDGE];

bool bellmanFord(int fonte, int n, int m) {
	for (int i = 0; i < n; i++)
		dist[i] = INF;
	dist[fonte] = 0;
	for (int i = 0; i < n - 1; ++i)
		for (int j = 0; j < m; j++) {
			int u = edges[j].u, v = edges[j].v, w = edges[j].w;
			if (dist[u] != INF && dist[v] > dist[u] + w) // dist[u] != INF (se for atingivel -> do problema)
				dist[v] = dist[u] + w;
		}
	for (int i = 0; i < m; i++) { // checa ciclos de custo negativo
		int u = edges[i].u, v = edges[i].v, w = edges[i].w;
		if (dist[u] != INF && dist[v] > dist[u] + w) // dist[u] != INF (se for atingivel -> do problema)
			return false; // tem ciclo de custo negativo e atingivel
	}
	return true;
}

/* questao */

struct Hole {
	int x, y, w;

	Hole() {
	}
	Hole(int di, int dj, int t) {
		x = di, y = dj, w = t;
	}
};

int grid[MAX][MAX], adj[4][2] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

int no(int x, int y, int W) {
	return x + (W * y);
}

int main() {
	int W, H, G, E, X, Y, X2, Y2, T;

	while (1) {
		scanf("%d %d", &W, &H);

		if (W == 0 && H == 0)
			break;

		vector<Hole> holes;
		int N_EDGES = 0;
		int H_ATUAL = (GRAVEYARD + 1);

		scanf("%d", &G);
		for (int i = 0; i < G; i++) {
			scanf("%d %d", &X, &Y);
			grid[X][Y] = GRAVEYARD;
		}

		scanf("%d", &E);
		for (int i = 0; i < E; i++) {
			scanf("%d %d %d %d %d", &X, &Y, &X2, &Y2, &T);
			grid[X][Y] = H_ATUAL++;
			holes.push_back(Hole(X2, Y2, T));
		}

		for (int i = 0; i < W; i++)
			for (int j = 0; j < H; j++) {
				if (i != W - 1 || j != H - 1) { // ultimo nao precisa de aresta
					int noAtual = no(i, j, W);
					if (grid[i][j] == GRAMA) {
						for (int k = 0; k < 4; k++) {
							int di = i + adj[k][0];
							int dj = j + adj[k][1];
							if (di >= 0 && dj >= 0 && di < W && dj < H
									&& grid[di][dj] != GRAVEYARD)
								edges[N_EDGES++] = Edge(noAtual, no(di, dj, W),
										1);
						}
					} else if (grid[i][j] > GRAVEYARD) {
						Hole h = holes[grid[i][j] - (GRAVEYARD + 1)];
						edges[N_EDGES++] = Edge(noAtual, no(h.x, h.y, W), h.w);
					}
				}
			}

		int FONTE = no(0, 0, W);
		int DESTINO = no(W - 1, H - 1, W);

		bool temCicloNegativo = !bellmanFord(FONTE, W * H, N_EDGES);
		if (temCicloNegativo)
			puts("Never");
		else if (dist[DESTINO] == INF)
			puts("Impossible");
		else
			printf("%d\n", dist[DESTINO]);

		for (int i = 0; i < W; i++)
			for (int j = 0; j < H; j++)
				grid[i][j] = GRAMA;
	}

	return 0;
}
