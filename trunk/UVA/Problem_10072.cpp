/*
 * Bob Laptop Woolmer
 */

#include <stdio.h>
#include <string.h>
#include <climits>
#include <vector>
#include <cmath>

using namespace std;

#define FONTE 0
#define SORVEDOURO 1
#define BATSMAN 2
#define BOWLER 3
#define ALL_ROUNDER 4
#define NN 120
#define INF (INT_MAX/2)
#define EPS 1e-7

/* min cost max flow */

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

int minCostMaxFlow(int n, int s, int t, int &fcost, int demanda) {
	int flow;
	flow = fcost = 0;
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

/* questao */

void reseta() {
	memset(pi, 0, sizeof(pi));
	memset(deg, 0, sizeof(deg));
	memset(adj, 0, sizeof(adj));
	memset(cap, 0, sizeof(cap));
	memset(fnet, 0, sizeof(fnet));
}

void liga(int u, int v, int capacidade, int custo) {
	cap[u][v] = capacidade;
	cost[u][v] = custo;
}

int jogador(int i) {
	return ALL_ROUNDER + i + 1;
}

int batsmanScore(int batting, int bowling, int fielding) {
	return (int) round(0.8 * batting + 0.2 * fielding + EPS);
}

int bowlerScore(int batting, int bowling, int fielding) {
	return (int) round(0.7 * bowling + 0.1 * batting + 0.2 * fielding + EPS);
}

int allRounderScore(int batting, int bowling, int fielding) {
	return (int) round(0.4 * batting + 0.4 * bowling + 0.2 * fielding + EPS);
}

int main() {
	int MAXIMO_SCORE, N_VERT, N, BT, BL, AR, bt, bl, fl, caso = 1;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;
		else if (caso > 1)
			printf("\n");

		for (int i = 0; i < N; i++) {
			scanf("%d %d %d", &bt, &bl, &fl);

			liga(jogador(i), SORVEDOURO, 1, 0);
			liga(BATSMAN, jogador(i), 1, -batsmanScore(bt, bl, fl));
			liga(BOWLER, jogador(i), 1, -bowlerScore(bt, bl, fl));
			liga(ALL_ROUNDER, jogador(i), 1, -allRounderScore(bt, bl, fl));
		}

		scanf("%d %d %d", &BT, &BL, &AR);
		liga(FONTE, BATSMAN, BT, 0);
		liga(FONTE, BOWLER, BL, 0);
		liga(FONTE, ALL_ROUNDER, AR, 0);

		N_VERT = jogador(N);

		for (int i = 0; i < N_VERT; i++)
			for (int j = 0; j < N_VERT; j++)
				if (cap[i][j] || cap[j][i])
					adj[i][deg[i]++] = j;
		minCostMaxFlow(N_VERT, FONTE, SORVEDOURO, MAXIMO_SCORE, INF);

		vector<int> batsmen;
		vector<int> bowlers;
		vector<int> all_rounders;

		for (int i = 0; i < N; i++) {
			if (fnet[BATSMAN][jogador(i)] - fnet[jogador(i)][BATSMAN]) {
				batsmen.push_back(i + 1);
			} else if (fnet[BOWLER][jogador(i)] - fnet[jogador(i)][BOWLER]) {
				bowlers.push_back(i + 1);
			} else if (fnet[ALL_ROUNDER][jogador(i)]
					- fnet[jogador(i)][ALL_ROUNDER]) {
				all_rounders.push_back(i + 1);
			}
		}

		printf("Team #%d", caso++);
		printf("\nMaximum Effective Score = %d", -MAXIMO_SCORE);
		printf("\nBatsmen :");
		for (int i = 0; i < BT; i++) {
			printf(" %d", batsmen[i]);
		}
		printf("\nBowlers :");
		for (int i = 0; i < BL; i++) {
			printf(" %d", bowlers[i]);
		}
		printf("\nAll-rounders :");
		for (int i = 0; i < AR; i++) {
			printf(" %d", all_rounders[i]);
		}
		printf("\n");

		reseta();
	}

	return 0;
}
