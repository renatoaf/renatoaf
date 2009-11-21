#include <stdio.h>

int pilhas[1001];

int main() {
	int N, P, Q, caixa, pilha, posNaPilha, i, j;

	while (1) {
		scanf("%d %d", &N, &P);

		if (N == 0 && P == 0) {
			break;
		}

		pilha = -1;
		posNaPilha = -1;

		for (i = 0; i < P; i++) {
			scanf("%d", &Q);
			pilhas[i] = Q;

			for (j = 0; j < Q; j++) {
				scanf("%d", &caixa);
				if (caixa == 1) {
					pilha = i;
					posNaPilha = j;
				}
			}
		}

		int countTotal = (pilhas[pilha] - posNaPilha - 1);
		int countDireita = 0;
		int countEsquerda = 0;

		posNaPilha++;

		i = pilha + 1;
		while (i < P && pilhas[i] >= posNaPilha) { // tem adj
			countDireita += (pilhas[i] - posNaPilha + 1);
			i++;
		}
		i = pilha - 1;
		while (i >= 0 && pilhas[i] >= posNaPilha) { // tem adj
			countEsquerda += (pilhas[i] - posNaPilha + 1);
			i--;
		}

		if (countDireita < countEsquerda) {
			printf("%d\n", countTotal + countDireita);
		} else {
			printf("%d\n", countTotal + countEsquerda);
		}
	}

	return 0;
}
