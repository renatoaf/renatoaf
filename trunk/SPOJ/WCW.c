#include <stdio.h>

#define MAXN 10001

int numeros[MAXN]; // numeros[i] = num na posicao i
int posicao[MAXN]; // posicao[i] = posicao do num i

int main() {
	int n, i, trocas, casos, c;

	scanf("%d", &casos);

	for (c = 1; c <= casos; c++) {
		scanf("%d", &n);

		trocas = 0;

		for (i = 1; i <= n; i++) {
			scanf("%d", &numeros[i]);
			posicao[numeros[i]] = i;
		}

		for (i = 1; i <= n; i++) {
			if (numeros[i] != i) {
				numeros[posicao[i]] = numeros[i];
				posicao[numeros[i]] = posicao[i];
				numeros[i] = posicao[i] = i;
				trocas++;
			}
		}

		printf("%d\n", trocas);
	}

	return 0;
}
