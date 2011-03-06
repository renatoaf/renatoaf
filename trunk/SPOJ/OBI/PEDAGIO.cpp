#include <stdio.h>
#include <string.h>
#include <queue>

using namespace std;

#define MAX 50

struct Estado {
	int pedagios, vertice;

	Estado() {
	}

	Estado(int v, int p) :
		pedagios(p), vertice(v) {
	}
};

int adj[MAX][MAX], visitados[MAX];

void bfs(int fonte, int maximo, int n) {
	for (int i = 0; i < n; i++)
		visitados[i] = 0;
	queue<Estado> q;
	q.push(Estado(fonte, 0));

	while (!q.empty()) {
		Estado atual = q.front();
		q.pop();

		if (atual.pedagios > maximo)
			continue;

		visitados[atual.vertice] = 1;
		for (int u = 0; u < n; u++)
			if (adj[atual.vertice][u] && !visitados[u])
				q.push(Estado(u, atual.pedagios + 1));
	}

	visitados[fonte] = 0; // ignora a cidade inicial como visitada
}

int main() {
	int C, E, L, P, u, v, caso = 1;

	while (1) {
		scanf("%d %d %d %d", &C, &E, &L, &P);

		if (C == 0 && E == 0 && L == 0 && P == 0)
			break;

		for (int i = 0; i < C; i++)
			for (int j = 0; j < C; j++)
				adj[i][j] = 0;

		for (int i = 0; i < E; i++) {
			scanf("%d %d", &u, &v);
			u--, v--;
			adj[u][v] = adj[v][u] = 1;
		}

		bfs(L - 1, P, C);

		printf("Teste %d\n", caso++);
		bool primeiro = true;
		for (int i = 0; i < C; i++)
			if (visitados[i]) {
				if (!primeiro)
					printf(" ");
				printf("%d", i + 1);
				primeiro = false;
			}
		printf("\n\n");
	}

	return 0;
}
