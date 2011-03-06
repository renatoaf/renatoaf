#include <stdio.h>
#include <limits.h>
#include <queue>

using namespace std;

const int MAX = 100; // maximo de tipos de moedas
const int MAIOR_VALOR = 50000; // maior valor possivel a pesquisar troco
const int INF = (INT_MAX / 2);

int moedas[MAX + 1], numMoedas[MAIOR_VALOR + 1];

int minimoMoedasBFS(int valor, int n) {
	numMoedas[0] = 0; // 0 moedas necessarias para dar troco 0 :)
	for (int i = 1; i <= valor; i++)
		numMoedas[i] = INF;
	queue<int> q;
	q.push(0);

	while (!q.empty()) {
		int atual = q.front();
		q.pop();

		if (atual == valor)
			break;

		int novoNumeroMoedas = numMoedas[atual] + 1;
		for (int i = 0; i < n; i++) {
			int novoValor = atual + moedas[i];
			if (novoValor <= valor && novoNumeroMoedas < numMoedas[novoValor]) {
				numMoedas[novoValor] = novoNumeroMoedas;
				q.push(novoValor);
			}
		}
	}

	return numMoedas[valor];
}

int main() {
	int M, N;

	while (1) {
		scanf("%d", &M);

		if (M == 0)
			break;

		scanf("%d", &N);

		for (int i = 0; i < N; i++)
			scanf("%d", &moedas[i]);

		int min = minimoMoedasBFS(M, N);
		if (min == INF)
			printf("Impossivel\n");
		else
			printf("%d\n", min);
	}

	return 0;
}
