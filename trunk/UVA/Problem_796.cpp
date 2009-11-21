/*
 * Critical Links
 */

#include <stdio.h>
#include <string.h>
#include <set>
#include <vector>
#include <algorithm>

#define NN 200

using namespace std;

int deg[NN], adj[NN][NN];
int dfsn[NN], dfsnext, mindfsn[NN];
int comp[NN], ncomp;
int uf[NN];
bool incomp[NN];
bool naoAchei[NN][NN];

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

int contaComponentes(int N) {
	scc(N);
	set<int> componentes;
	for (int i = 0; i < N; i++) {
		componentes.insert(FIND(i));
	}
	return componentes.size();
}

int main() {
	int N, no, qt;

	while (scanf(" %d", &N) != EOF) {
		memset(adj, 0, sizeof(adj));
		memset(deg, 0, sizeof(deg));
		memset(naoAchei, true, sizeof(naoAchei));

		for (int i = 0; i < N; i++) {
			scanf("%d (%d)", &no, &qt);
			while (qt--)
				scanf(" %d", &adj[no][deg[no]++]);
			sort(adj[no], adj[no] + deg[no]);
		}

		int componentesInit = contaComponentes(N);

		vector<pair<int, int> > criticas;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < deg[i]; j++) {
				int temp = adj[i][j];

				if (naoAchei[temp][i]) {
					adj[i][j] = -1;
					int componentesAtual = contaComponentes(N);
					adj[i][j] = temp;

					if (componentesAtual != componentesInit) {
						criticas.push_back(pair<int, int> (i, temp));
						naoAchei[i][temp] = false;
					}
				}
			}
		}

		int numArestasCriticas = criticas.size();
		printf("%d critical links\n", numArestasCriticas);
		for (int i = 0; i < numArestasCriticas; i++)
			printf("%d - %d\n", criticas[i].first, criticas[i].second);
		printf("\n");
	}

	return 0;
}
