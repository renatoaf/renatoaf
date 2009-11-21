#include <stdio.h>

int pontos[65537][2];

int pertenceAReta(int a, int b, int x, int y) {
	return y == ((a * x) + b);
}

int ladoPosDaReta(int a, int b, int x, int y) {
	return y > ((a * x) + b);
}

int ladoNegDaReta(int a, int b, int x, int y) {
	return y < ((a * x) + b);
}

void getPontosEspiral(int a, int b) {
	int i = 0, x = 0, y = 0, max = 65536;
	int quadrado = 1;

	while (i < max) {
		while (x > -quadrado && i < max) {
			if (!pertenceAReta(a, b, x, y)) {
				pontos[i][0] = x;
				pontos[i][1] = y;
				i++;
			}
			x--;
		}
		while (y < quadrado && i < max) {
			if (!pertenceAReta(a, b, x, y)) {
				pontos[i][0] = x;
				pontos[i][1] = y;
				i++;
			}
			y++;
		}
		while (x < quadrado && i < max) {
			if (!pertenceAReta(a, b, x, y)) {
				pontos[i][0] = x;
				pontos[i][1] = y;
				i++;
			}
			x++;
		}
		while (y > -quadrado && i < max) {
			if (!pertenceAReta(a, b, x, y)) {
				pontos[i][0] = x;
				pontos[i][1] = y;
				i++;
			}
			y--;
		}
		quadrado++;
	}
}

int main() {
	int casos, c, a, b, K, i, M, N, xM, yM, xN, yN;

	scanf("%d", &casos);

	for (c = 1; c <= casos; c++) {
		scanf("%d %d", &a, &b);

		getPontosEspiral(a, b);

		scanf("%d", &K);

		printf("Caso %d\n", c);
		for (i = 0; i < K; i++) {
			scanf("%d %d", &M, &N);
			xM = pontos[M][0];
			yM = pontos[M][1];
			xN = pontos[N][0];
			yN = pontos[N][1];
			if ((ladoPosDaReta(a, b, xM, yM) && ladoPosDaReta(a, b, xN, yN))
					|| (ladoNegDaReta(a, b, xM, yM) && ladoNegDaReta(a, b, xN,
							yN))) {
				printf("Mesmo lado da fronteira\n");
			} else {
				printf("Lados opostos da fronteira\n");
			}
		}
	}

	return 0;
}
