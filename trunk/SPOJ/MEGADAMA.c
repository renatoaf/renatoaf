#include <stdio.h>

#define MAX 20

int linhas, colunas;
int grid[MAX][MAX];
int visitado[MAX][MAX];

int max(int a, int b) {
	return a > b ? a : b;
}

int podeVisitar(int i, int j, int iComida, int jComida) {
	return (i < linhas && j < colunas && i >= 0 && j >= 0
			&& !visitado[iComida][jComida] && grid[i][j] == 0
			&& grid[iComida][jComida] == 2);
}

int maximoPecas(int i, int j) {
	int maximo = 0;
	if (podeVisitar(i + 2, j + 2, i + 1, j + 1)) {
		visitado[i + 1][j + 1] = 1;
		maximo = max(1 + maximoPecas(i + 2, j + 2), maximo);
		visitado[i + 1][j + 1] = 0;
	}
	if (podeVisitar(i - 2, j + 2, i - 1, j + 1)) {
		visitado[i - 1][j + 1] = 1;
		maximo = max(1 + maximoPecas(i - 2, j + 2), maximo);
		visitado[i - 1][j + 1] = 0;
	}
	if (podeVisitar(i + 2, j - 2, i + 1, j - 1)) {
		visitado[i + 1][j - 1] = 1;
		maximo = max(1 + maximoPecas(i + 2, j - 2), maximo);
		visitado[i + 1][j - 1] = 0;
	}
	if (podeVisitar(i - 2, j - 2, i - 1, j - 1)) {
		visitado[i - 1][j - 1] = 1;
		maximo = max(1 + maximoPecas(i - 2, j - 2), maximo);
		visitado[i - 1][j - 1] = 0;
	}
	return maximo;
}

int main() {
	int i, j;

	while (1) {
		scanf("%d %d", &linhas, &colunas);

		if (linhas == 0 && colunas == 0) {
			break;
		}

		for (i = 0; i < linhas; i++) {
			for (j = 0; j < colunas; j++) {
				visitado[i][j] = 0;
			}
		}

		for (i = 0; i < linhas; i++) {
			for (j = (i % 2); j < colunas; j += 2) {
				scanf("%d", &grid[i][j]);
			}
		}

		int maximoGlobal = 0;

		for (i = 0; i < linhas; i++) {
			for (j = (i % 2); j < colunas; j += 2) {
				if (grid[i][j] == 1) {
					grid[i][j] = 0;
					maximoGlobal = max(maximoPecas(i, j), maximoGlobal);
					grid[i][j] = 1;
				}
			}
		}

		printf("%d\n", maximoGlobal);
	}

	return 0;
}
