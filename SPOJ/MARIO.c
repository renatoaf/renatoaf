#include <stdio.h>

int livres[100001];
int max = 1000000;

int main() {
	int N, L, i, j, dif, min, qt, espVagos;

	while (1) {
		scanf("%d %d", &N, &L);
		if (N == 0 && L == 0) {
			break;
		}

		for (i = 0; i < L; i++) {
			scanf("%d", &livres[i]);
		}

		min = max;
		for (i = 0; i < L; i++) {
			if (min == 0) {
				break;
			}

			espVagos = 0;
			qt = 1;
			j = i;

			while ((j + 1) < L && qt < N) {
				dif = (livres[j + 1] - livres[j]);

				if (qt + dif > N) {
					espVagos += (N - qt);
					qt = N;
					break;
				}

				espVagos += dif - 1;
				qt += dif;
				j++;
			}

			if (qt >= N) {
				if (espVagos < min) {
					min = espVagos;
				}
			}
		}

		printf("%d\n", min);
	}

	return 0;
}
