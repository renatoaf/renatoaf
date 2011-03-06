#include <stdio.h>
#include <vector>
#include <queue>
#include <limits.h>

using namespace std;

#define MAX 1010
#define INF (INT_MAX/2)

int distHP[MAX], magiasMP[MAX], magiasHP[MAX]; // dijkstraMonstro
int dist[MAX], manaPorSalao[MAX]; // dijkstra
int mana[MAX]; // memorization
vector<int> adj[MAX]; // listas de adjacencia

struct Aresta {
	int destino, peso;

	Aresta() {
	}

	Aresta(int d, int p) :
		destino(d), peso(p) {
	}

	bool operator <(const Aresta &outra) const {
		return peso > outra.peso;
	}
};

int dijkstraMonstro(int HP, int N_MAGIAS) {
	for (int i = 0; i <= MAX; i++)
		distHP[i] = INF;
	distHP[0] = 0;
	priority_queue<Aresta> q;
	q.push(Aresta(0, 0));

	while (!q.empty()) {
		Aresta atual = q.top();
		q.pop();

		if (atual.destino >= HP) {
			distHP[HP] = atual.peso;
			break;
		} else if (distHP[atual.destino] < atual.peso)
			continue;

		for (int i = 0; i < N_MAGIAS; i++) {
			int v = atual.destino + magiasHP[i];
			int w = atual.peso + magiasMP[i];
			if (w < distHP[v]) {
				distHP[v] = w;
				q.push(Aresta(v, w));
			}
		}
	}

	return distHP[HP];
}

int dijkstra(int fonte, int destino, int N) {
	for (int i = 0; i <= N; i++)
		dist[i] = INF;
	dist[fonte] = 0;
	priority_queue<Aresta> q;
	q.push(Aresta(fonte, 0));

	while (!q.empty()) {
		Aresta atual = q.top();
		q.pop();

		if (atual.destino == destino)
			break;
		else if (dist[atual.destino] < atual.peso)
			continue;

		int grau = adj[atual.destino].size();
		for (int i = 0; i < grau; i++) {
			int v = adj[atual.destino][i];
			int w = atual.peso + manaPorSalao[atual.destino]; // atual + pesoAresta
			if (w < dist[v]) {
				dist[v] = w;
				q.push(Aresta(v, w));
			}
		}
	}

	return dist[destino];
}

void reset() {
	for (int i = 0; i < MAX; i++)
		mana[i] = -1, manaPorSalao[i] = 0, adj[i].clear();
}

int main() {
	int M, N, G, K, u, v, salao, hp;

	while (scanf("%d %d %d %d", &M, &N, &G, &K) && (M || N || G || K)) {
		reset();

		for (int i = 0; i < M; i++)
			scanf("%d %d", &magiasMP[i], &magiasHP[i]);

		for (int i = 0; i < G; i++) {
			scanf("%d %d", &u, &v);
			u--, v--;
			adj[u].push_back(v);
			adj[v].push_back(u);
		}

		adj[N - 1].push_back(N);
		for (int i = 0; i < K; i++) {
			scanf("%d %d", &salao, &hp);
			if (mana[hp] == -1)
				mana[hp] = dijkstraMonstro(hp, M);
			manaPorSalao[salao - 1] += mana[hp];
		}

		int min = dijkstra(0, N, N + 1); // vertice 'N' eh o vertice 'lado de fora'
		if (min >= INF)
			printf("-1\n");
		else
			printf("%d\n", min);
	}

	return 0;
}
