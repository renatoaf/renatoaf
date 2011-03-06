#include <stdio.h>
#include <vector>

using namespace std;

#define MAX 101

int visitado[MAX], numVisitados;
vector<int> adj[MAX];

void dfs(int v) {
	visitado[v] = 1;
	numVisitados++;
	int grau = adj[v].size();
	for (int i = 0; i < grau; i++)
		if (!visitado[adj[v][i]])
			dfs(adj[v][i]);
}

bool conectado(int N) { // dado que as arestas sao bi-direcionais, basta um dfs
	for (int i = 0; i < N; i++)
		visitado[i] = 0;
	numVisitados = 0;
	dfs(0);
	return numVisitados == N;
}

int main() {
	int E, L, u, v, caso = 1;

	while (1) {
		scanf("%d %d", &E, &L);

		if (E == 0 && L == 0)
			break;

		for (int i = 0; i < E; i++)
			adj[i].clear();

		for (int i = 0; i < L; i++) {
			scanf("%d %d", &u, &v);
			u--, v--;
			adj[u].push_back(v);
			adj[v].push_back(u);
		}

		printf("Teste %d\n", caso++);
		puts(conectado(E) ? "normal\n" : "falha\n");
	}
}
