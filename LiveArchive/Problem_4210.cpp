/*
 * Almost Shortest Path
 */

#include <stdio.h>
#include <vector>
#include <queue>

using namespace std;

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

vector<Aresta> grafo[501];
vector<int> predecessores[501];
int INF = INT_MAX;
bool proibida[501][501]; // arestas proibidas
bool visitado[501]; // bfs
int dist[501]; // dijkstra

void getPredecessoresMenorCaminho(int fonte, int destino, int n) {
	int i;
	for (i = 0; i < n; i++) {
		dist[i] = INF;
	}
	dist[fonte] = 0;

	priority_queue<Aresta> fila;
	fila.push(Aresta(fonte, 0));
	while (!fila.empty()) {
		int dest = fila.top().destino;
		int peso = fila.top().peso;
		fila.pop();
		if (dist[dest] < peso) {
			continue;
		}
		vector<Aresta> arestas = grafo[dest];
		int tam = arestas.size();
		for (i = 0; i < tam; i++) {
			if (peso + arestas[i].peso < dist[arestas[i].destino]) {
				predecessores[arestas[i].destino].clear(); // ignora o q tinha antes
				predecessores[arestas[i].destino].push_back(dest); // novo predecessor
				dist[arestas[i].destino] = peso + arestas[i].peso;
				fila.push(Aresta(arestas[i].destino, dist[arestas[i].destino]));
			} else if (peso + arestas[i].peso == dist[arestas[i].destino]) {
				// pode ter mais de um menor caminho
				predecessores[arestas[i].destino].push_back(dest);
			}
		}
	}
}

int dijkstra(int fonte, int destino, int n) {
	int i;
	for (i = 0; i < n; i++) {
		dist[i] = INF;
	}
	dist[fonte] = 0;
	priority_queue<Aresta> fila;
	fila.push(Aresta(fonte, 0));

	while (!fila.empty()) {
		int dest = fila.top().destino;
		int peso = fila.top().peso;
		fila.pop();

		if (dist[dest] < peso) {
			continue;
		} else if (dest == destino) {
			return peso;
		}

		vector<Aresta> arestas = grafo[dest];
		int tam = arestas.size();
		for (i = 0; i < tam; i++) {
			if (!proibida[dest][arestas[i].destino]) {
				if (peso + arestas[i].peso < dist[arestas[i].destino]) {
					dist[arestas[i].destino] = peso + arestas[i].peso;
					fila.push(Aresta(arestas[i].destino,
							dist[arestas[i].destino]));
				}
			}
		}
	}

	return INF;
}

void bfs(int destino, int n) {
	int i;
	memset(visitado, 0, n);
	queue<int> fila;
	visitado[destino] = true;
	fila.push(destino);
	while (!fila.empty()) {
		int prox = fila.front();
		fila.pop();
		vector<int> pred = predecessores[prox];
		int tam = pred.size();
		for (i = 0; i < tam; i++) {
			proibida[pred[i]][prox] = true;
			if (!visitado[pred[i]]) {
				visitado[pred[i]] = true;
				fila.push(pred[i]);
			}
		}
	}
}

int main() {
	while (true) {
		int N, M, fonte, destino, i, u, v, w;

		scanf("%d %d", &N, &M);
		if (N == 0 && M == 0) {
			break;
		}
		scanf("%d %d", &fonte, &destino);

		for (i = 0; i < N; i++) {
			grafo[i].clear();
			predecessores[i].clear();
		}
		memset(proibida, 0, sizeof(proibida));

		for (i = 0; i < M; i++) {
			scanf("%d %d %d", &u, &v, &w);
			grafo[u].push_back(Aresta(v, w));
		}

		getPredecessoresMenorCaminho(fonte, destino, N);
		bfs(destino, N);
		int quaseMenorCaminho = dijkstra(fonte, destino, N);

		printf("%d\n", quaseMenorCaminho == INF ? -1 : quaseMenorCaminho);
	}

	return 0;
}
