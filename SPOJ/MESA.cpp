#include <stdio.h>
#include <vector>

using namespace std;

int col[101];
int grau[101];

bool bicolorivel(int no, int color, vector<int> adj[]) {
	col[no] = (color + 1) % 2; // alterna a cor
	for (int i = 0; i < grau[no]; i++) {
		if (col[adj[no][i]] == -1) {
			if (!bicolorivel(adj[no][i], col[no], adj)) { // tenta colorir adjacente
				return false;
			}
		} else if (col[adj[no][i]] == col[no]) { // adjacente tem a mesma cor
			return false;
		}
	}
	return true;
}

int main() {
	int n, m;
	int c = 1;
	while (scanf("%d %d", &n, &m) == 2) {
		vector<int> adj[n]; // listas de adjacencias

		memset(col, -1, sizeof(col)); // preenche cor com -1
		memset(grau, 0, sizeof(grau)); // prenche grau com 0

		for (int i = 0; i < m; i++) {
			int u, v;
			scanf("%d %d", &u, &v);
			adj[u - 1].push_back(v - 1);
			adj[v - 1].push_back(u - 1);
			grau[u - 1]++;
			grau[v - 1]++;
		}

		printf("Instancia %d\n", c);
		if (bicolorivel(0, 0, adj)) {
			printf("sim\n\n");
		} else {
			printf("nao\n\n");
		}
		c++;
	}

	return 0;
}
