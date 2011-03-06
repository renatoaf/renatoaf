/*
 * The House of Santa Claus
 */

#include <stdio.h>

#define NUM_ARESTAS 8
#define NUM_VERTICES 5

int euleriano[NUM_ARESTAS + 1], visitou[NUM_VERTICES][NUM_VERTICES];
int aresta_possivel[NUM_VERTICES][NUM_VERTICES] = // matriz
		{ { 0, 1, 1, 0, 1 }, // 1 se for adjacente a 1
				{ 1, 0, 1, 0, 1 }, // 1 se for adjacente a 2
				{ 1, 1, 0, 1, 1 }, // 1 se for adjacente a 3
				{ 0, 0, 1, 0, 1 }, // 1 se for adjacente a 4
				{ 1, 1, 1, 1, 0 } // 1 se for adjacente a 5
		};

void imprime_solucao() {
	for (int i = 0; i <= NUM_ARESTAS; i++)
		printf("%d", euleriano[i] + 1);
	printf("\n");
}

void backtracking(int u, int k) { // vertice u, nivel k
	euleriano[k] = u;

	if (k == NUM_ARESTAS)
		imprime_solucao();
	else
		for (int v = 0; v < NUM_VERTICES; v++)
			if (aresta_possivel[u][v])
				if (!visitou[u][v] && !visitou[v][u]) {
					visitou[u][v] = 1;
					backtracking(v, k + 1);
					visitou[u][v] = 0;
				}
}

int main() {
	backtracking(0, 0);
	return 0;
}