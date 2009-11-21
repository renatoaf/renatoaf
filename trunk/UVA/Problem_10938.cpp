/*
 * Flea Circus
 */

#include <stdio.h>
#include <vector>

#define MAX 5001

using namespace std;

int min(int a, int b) {
	return a < b ? a : b;
}

int max(int a, int b) {
	return a > b ? a : b;
}

vector<int> adj[MAX], caminho;
int pai[MAX], visitado[MAX];

bool dfs(int origem, int destino) {
	visitado[origem] = 1;
	if (origem == destino)
		return true;
	int grau = adj[origem].size();
	for (int i = 0; i < grau; i++) {
		int next = adj[origem][i];
		if (!visitado[next]) {
			pai[next] = origem;
			if (dfs(next, destino))
				return true;
		}
	}
	return false;
}

void achaCaminho(int a, int b, int n) {
	for (int i = 0; i <= n; i++) {
		pai[i] = -1, visitado[i] = 0;
	}
	dfs(a, b);
	int fim = b;
	while (fim != -1) {
		caminho.push_back(fim);
		fim = pai[fim];
	}
}

int main() {
	int N, Q, a, b;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;

		for (int i = 0; i < N - 1; i++) {
			scanf("%d %d", &a, &b);
			adj[a].push_back(b);
			adj[b].push_back(a);
		}

		scanf("%d", &Q);
		for (int i = 0; i < Q; i++) {
			scanf("%d %d", &a, &b);

			achaCaminho(a, b, N);
			int comprimento = caminho.size();
			if (comprimento % 2 == 0) {
				int n1 = caminho[comprimento / 2];
				int n2 = caminho[(comprimento / 2) - 1];
				printf("The fleas jump forever between %d and %d.\n", min(n1,
						n2), max(n1, n2));
			} else {
				printf("The fleas meet at %d.\n", caminho[comprimento / 2]);
			}

			caminho.clear();
		}

		for (int i = 0; i <= N; i++)
			adj[i].clear();
	}

	return 0;
}
