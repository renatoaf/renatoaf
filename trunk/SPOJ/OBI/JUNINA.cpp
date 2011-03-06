#include <stdio.h>

#define MAX 20

int N, naParticao[MAX], gosta[MAX][MAX], melhor;

// checa se todo mundo que esta na particao atual gosta dele
int podeDescer(int proximo, int qtidadeNaParticao) {
	for (int i = 0; i < qtidadeNaParticao; i++)
		if (!gosta[naParticao[i]][proximo])
			return 0;
	return 1;
}

// u = vertice de partida, k = nivel na arvore (qtidade de elementos na particao)
void backtracking(int u, int k) { // obtem a melhor quantidade partindo de u
	naParticao[k++] = u;
	if (k > melhor)
		melhor = k;
	for (int candidato = u + 1; candidato < N; candidato++)
		if (podeDescer(candidato, k))
			backtracking(candidato, k);
}

int obtemMaiorParticao() {
	int melhorSolucaoEmN = 0;
	for (int i = 0; i < N; i++) {
		melhor = 0;
		backtracking(i, 0);
		if (melhor > melhorSolucaoEmN)
			melhorSolucaoEmN = melhor;
		if (melhorSolucaoEmN == N)
			break;
	}
	return melhorSolucaoEmN;
}

int main() {
	int v, c = 1;

	while (scanf("%d", &N) && N > 0) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				gosta[i][j] = 1;

		for (int u = 0; u < N; u++)
			while (scanf("%d", &v) && v > 0)
				gosta[u][v - 1] = gosta[v - 1][u] = 0;

		printf("Teste %d\n%d\n\n", c++, obtemMaiorParticao());
	}

	return 0;
}
