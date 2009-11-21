/*
 * Software Allocation
 */

#include <stdio.h>
#include <string.h>

#define FONTE 0
#define SORVEDOURO 1
#define NN 40

/* questao */
char descricao[25], alocacao[10];
int emparelhamentoNecessario;

/* fluxo */
int cap[NN][NN], fnet[NN][NN];
int q[NN], qf, qb, prev[NN];

int min(int a, int b) {
	return a < b ? a : b;
}

int fordFulkerson(int n, int s, int t) {
	int flow = 0;
	while (true) {
		memset(prev, -1, sizeof(prev));
		qf = qb = 0;
		prev[q[qb++] = s] = -2;
		while (qb > qf && prev[t] == -1)
			for (int u = q[qf++], v = 0; v < n; v++)
				if (prev[v] == -1 && fnet[u][v] - fnet[v][u] < cap[u][v])
					prev[q[qb++] = v] = u;
		if (prev[t] == -1)
			break;
		int bot = 0x7FFFFFFF;
		for (int v = t, u = prev[v]; u >= 0; v = u, u = prev[v])
			bot = min(bot, cap[u][v] - fnet[u][v] + fnet[v][u]);
		for (int v = t, u = prev[v]; u >= 0; v = u, u = prev[v])
			fnet[u][v] += bot;
		flow += bot;
	}
	return flow;
}

int pc(int i) {
	return SORVEDOURO + 1 + i;
}

int aplicacao(char c) {
	return pc(9) + 1 + (c - 'A');
}

void liga(int u, int v, int capacidade) {
	cap[u][v] = capacidade;
}

void reseta() {
	emparelhamentoNecessario = 0;
	memset(cap, 0, sizeof(cap));
	memset(fnet, 0, sizeof(fnet));
	memset(alocacao, '_', sizeof(alocacao));
}

void montaArestas() {
	char app = descricao[0];
	int numUsuarios = descricao[1] - '0';

	for (int i = 3; descricao[i] != ';'; i++) {
		int comp = descricao[i] - '0';
		liga(aplicacao(app), pc(comp), 1);
	}

	emparelhamentoNecessario += numUsuarios;
	liga(FONTE, aplicacao(app), numUsuarios);
}

int main() {
	int N = aplicacao('Z') + 1;

	while (gets(descricao)) {
		reseta();
		for (int i = 0; i <= 9; i++) {
			liga(pc(i), SORVEDOURO, 1);
		}

		do {
			montaArestas();
		} while (gets(descricao) && descricao[0] >= 'A' && descricao[0] <= 'Z');

		int emparelhamento = fordFulkerson(N, FONTE, SORVEDOURO);
		if (emparelhamento < emparelhamentoNecessario) {
			printf("!\n");
		} else {
			for (char app = 'A'; app <= 'Z'; app++) {
				for (int comp = 0; comp <= 9; comp++) {
					if (fnet[aplicacao(app)][pc(comp)]
							&& !fnet[pc(comp)][aplicacao(app)]) {
						alocacao[comp] = app;
					}
				}
			}
			printf("%s\n", alocacao);
		}
	}

	return 0;
}
