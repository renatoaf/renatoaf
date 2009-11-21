#include <stdio.h>

int blocos[10001][10001];

int main() {
	int A, C, i, j, Xi, cont, maior;
	while (1) {
		scanf("%d%d", &A, &C);

		if (A == 0 && C == 0) {
			break;
		}

		cont = 0;
		maior = A;

		for (i = 0; i < C; i++) {
			scanf("%d", &Xi);
			if (Xi < maior) {
				cont += (maior - Xi);
				maior = Xi;
			} else {
				maior = Xi;
			}
		}

		printf("%d\n", cont);
	}

	return 0;
}
