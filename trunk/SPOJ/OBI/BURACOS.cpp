#include <stdio.h>
#include <vector>

using namespace std;

#define MAX 3000

int visitados[MAX], nVis;
vector<int> g1[MAX], g2[MAX];

void dfs1(int v, int n) {
	visitados[v] = 1, nVis++;
	int grau = g1[v].size();
	for (int i = 0; i < grau; i++)
		if (!visitados[g1[v][i]])
			dfs1(g1[v][i], n);
}

void dfs2(int v, int n) {
	visitados[v] = 1, nVis++;
	int grau = g2[v].size();
	for (int i = 0; i < grau; i++)
		if (!visitados[g2[v][i]])
			dfs2(g2[v][i], n);
}

int contaAcessiveis(int n, int g) {
	for (int i = 0; i < n; i++)
		visitados[i] = 0;
	nVis = 0;
	if (g == 1)
		dfs1(0, n);
	else if (g == 2)
		dfs2(0, n);
	return nVis;
}

int fortementeConexo(int n) {
	return contaAcessiveis(n, 1) == n && contaAcessiveis(n, 2) == n;
}

int main() {
	int N, M, u, v, caso = 1;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0)
			break;

		for (int i = 0; i < M; i++) {
			scanf("%d %d", &u, &v);
			u--, v--;
			g1[u].push_back(v);
			g2[v].push_back(u);
		}

		printf("Teste %d\n", caso++);
		puts(fortementeConexo(N) ? "S\n" : "N\n");

		for (int i = 0; i < N; i++) {
			g1[i].clear();
			g2[i].clear();
		}
	}

	return 0;
}
