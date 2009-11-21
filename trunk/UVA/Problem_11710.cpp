/*
 * Expensive Subway
 */

#include <stdio.h>
#include <algorithm>
#include <map>
#include <vector>
#include <string>

#define MAXN 500
#define MAXLEN 15

using namespace std;

char estacao1[MAXLEN], estacao2[MAXLEN];

/* mst */

struct Edge {
	int o, d, p;

	Edge() {
	}

	Edge(int orig, int dest, int peso) {
		o = orig;
		d = dest;
		p = peso;
	}
};

int parent[MAXN], rank[MAXN];

bool compare(Edge t1, Edge t2) {
	return t1.p < t2.p; // usar > se quiser a arvore geradora de peso maximo
}

int FIND(int x) {
	if (parent[x] == x) {
		return x;
	} else {
		parent[x] = FIND(parent[x]);
		return parent[x];
	}
}

void UNION(int x, int y) {
	int xRoot = FIND(x);
	int yRoot = FIND(y);
	if (rank[xRoot] > rank[yRoot])
		parent[yRoot] = xRoot;
	else if (rank[xRoot] < rank[yRoot])
		parent[xRoot] = yRoot;
	else if (xRoot != yRoot)
		parent[yRoot] = xRoot, rank[xRoot] = rank[xRoot] + 1;
}

int kruskal(vector<Edge> arestas, int n) {
	int vertices = n, custoTotal = 0, m = arestas.size();
	sort(arestas.begin(), arestas.end(), compare);
	for (int i = 0; i < n; i++)
		parent[i] = i, rank[i] = 0;
	for (int i = 0; vertices > 1 && i < m; i++) {
		Edge e = arestas[i];
		int parentA = FIND(e.o);
		int parentB = FIND(e.d);
		if (parentA != parentB) {
			custoTotal += e.p;
			UNION(e.o, e.d);
			vertices--;
		}
	}
	if (vertices == 1)
		return custoTotal;
	return -1;
}

int main() {
	int N, M, peso;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0)
			break;

		map<string, int> estacoes;
		vector<Edge> arestas;

		for (int i = 0; i < N; i++) {
			scanf("%s", estacao1);
			estacoes[string(estacao1)] = i;
		}

		for (int i = 0; i < M; i++) {
			scanf("%s %s %d", estacao1, estacao2, &peso);
			int u = estacoes[string(estacao1)];
			int v = estacoes[string(estacao2)];
			arestas.push_back(Edge(u, v, peso));
			arestas.push_back(Edge(v, u, peso));
		}

		scanf("%s", estacao1);

		int pesoMST = kruskal(arestas, N);
		if (pesoMST == -1)
			printf("Impossible\n");
		else
			printf("%d\n", pesoMST);
	}

	return 0;
}
