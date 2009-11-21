#include <stdio.h>

#define MAXN 10000

struct Call {
	int inicio, fim;

	Call() {
	}

	Call(int start, int duration) {
		inicio = start;
		fim = start + duration - 1;
	}
};

Call ligacoes[MAXN];

int main() {
	int N, M, x, y, inicio, duracao, i, j, contagem;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0)
			break;

		for (i = 0; i < N; i++) {
			scanf("%d %d %d %d", &x, &y, &inicio, &duracao);
			ligacoes[i] = Call(inicio, duracao);
		}

		for (i = 0; i < M; i++) {
			scanf("%d %d", &x, &y);

			contagem = 0;
			for (j = 0; j < N; j++) {
				int ini = x;
				int fim = x + y - 1;
				if ((ini >= ligacoes[j].inicio && ini <= ligacoes[j].fim)
						|| (fim >= ligacoes[j].inicio && fim <= ligacoes[j].fim)) {
					contagem++;
				} else if ((ligacoes[j].inicio >= ini && ligacoes[j].inicio
						<= fim) || (ligacoes[j].fim >= ini && ligacoes[j].fim
						<= fim)) {
					contagem++;
				}
			}
			printf("%d\n", contagem);
		}
	}

	return 0;
}
