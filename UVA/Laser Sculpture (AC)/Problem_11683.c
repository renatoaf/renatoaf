#include <stdio.h>

int main() {
	int A, C, i, Xi, cont;
	while (1) {
		scanf("%d", &A);
		if (A == 0) {
			break;
		}
		scanf("%d", &C);
		cont = 0;
		for (i = 0; i < C; i++) {
			scanf("%d", &Xi);
			if (Xi < A) {
				cont += (A - Xi);
			}
			A = Xi;
		}
		printf("%d\n", cont);
	}
	return 0;
}
