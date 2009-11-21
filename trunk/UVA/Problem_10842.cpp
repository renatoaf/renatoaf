/*
 * Traffic Flow
 */

#include <stdio.h>
#include <algorithm>
#include <vector>

#define MAXN 100

using namespace std;

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

vector<Edge> mst;
int parent[MAXN], rank[MAXN];

bool compare(Edge t1, Edge t2) {
	return t1.p > t2.p; // usar < se quiser a arvore geradora de peso minimo
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

// nao coloca lacos!
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
			mst.push_back(e);
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
	int T, N, M, u, v, w;

	scanf("%d", &T);
	for (int c = 1; c <= T; c++) {
		scanf("%d %d", &N, &M);

		mst.clear();

		bool sohUm = true;

		vector<Edge> arestas;
		for (int i = 0; i < M; i++) {
			scanf("%d %d %d", &u, &v, &w);
			arestas.push_back(Edge(u, v, w));
			arestas.push_back(Edge(v, u, w));
			if (u != v)
				sohUm = false;
		}

		if (sohUm) {
			// aresta de maior capacidade, que seria escolhida pela mst
			int maxCap = 0;
			for (int i = 0; i < 2* M ; i++) {
				if (arestas[i].p > maxCap)
					maxCap = arestas[i].p;
			}
			printf("Case #%d: %d\n", c, maxCap);
			continue;
		}

		kruskal(arestas, N);

		int minCap = INT_MAX;
		int tamMST = mst.size();
		for (int i = 0; i < tamMST; i++)
			if (mst[i].p < minCap)
				minCap = mst[i].p;
		printf("Case #%d: %d\n", c, minCap);
	}

	return 0;
}
