#include <stdio.h>
#include <climits>
#include <vector>
#include <queue>

using namespace std;

#define MAXV 1005
#define INF (INT_MAX/2)

struct Edge {
	int d, w;

	Edge() {
	}

	Edge(int d, int w) :
		d(d), w(w) {
	}
};

int dist[MAXV], ordenacaoTop[MAXV], indegree[MAXV], degree[MAXV];
vector<Edge> adj[MAXV];

void liga(int u, int v, int w) {
	degree[u]++;
	indegree[v]++;
	adj[u].push_back(Edge(v, w));
}

void topsort(int N) {
	queue<int> q; // Vertices de indegree (no de arestas que 'entram') 0
	int atual, proximo, j = 0;
	for (int i = 0; i < N; i++)
		if (indegree[i] == 0)
			q.push(i);
	while (!q.empty()) {
		atual = q.front();
		q.pop();
		ordenacaoTop[j++] = atual;
		for (int i = 0; i < degree[atual]; i++) {
			proximo = adj[atual][i].d;
			indegree[proximo]--;
			if (indegree[proximo] == 0)
				q.push(proximo);
		}
	}
}

int DAGMax(int fonte, int destino, int N) {
	topsort(N);
	for (int v = 0; v < N; v++)
		dist[v] = -INF; // trocar por +INF para achar o menor cam.
	dist[fonte] = 0;
	for (int i = 0, v = ordenacaoTop[i]; i < N; v = ordenacaoTop[i++])
		for (int j = 0; j < degree[v]; j++) {
			Edge p = adj[v][j];
			if (dist[p.d] < dist[v] + p.w) // trocar por > para achar o menor cam.
				dist[p.d] = dist[v] + p.w;
		}
	return dist[destino]; // dist[u] = distancia de fonte ate u
}

int main() {
	int N, Q, P, v;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;

		for (int u = 0; u <= N + 1; u++) {
			indegree[u] = 0, degree[u] = 0;
			adj[u].clear();
		}

		for (int u = 1; u <= N; u++) {
			scanf("%d %d", &Q, &P);

			liga(0, u, 0);
			liga(u, N + 1, Q);

			for (int j = 0; j < P; j++) {
				scanf("%d", &v);
				liga(u, v, Q);
			}
		}

		printf("%d\n", DAGMax(0, N + 1, N + 2));
	}

	return 0;
}
