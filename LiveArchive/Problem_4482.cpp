/*
 * Klingon Levels
 */

#include <stdio.h>
#include <vector>
#include <algorithm>

using namespace std;

#define MAX 10000

vector<int> notas[MAX];
int indice[MAX];

int max(int a, int b) {
	return a > b ? a : b;
}

int min(int a, int b) {
	return a < b ? a : b;
}

int abs(int a) {
	return a < 0 ? -a : a;
}

int main() {
	int N, i, j, maxT, qt, nota, numNotas, acum, acumAtual, t, basico, avancado;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;

		maxT = 0;
		for (i = 0; i < N; i++) {
			scanf("%d", &qt);
			notas[i].clear();
			indice[i] = 0;

			for (j = 0; j < qt; j++) {
				scanf("%d", &nota);
				maxT = max(maxT, nota);
				notas[i].push_back(nota);
			}

			sort(notas[i].begin(), notas[i].end());
		}

		acum = MAX * 4;
		for (t = 0; t < maxT; t++) {
			acumAtual = 0;

			for (i = 0; i < N; i++) {
				numNotas = notas[i].size();
				while (indice[i] < numNotas && notas[i][indice[i]] < t)
					indice[i]++;
				basico = indice[i];
				avancado = numNotas - basico;
				acumAtual += abs(basico - avancado);
			}

			acum = min(acum, acumAtual);
			if (acum == 0)
				break;
		}

		printf("%d\n", acum);
	}

	return 0;
}
