#include <stdio.h>
#include <vector>

using namespace std;

#define MAXN 6001

vector<int> adj[MAXN];
int base[MAXN], visitado[MAXN], bases;

void dfs(int vert) {
	visitado[vert] = 1;
	int grau = adj[vert].size();
	int filhosMarcados = 0;
	for (int i = 0; i < grau; i++) {
		int prox = adj[vert][i];
		if (!visitado[prox]) {
			dfs(prox);
			if (base[prox]) {
				filhosMarcados++;
			}
		}
	}
	if (filhosMarcados == 0) {
		base[vert] = 1;
		bases++;
	}
}

int main() {
	int casos, c, i, n, u, v;

	scanf("%d", &casos);

	for (c = 1; c <= casos; c++) {
		scanf("%d", &n);

		for (i = 0; i < n; i++) {
			adj[i].clear();
			visitado[i] = 0;
			base[i] = 0;
		}

		for (i = 0; i < n - 1; i++) {
			scanf("%d %d", &u, &v);
			u--;
			v--;
			adj[u].push_back(v);
			adj[v].push_back(u);
		}

		bases = 0;
		dfs(0);
		printf("%d\n", bases);
	}

	return 0;
}
