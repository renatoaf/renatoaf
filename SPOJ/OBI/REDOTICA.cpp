#include <stdio.h>
#include <vector>
#include <algorithm>

using namespace std;

#define MAXN 100

struct Edge {
	int o, d, p;

	Edge() {
	}

	Edge(int orig, int dest, int peso) {
		o = orig, d = dest, p = peso;
	}
};

int parent[MAXN], rank[MAXN];

int min(int a, int b) {
	return a < b ? a : b;
}

int max(int a, int b) {
	return a > b ? a : b;
}

int FIND(int x) {
	if (parent[x] == x)
		return x;
	parent[x] = FIND(parent[x]);
	return parent[x];
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

bool compare(Edge t1, Edge t2) {
	return t1.p < t2.p; // usar > se quiser a arvore geradora de peso maximo
}

void kruskal(vector<Edge> arestas, int n) {
	int vertices = n, m = arestas.size();
	for (int i = 0; i <= n; i++)
		parent[i] = i, rank[i] = 0;

	sort(arestas.begin(), arestas.end(), compare);
	for (int i = 0; vertices > 1 && i < m; i++) {
		Edge e = arestas[i];
		int parentA = FIND(e.o);
		int parentB = FIND(e.d);
		if (parentA != parentB) {
			printf("%d %d\n", min(e.o, e.d), max(e.o, e.d)); // custo nao eh necessario
			UNION(e.o, e.d);
			vertices--;
		}
	}
}

int main() {
	int N, M, caso = 1;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0)
			break;

		vector<Edge> arestas;
		for (int i = 0; i < M; i++) {
			Edge e;
			scanf("%d %d %d", &e.o, &e.d, &e.p);
			arestas.push_back(e);
		}

		printf("Teste %d\n", caso++);
		kruskal(arestas, N);
		printf("\n");
	}

	return 0;
}
