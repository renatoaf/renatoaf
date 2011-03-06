#include <stdio.h>
#include <vector>

using namespace std;

#define MAX 300

int arestasNecessarias, visitados[MAX], precisoVisitar[MAX], usei[MAX][MAX];
vector<int> adj[MAX];

int dfs(int v, int n) { // retorna 1 se a visita foi necessaria, 0 se desnecessaria
	int necessaria = precisoVisitar[v];
	visitados[v] = 1;
	int grau = adj[v].size();
	for (int i = 0; i < grau; i++) {
		int u = adj[v][i];
		if (!visitados[u])
			if (dfs(u, n)) {
				necessaria = 1; // se a filha eh necessaria, visita foi necessaria
				arestasNecessarias++;
			}
	}
	return necessaria;
}

int getMinTickets(int n) {
	// grafo eh uma arvore, soh ha um caminho para cada vertice, basta explorar
	// cada ramo, ate visitar todas as necessarias, e ir contando as arestas uteis
	for (int i = 0; i < n; i++)
		visitados[i] = 0;
	arestasNecessarias = 0;
	dfs(0, n);
	return arestasNecessarias * 2; // ida e volta
}

int main() {
	int C, V, u, v, caso = 1;

	while (scanf("%d %d", &C, &V) && (C || V)) {
		for (int i = 0; i < C - 1; i++) {
			scanf("%d %d", &u, &v);
			u--, v--;
			adj[u].push_back(v);
			adj[v].push_back(u);
		}

		for (int i = 0; i < V; i++)
			scanf("%d", &u), precisoVisitar[u - 1] = 1;

		printf("Teste %d\n%d\n\n", caso++, getMinTickets(C));

		for (int i = 0; i < C; i++)
			adj[i].clear(), precisoVisitar[i] = 0;
	}

	return 0;
}
