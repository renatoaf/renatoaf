#include <stdio.h>

#define MAX 502

int retangulo[MAX][MAX];

int min(int x, int y) {
	return x < y ? x : y;
}

int max(int x, int y) {
	return x > y ? x : y;
}

int main() {
	int W, H, N, X1, Y1, X2, Y2, minX, maxX, minY, maxY, i, j, k, contador;

	while (1) {
		scanf(" %d %d %d", &W, &H, &N);

		if (W == 0 && H == 0 && N == 0)
			break;

		for (k = 0; k < N; k++) {
			scanf("%d %d %d %d", &X1, &Y1, &X2, &Y2);
			minX = min(X1, X2), maxX = max(X1, X2), minY = min(Y1, Y2), maxY
					= max(Y1, Y2);

			for (i = minX; i <= maxX; i++)
				for (j = minY; j <= maxY; j++)
					retangulo[i][j] = 1;
		}

		contador = 0;
		for (i = 1; i <= W; i++)
			for (j = 1; j <= H; j++)
				if (!retangulo[i][j])
					contador++;

		if (contador == 0)
			printf("There is no empty spots.\n");
		else if (contador == 1)
			printf("There is one empty spot.\n");
		else
			printf("There are %d empty spots.\n", contador);

		for (i = 1; i <= W; i++)
			for (j = 1; j <= H; j++)
				retangulo[i][j] = 0;
	}

	return 0;
}
