#include <stdio.h>
#include <math.h>

#define MAX 100

int numeros[MAX], bolinhas[MAX];

int abs(int n) {
	return n < 0 ? -n : n;
}

// B
int main() {
	int N, B;

	while (scanf("%d %d", &N, &B) && N && B) {
		for (int i = 0; i <= N; i++) {
			numeros[i] = 0;
		}

		for (int i = 0; i < B; i++) {
			scanf("%d", &bolinhas[i]);
		}

		for (int i = 0; i < B; i++) {
			for (int j = 0; j < B; j++) {
				numeros[abs(bolinhas[i] - bolinhas[j])] = 1;
			}
		}

		int possivel = 1;
		for (int i = 0; i <= N; i++) {
			if (!numeros[i]) {
				possivel = 0;
				break;
			}
		}

		puts(possivel ? "Y" : "N");
	}
}
