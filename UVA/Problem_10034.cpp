/*
 * Freckles
 */

#include <stdio.h>
#include <algorithm>
#include <queue>
#include <cmath>

struct Ponto {
	double x, y;

	Ponto() {
	}

	Ponto(double xi, double yi) {
		x = xi;
		y = yi;
	}
};

#define MAXN 100
#define INF (INT_MAX)/2

using namespace std;

/* mst */

int visit[MAXN];
double dist[MAXN], adj[MAXN][MAXN];

double prim(int N) {
	for (int i = 0; i < N; i++)
		visit[i] = 0, dist[i] = INF;
	dist[0] = 0;
	priority_queue<pair<double, int> > pq;
	pq.push(make_pair(0.0, 0));
	while (!pq.empty()) {
		pair<double, int> p = pq.top();
		pq.pop();
		int u = p.second;
		if (visit[u])
			continue;
		visit[u] = true;
		for (int v = 0; v < N; v++)
			if (v != u && !visit[v])
				if (adj[u][v] < dist[v]) {
					dist[v] = adj[u][v];
					pq.push(make_pair(-dist[v], v));
				}
	}
	double custoMST = 0;
	for (int i = 0; i < N; i++)
		custoMST += dist[i];
	return custoMST;
}

/* questao */

Ponto pontos[MAXN];

double distancia(Ponto p1, Ponto p2) {
	double q1 = (p2.x - p1.x) * (p2.x - p1.x);
	double q2 = (p2.y - p1.y) * (p2.y - p1.y);
	return sqrt(q1 + q2);
}

int main() {
	int T, N;
	double x, y;

	scanf("%d", &T);
	for (int c = 1; c <= T; c++) {
		if (c > 1)
			printf("\n");

		scanf(" %d", &N);

		for (int i = 0; i < N; i++) {
			scanf("%lf %lf", &x, &y);
			pontos[i] = Ponto(x, y);
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < i; j++)
				adj[i][j] = adj[j][i] = distancia(pontos[i], pontos[j]);

		double pesoMST = prim(N);
		printf("%.2lf\n", pesoMST);
	}

	return 0;
}
