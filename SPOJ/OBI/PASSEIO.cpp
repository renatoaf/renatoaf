#include <stdio.h>
#include <vector>
#include <queue>

using namespace std;

#define MAX 150
#define INF 1000

int altitude[MAX], dist[MAX], ordenacaoTop[MAX], indegree[MAX];
vector<int> adj[MAX];

void liga(int u, int v) {
	indegree[v]++;
	adj[u].push_back(v);
}

void topsort(int N) {
	queue<int> q; // Vertices de indegree (no de arestas que 'entram') 0
	int atual, proximo, degree, j = 0;
	for (int i = 0; i < N; i++)
		if (indegree[i] == 0)
			q.push(i);
	while (!q.empty()) {
		atual = q.front();
		q.pop();
		ordenacaoTop[j++] = atual;
		degree = adj[atual].size();
		for (int i = 0; i < degree; i++) {
			proximo = adj[atual][i];
			indegree[proximo]--;
			if (indegree[proximo] == 0)
				q.push(proximo);
		}
	}
}

void DAGMax(int fonte, int N) {
	topsort(N);
	for (int v = 0; v < N; v++)
		dist[v] = -INF; // trocar por +INF para achar o menor cam.
	dist[fonte] = 0;
	for (int i = 0, v = ordenacaoTop[i]; i < N; v = ordenacaoTop[i++])
		for (int j = 0, degree = adj[v].size(); j < degree; j++) {
			int p = adj[v][j];
			if (dist[p] < dist[v] + 1) // trocar por > para achar o menor cam.
				dist[p] = dist[v] + 1; // 1 pois todas arestas tem peso 1
		}
}

int main() {
	int P, L, I, caso = 1, u, v;

	while (1) {
		scanf("%d %d %d", &P, &L, &I);

		if (P == 0 && L == 0 && I == 0)
			break;

		for (int i = 0; i < P; i++) {
			scanf("%d", &altitude[i]);
			adj[i].clear();
			indegree[i] = 0;
		}

		for (int i = 0; i < L; i++) {
			scanf("%d %d", &u, &v);
			if (altitude[u - 1] > altitude[v - 1])
				liga(u - 1, v - 1);
		}

		DAGMax(I - 1, P);
		int maiorPasseio = -1;
		for (int i = 0; i < P; i++)
			if (dist[i] > maiorPasseio)
				maiorPasseio = dist[i];

		printf("Teste %d\n%d\n\n", caso++, maiorPasseio);
	}

	return 0;
}
