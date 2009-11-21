#include <stdio.h>

int temPonto[10000000];
int arcos[10000];

int main() {
	int N, i, j, ponto, melhorSol, tamLadoSuposto, possivel, k, minimoCortes;

	while (1) {
		scanf("%d", &N);

		if (!N) {
			break;
		}

		int perimetroTotal = 0;
		for (i = 0; i < N; i++) {
			scanf("%d", &arcos[i]);
			perimetroTotal += arcos[i];
		}

		for (i = 0; i < perimetroTotal; i++) {
			temPonto[i] = 0;
		}
		ponto = 0;
		for (i = 0; i < N; i++) {
			temPonto[ponto] = 1;
			ponto += arcos[i];
		}

		melhorSol = -1;

		for (i = N; i >= 3; i--) {
			int acabou = 0;
			if (perimetroTotal % i == 0) {
				tamLadoSuposto = (perimetroTotal / i);
				for (j = 0; j < tamLadoSuposto; j++) {
					possivel = 1;
					k = j;

					while (k < perimetroTotal) {
						if (!temPonto[k]) {
							possivel = 0;
							break;
						}
						k += tamLadoSuposto;
					}

					if (possivel) {
						melhorSol = i;
						acabou = 1;
						break;
					}
				}
			}
			if (acabou) {
				break;
			}
		}

		minimoCortes = (melhorSol == -1) ? -1 : N - melhorSol;
		printf("%d\n", minimoCortes);
	}
	return 0;
}
