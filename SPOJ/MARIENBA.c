#include <stdio.h>

int representacaoBinaria[6][4];

int configSegura(int bin[6][4]) {
	int i, soma;
	for (i = 0; i < 4; i++) {
		soma = bin[0][i] + bin[1][i] + bin[2][i] + bin[3][i] + bin[4][i]
				+ bin[5][i];
		if (soma % 2 != 0) {
			return 0;
		}
	}
	return 1;
}

int main() {
	int casos, c, i, j, numPalitos, maxUmPorFila, somaPalitos,
			jogadaAtualPerfeita;
	scanf("%d", &casos);

	for (c = 1; c <= casos; c++) {
		somaPalitos = 0;
		maxUmPorFila = 1;

		for (i = 0; i < 6; i++) {
			scanf("%d", &numPalitos);
			if (numPalitos > 1) {
				maxUmPorFila = 0;
			}
			somaPalitos += numPalitos;
			for (j = 0; j < 4; j++) {
				representacaoBinaria[i][4 - j - 1] = numPalitos % 2; // em binario
				numPalitos /= 2;
			}
		}

		if (maxUmPorFila && (somaPalitos % 2 != 0)) {
			jogadaAtualPerfeita = 1;
		} else if (maxUmPorFila && (somaPalitos % 2 == 0)) {
			jogadaAtualPerfeita = 0;
		} else {
			jogadaAtualPerfeita = configSegura(representacaoBinaria);
		}

		printf("Instancia %d\n", c);
		if (jogadaAtualPerfeita) {
			printf("nao\n\n");
		} else {
			printf("sim\n\n");
		}
	}

	return 0;
}
