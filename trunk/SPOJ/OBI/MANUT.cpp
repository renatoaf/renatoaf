#include <stdio.h>

#define MAX 400

int cnt, raiz, numFilhosRaiz; // contador global de visitacao no dfs, raiz do dfs
int num[MAX]; // se num[i] = k, i foi o k-esimo a ser visitado
int low[MAX]; // menor numero de vertice que se atinge com zero ou mais arestas na arvore
int visitado[MAX], adj[MAX][MAX], pai[MAX], articulacao[MAX]; // dfs

int min(int a, int b) {
	return a < b ? a : b;
}

void dfs(int v, int n) {
	visitado[v] = 1;
	low[v] = num[v] = cnt++;
	for (int u = 0; u < n; u++)
		if (adj[v][u])
			if (!visitado[u]) { // desce na arvore
				if (v == raiz)
					numFilhosRaiz++;
				pai[u] = v;
				dfs(u, n);
				if (low[u] >= num[v]) // v eh P.A. se tiver um filho u tal que Low(u) >= Num(v)
					articulacao[v] = 1;
				low[v] = min(low[v], low[u]);
			} else if (pai[v] != u) // se nao for aresta de retorno
				low[v] = min(low[v], num[u]);
}

void getPontosDeArticulacao(int raizDFS, int n) {
	raiz = raizDFS, cnt = numFilhosRaiz = 0;
	for (int i = 0; i < n; i++)
		num[i] = low[i] = visitado[i] = articulacao[i] = 0, pai[i] = i;
	dfs(raizDFS, n);
	articulacao[raizDFS] = numFilhosRaiz > 1; // raiz eh P.A. sss tiver mais de 1 filho na arvore
}

int main() {
	int N, M, u, v, caso = 1;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0)
			break;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				adj[i][j] = 0;

		for (int i = 0; i < M; i++) {
			scanf("%d %d", &u, &v);
			u--, v--;
			adj[u][v] = adj[v][u] = 1;
		}

		getPontosDeArticulacao(0, N);

		int primeiro = 1;
		printf("Teste %d\n", caso++);
		for (int i = 0; i < N; i++)
			if (articulacao[i]) {
				if (!primeiro)
					printf(" ");
				primeiro = 0;
				printf("%d", i + 1);
			}
		if (primeiro == 1)
			printf("nenhum");
		printf("\n\n");
	}

	return 0;
}
