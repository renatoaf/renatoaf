#include <stdio.h>
#include <string.h>
#include <queue>

#define MAX 50
#define BURACO 'B'
#define PAREDE '#'

using namespace std;

int linhas, colunas;
int visitado[MAX][MAX][MAX][MAX];
int adj[4][4] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
char grid1[MAX][MAX], grid2[MAX][MAX];

struct Estado {
	int x1, y1, x2, y2, mov;

	Estado();
	Estado(int i1, int j1, int i2, int j2, int m) {
		x1 = i1;
		y1 = j1;
		x2 = i2;
		y2 = j2;
		mov = m;
	}

	int equal(Estado outro) {
		return x1 == outro.x1 && x2 == outro.x2 && y1 == outro.y1 && y2
				== outro.y2;
	}
};

int valido(Estado e) {
	return e.x1 < linhas && e.y1 < colunas && e.x1 >= 0 && e.y1 >= 0 && e.x2
			< linhas && e.y2 < colunas && e.x2 >= 0 && e.y2 >= 0
			&& !visitado[e.x1][e.y1][e.x2][e.y2] && grid1[e.x1][e.y1] != BURACO
			&& grid2[e.x2][e.y2] != BURACO && e.mov <= MAX;
}

int bfs(Estado inicial, Estado final) {
	queue<Estado> q;
	q.push(inicial);
	visitado[inicial.x1][inicial.y1][inicial.x2][inicial.y2] = 1;

	while (!q.empty()) {
		Estado atual = q.front();
		q.pop();

		for (int i = 0; i < 4; i++) {
			int novox1 = atual.x1 + adj[i][0];
			int novox2 = atual.x2 + adj[i][0];
			int novoy1 = atual.y1 + adj[i][1];
			int novoy2 = atual.y2 + adj[i][1];

			Estado novo = Estado(novox1, novoy1, novox2, novoy2, atual.mov + 1);
			if (grid1[novo.x1][novo.y1] == PAREDE) { // deslocamento pra parede, n mexe
				novo.x1 = atual.x1;
				novo.y1 = atual.y1;
			}
			if (grid2[novo.x2][novo.y2] == PAREDE) { // deslocamento pra parede, n mexe
				novo.x2 = atual.x2;
				novo.y2 = atual.y2;
			}

			if (!valido(novo)) {
				continue;
			} else if (novo.equal(final)) {
				return novo.mov;
			}
			visitado[novo.x1][novo.y1][novo.x2][novo.y2] = 1;
			q.push(novo);
		}
	}

	return -1;
}

int main() {
	int nCasos, c, i, j, min;

	scanf("%d", &nCasos);

	for (c = 1; c <= nCasos; c++) {
		scanf("%d %d ", &linhas, &colunas);

		int x1, y1, x2, y2, xf1, yf1, xf2, yf2;

		for (i = 0; i < linhas; i++) {
			gets(grid1[i]);
			for (j = 0; j < colunas; j++) {
				if (grid1[i][j] == 'R') {
					x1 = i;
					y1 = j;
				} else if (grid1[i][j] == 'F') {
					xf1 = i;
					yf1 = j;
				}
			}
		}
		for (i = 0; i < linhas; i++) {
			gets(grid2[i]);
			for (j = 0; j < colunas; j++) {
				if (grid2[i][j] == 'R') {
					x2 = i;
					y2 = j;
				} else if (grid2[i][j] == 'F') {
					xf2 = i;
					yf2 = j;
				}
			}
		}

		memset(visitado, 0, sizeof(visitado));
		min = bfs(Estado(x1, y1, x2, y2, 0), Estado(xf1, yf1, xf2, yf2, 0));

		if (min == -1) {
			printf("impossivel\n");
		} else {
			printf("%d\n", min);
		}
	}

	return 0;
}
