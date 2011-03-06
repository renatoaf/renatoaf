#include <stdio.h>
#include <vector>

using namespace std;

#define MAX 7

int encontrados, visitados[MAX];
vector<int> adj[MAX];

int dfs(int v) {
	visitados[v] = 1;
	encontrados++;
	int grau = adj[v].size();
	for (int u = 0; u < grau; u++)
		if (!visitados[adj[v][u]])
			dfs(adj[v][u]);
	return encontrados;
}

int temCaminhoEuleriano() {
	// admite caminho euleriano sss eh conexo e no maximo 2 vertices tem grau impar
	encontrados = 0;
	int partidaDFS = 0, numVertices = 0, grauImpar = 0;
	for (int i = 0; i < MAX; i++) {
		if (adj[i].size() > 0)
			partidaDFS = i, numVertices++;
		if (adj[i].size() % 2 != 0)
			grauImpar++;
	}
	return grauImpar <= 2 && dfs(partidaDFS) == numVertices;
}

int main() {
	int N, u, v, caso = 1;

	while (scanf("%d", &N) && N) {
		for (int i = 0; i < N; i++) {
			scanf("%d %d", &u, &v);
			adj[u].push_back(v);
			adj[v].push_back(u);
		}

		printf("Teste %d\n", caso++);
		puts(temCaminhoEuleriano() ? "sim\n" : "nao\n");

		for (int i = 0; i < MAX; i++)
			visitados[i] = 0, adj[i].clear();
	}

	return 0;
}
