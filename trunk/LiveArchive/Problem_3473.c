#include <stdio.h>

int inversaoI[1001];
int inversaoJ[1001];

int main() {
	int c = 1, n, i, j, posIni, x, y, r, q;

	while (1) {
		scanf("%d", &n);

		if (n == 0) {
			break;
		}

		scanf("%d", &r);

		for (i = 1; i <= r; i++) {
			scanf("%d %d", &x, &y);
			inversaoI[i] = x;
			inversaoJ[i] = y;
		}

		printf("Genome %d\n", c++);
		scanf("%d", &q);

		for (i = 1; i <= q; i++) {
			scanf("%d", &posIni);

			for (j = 1; j <= r; j++) {
				if ((inversaoI[j]) <= posIni && (inversaoJ[j] >= posIni)) {
					// inverte a posicao em relacao a i e j
					posIni = inversaoI[j] + (inversaoJ[j] - posIni);
				}
			}

			printf("%d\n", posIni);
		}
	}

	return 0;
}
