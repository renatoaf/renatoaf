#include <stdio.h>

int temK[10000];

int main() {
	int colunas, linhas, K, i, j, lido;
	while (1) {
		scanf("%d %d %d", &colunas, &linhas, &K);

		if (colunas == 0 && linhas == 0 && K == 0) {
			break;
		}

		for (j = 0; j < colunas; j++) {
			temK[j] = 0;
		}

		int qt = 0;

		for (i = 0; i < linhas; i++) {
			for (j = 0; j < colunas; j++) {
				scanf("%d", &lido);
				if (lido == 1) {
					temK[j]++;
				} else {
					if (temK[j] >= K) {
						qt++;
					}
					temK[j] = 0;
				}
			}
		}

		for (j = 0; j < colunas; j++) {
			if (temK[j] >= K)
				qt++;
		}

		printf("%d\n", qt);
	}

	return 0;
}
