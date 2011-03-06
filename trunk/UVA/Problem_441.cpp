/*
 * Lotto
 */

#include <stdio.h>

#define MAXK 13
#define TAM_SUBCONJUNTO 6

int k, conjunto[MAXK], solucao[TAM_SUBCONJUNTO];

void imprime() {
	for (int i = 0; i < TAM_SUBCONJUNTO; i++) {
		printf("%d", solucao[i]);
		if (i != TAM_SUBCONJUNTO - 1)
			printf(" ");
	}
	printf("\n");
}

void backtracking(int nivel, int numElementosNivel, bool output) {
	if (numElementosNivel == TAM_SUBCONJUNTO && output) {
		imprime();
		return;
	} else if (nivel == k)
		return;
	solucao[numElementosNivel] = conjunto[nivel];
	backtracking(nivel + 1, numElementosNivel + 1, true);
	backtracking(nivel + 1, numElementosNivel, false);
}

int main() {
	bool first = true;
	while (scanf("%d", &k) && k > 0) {
		if (!first)
			puts("");
		first = false;

		for (int i = 0; i < k; i++)
			scanf("%d", &conjunto[i]);
		backtracking(0, 0, false);
	}
}
