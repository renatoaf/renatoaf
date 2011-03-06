#include <stdio.h>
#include <string>
#include <vector>
#include <map>

#define MAX_LEN 30
#define MAX_VER 5000

using namespace std;

char cadeia[MAX_LEN], cadeia2[MAX_LEN];
vector<int> adj[MAX_VER];
int tamanhoCadeia, vis[MAX_VER];

void dfs(int u) {
	tamanhoCadeia++;
	vis[u] = 1;

	int grau = adj[u].size();
	for (int v = 0; v < grau; v++) {
		int next = adj[u][v];
		if (!vis[next])
			dfs(next);
	}
}

int getTamCadeia(int u, int n) {
	for (int i = 0; i < n; i++)
		vis[i] = 0;
	tamanhoCadeia = 0;
	dfs(u);
	return tamanhoCadeia;
}

int main() {
	int C, R, u, v;

	while (1) {
		scanf(" %d %d", &C, &R);

		if (C == 0 && R == 0)
			break;

		map<string, int> criaturas;

		for (int i = 0; i < C; i++) {
			scanf("%s", cadeia);
			criaturas[string(cadeia)] = i;
			adj[i].clear();
		}

		for (int i = 0; i < R; i++) {
			scanf("%s %s", cadeia, cadeia2);
			u = criaturas[string(cadeia)], v = criaturas[string(cadeia2)];
			adj[u].push_back(v);
			adj[v].push_back(u);
		}

		int maiorCadeia = 1;
		for (int i = 0; i < C; i++) {
			int tam = getTamCadeia(i, C);
			if (tam > maiorCadeia)
				maiorCadeia = tam;
		}

		printf("%d\n", maiorCadeia);
	}

	return 0;
}
