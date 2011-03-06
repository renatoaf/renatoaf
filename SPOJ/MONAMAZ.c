#include <stdio.h>

#define MAX 1000
#define INF 100

int pontos[MAX][2], dist[MAX][MAX], adj[MAX][2], visitado[MAX], nVisitados;

int distancia(int i, int j) { // sem sqrt pra melhorar desempenho
	int first, second;
	first = pontos[i][0] - pontos[j][0];
	second = pontos[i][1] - pontos[j][1];
	return (first * first) + (second * second);
}

void dfs(int fonte) {
	visitado[fonte] = 1;
	nVisitados++;
	if (!visitado[adj[fonte][0]])
		dfs(adj[fonte][0]);
	if (!visitado[adj[fonte][1]])
		dfs(adj[fonte][1]);
}

int desempata(int p1, int p2) { // true se p1 ganha de p2
	if (pontos[p1][0] == pontos[p2][0])
		return pontos[p1][1] < pontos[p2][1];
	return pontos[p1][0] < pontos[p2][0];
}

int main() {
	int N, i, j, maisProximo, segundoProximo;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;

		for (i = 0; i < N; i++) {
			scanf("%d %d", &pontos[i][0], &pontos[i][1]);
			visitado[i] = 0, adj[i][0] = adj[i][1] = -1;
		}

		for (i = 0; i < N; i++)
			for (j = 0; j <= i; j++)
				dist[i][j] = dist[j][i] = distancia(i, j);

		maisProximo = INF, segundoProximo = INF;
		for (i = 0; i < N; i++)
			for (j = 0; j < N; j++)
				if (i != j) {
					if (dist[i][j] < maisProximo) {
						segundoProximo = maisProximo, adj[i][1] = adj[i][0], maisProximo
								= dist[i][j], adj[i][0] = j;
					} else if (dist[i][j] == maisProximo) {
						if (adj[i][0] == -1)
							maisProximo = dist[i][j], adj[i][0] = j;
						else if (desempata(adj[i][0], j))
							segundoProximo = dist[i][j], adj[i][1] = j;
						else
							segundoProximo = maisProximo, adj[i][1] = adj[i][0], maisProximo
									= dist[i][j], adj[i][0] = j;
					} else if (dist[i][j] < segundoProximo) {
						segundoProximo = dist[i][j], adj[i][1] = j;
					} else if (dist[i][j] == segundoProximo) {
						if (adj[i][1] == -1 || desempata(j, adj[i][1]))
							segundoProximo = dist[i][j], adj[i][1] = j;
					}
				}

		nVisitados = 0;
		dfs(0);

		if (N > 3 && nVisitados != N)
			puts("There are stations that are unreachable.");
		else
			puts("All stations are reachable.");
	}

	return 0;
}
