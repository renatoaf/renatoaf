#include <queue>
#include <stdio.h>
#include <string>

static const int MAX = 21;
static const char BLOQ = '#';

using namespace std;

struct No {
	int xJog, yJog, xCaixa, yCaixa, movimentos, empurroes;

	No(int iJog, int jJog, int xCai, int yCai, int m, int e) {
		xJog = iJog;
		yJog = jJog;
		xCaixa = xCai;
		yCaixa = yCai;
		movimentos = m;
		empurroes = e;
	}
};

char grid[MAX][MAX];
bool visitado[MAX][MAX][MAX][MAX];
int linhas, colunas, caixaFinalX, caixaFinalY, jogadorIniX, jogadorIniY,
		caixaIniX, caixaIniY;

bool configValida(No no) {
	return (no.xCaixa >= 0 && no.yCaixa >= 0 && no.xJog >= 0 && no.yJog >= 0
			&& no.xCaixa < linhas && no.xJog < linhas && no.yCaixa < colunas
			&& no.yJog < colunas && grid[no.xJog][no.yJog] != BLOQ
			&& grid[no.xCaixa][no.yCaixa] != BLOQ
			&& !visitado[no.xCaixa][no.yCaixa][no.xJog][no.yJog]);
}

bool configFinal(No no) {
	return no.xCaixa == caixaFinalX && no.yCaixa == caixaFinalY;
}

No bfs() {
	No inv = No(0, 0, 0, 0, -1, -1);
	queue<No> filaMesmaQtd;
	queue<No> filaMaior;

	filaMesmaQtd.push(No(jogadorIniX, jogadorIniY, caixaIniX, caixaIniY, 0, 0));

	while (!filaMesmaQtd.empty() || !filaMaior.empty()) {
		No atual = inv;
		if (filaMesmaQtd.empty()) {
			atual = filaMaior.front();
			filaMaior.pop();
		} else {
			atual = filaMesmaQtd.front();
			filaMesmaQtd.pop();
		}

		if (!configValida(atual)) {
			continue;
		} else if (configFinal(atual)) {
			return atual;
		}

		visitado[atual.xCaixa][atual.yCaixa][atual.xJog][atual.yJog] = true;

		if (atual.yJog == atual.yCaixa - 1 && atual.xJog == atual.xCaixa) {
			filaMaior.push(
					No(atual.xJog, atual.yJog + 1, atual.xCaixa, atual.yCaixa
							+ 1, atual.movimentos + 1, atual.empurroes + 1));
		} else {
			filaMesmaQtd.push(No(atual.xJog, atual.yJog + 1, atual.xCaixa,
					atual.yCaixa, atual.movimentos + 1, atual.empurroes));
		}

		if (atual.yJog == atual.yCaixa + 1 && atual.xJog == atual.xCaixa) {
			filaMaior.push(
					No(atual.xJog, atual.yJog - 1, atual.xCaixa, atual.yCaixa
							- 1, atual.movimentos + 1, atual.empurroes + 1));
		} else {
			filaMesmaQtd.push(No(atual.xJog, atual.yJog - 1, atual.xCaixa,
					atual.yCaixa, atual.movimentos + 1, atual.empurroes));
		}

		if (atual.xJog == atual.xCaixa - 1 && atual.yJog == atual.yCaixa) {
			filaMaior.push(No(atual.xJog + 1, atual.yJog, atual.xCaixa + 1,
					atual.yCaixa, atual.movimentos + 1, atual.empurroes + 1));
		} else {
			filaMesmaQtd.push(No(atual.xJog + 1, atual.yJog, atual.xCaixa,
					atual.yCaixa, atual.movimentos + 1, atual.empurroes));
		}

		if (atual.xJog == atual.xCaixa + 1 && atual.yJog == atual.yCaixa) {
			filaMaior.push(No(atual.xJog - 1, atual.yJog, atual.xCaixa - 1,
					atual.yCaixa, atual.movimentos + 1, atual.empurroes + 1));
		} else {
			filaMesmaQtd.push(No(atual.xJog - 1, atual.yJog, atual.xCaixa,
					atual.yCaixa, atual.movimentos + 1, atual.empurroes));
		}
	}

	return inv;
}

int main() {
	int i, j, caso = 1;

	while (true) {
		scanf("%d %d ", &linhas, &colunas);

		if (linhas == 0 && colunas == 0) {
			break;
		}

		if (caso > 1)
			memset(visitado, false, sizeof(visitado));

		for (i = 0; i < linhas; i++) {
			for (j = 0; j < colunas; j++) {
				grid[i][j] = getchar();
				if (grid[i][j] == 'T') {
					caixaFinalX = i;
					caixaFinalY = j;
				} else if (grid[i][j] == 'B') {
					caixaIniX = i;
					caixaIniY = j;
				} else if (grid[i][j] == 'S') {
					jogadorIniX = i;
					jogadorIniY = j;
				}
			}
			getchar(); // '\n'
		}

		No res = bfs();
		printf("Instancia %d\n", caso++);
		if (res.empurroes == -1 && res.movimentos == -1) {
			printf("Impossivel\n");
		} else {
			printf("%d %d\n", res.movimentos, res.empurroes);
		}
		printf("\n");
	}

	return 0;
}
