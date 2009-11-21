/*
 * Trust Groups
 */

#include <stdio.h>
#include <map>
#include <string.h>
#include <string>
#include <set>

#define NN 1010

using namespace std;

int deg[NN];
int adj[NN][NN];
int dfsn[NN], dfsnext;
int mindfsn[NN];
int comp[NN], ncomp;
bool incomp[NN];
int uf[NN];
char pessoa[30], pessoa2[30];

int FIND(int x) {
	return uf[x] == x ? x : uf[x] = FIND(uf[x]);
}

void UNION(int x, int y) {
	uf[FIND(x)] = FIND(y);
}

void dfs(int n, int u) {
	dfsn[u] = mindfsn[u] = dfsnext++;
	incomp[comp[ncomp++] = u] = true;
	for (int i = 0, v; v = adj[u][i], i < deg[u]; i++) {
		if (!dfsn[v])
			dfs(n, v);
		if (incomp[v])
			mindfsn[u] = min(mindfsn[u], mindfsn[v]);
	}
	if (dfsn[u] == mindfsn[u]) {
		do {
			UNION(u, comp[--ncomp]);
			incomp[comp[ncomp]] = false;
		} while (comp[ncomp] != u);
	}
}

void scc(int n) {
	for (int i = 0; i < n; i++)
		dfsn[uf[i] = i] = ncomp = incomp[i] = 0;
	dfsnext = 1;
	for (int i = 0; i < n; i++)
		if (!dfsn[i])
			dfs(n, i);
}

int main() {
	int p, t, i;

	while (1) {
		scanf("%d %d ", &p, &t);

		if (p == 0 && t == 0) {
			break;
		}

		memset(adj, 0, sizeof(adj));

		for (i = 0; i < p; i++) {
			deg[i] = 0;
		}

		map<string, int> pessoas;
		for (i = 0; i < p; i++) {
			gets(pessoa);
			pessoas[string(pessoa)] = i;
		}

		for (i = 0; i < t; i++) {
			gets(pessoa);
			gets(pessoa2);
			int u = pessoas[string(pessoa)];
			int v = pessoas[string(pessoa2)];
			adj[u][deg[u]++] = v;
		}

		scc(p);
		set<int> componentes;
		for (i = 0; i < p; i++) {
			componentes.insert(FIND(i));
		}
		printf("%d\n", componentes.size());
	}

	return 0;
}
