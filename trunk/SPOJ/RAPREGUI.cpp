#include <stdio.h>
#include <queue>
#include <string.h>
#include <limits.h>

using namespace std;

#define INF (INT_MAX/2)
#define MAX 1005
#define INTERVALO_MAX 8

struct Estado {
	int x, y, w;

	Estado(int x, int y, int w) :
		x(x), y(y), w(w) {
	}
};

int calorias[5][5] = { { 7, 6, 5, 6, 7 }, { 6, 3, 2, 3, 6 },
		{ 5, 2, -1, 2, 5 }, { 6, 3, 2, 3, 6 }, { 7, 6, 5, 6, 7 } },
		alagado[MAX][MAX], dist[MAX][MAX], R, C, filaAtual;
queue<Estado> filas[INTERVALO_MAX];

int podeVisitar(int x, int y) {
	return (x >= 1 && y >= 1 && x <= R && y <= C && !alagado[x][y]);
}

void clear() {
	for (int i = 0; i < INTERVALO_MAX; i++)
		filas[i] = queue<Estado> ();
}

int empty() {
	for (int i = 0; i < INTERVALO_MAX; i++)
		if (!filas[i].empty())
			return 0;
	return 1;
}

void push(Estado e) {
	filas[e.w % INTERVALO_MAX].push(e);
}

Estado pop() {
	while (filas[filaAtual].empty())
		filaAtual = (filaAtual + 1) % INTERVALO_MAX;
	Estado e = filas[filaAtual].front();
	filas[filaAtual].pop();
	return e;
}

int dijkstra(int xIni, int yIni, int xFin, int yFin) {
	clear();
	dist[xIni][yIni] = 0;
	push(Estado(xIni, yIni, 0));

	while (!empty()) {
		Estado atual = pop();
		int x = atual.x, y = atual.y, w = atual.w;

		if (x == xFin && y == yFin)
			break;
		else if (dist[x][y] < w)
			continue;

		for (int i = -2; i <= 2; i++) // para cada adjacente
			for (int j = -2; j <= 2; j++) {
				int dx = x + i, dy = y + j, dxy = calorias[i + 2][j + 2];
				if (podeVisitar(dx, dy) && dxy != -1)
					if (dist[dx][dy] > w + dxy) {
						dist[dx][dy] = w + dxy;
						push(Estado(dx, dy, dist[dx][dy]));
					}
			}
	}

	return dist[xFin][yFin];
}

int main() {
	int W, xIni, yIni, xFin, yFin, x1, x2, y1, y2;

	while (1) {
		scanf("%d %d", &R, &C);

		if (R == 0 && C == 0)
			break;

		for (int i = 1; i <= R; i++)
			for (int j = 1; j <= C; j++)
				alagado[i][j] = 0, dist[i][j] = INF;

		scanf("%d %d %d %d %d", &xIni, &yIni, &xFin, &yFin, &W);
		for (int i = 0; i < W; i++) {
			scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
			for (int i = x1; i <= x2; i++)
				for (int j = y1; j <= y2; j++)
					alagado[i][j] = 1;
		}

		int min = dijkstra(xIni, yIni, xFin, yFin);
		if (min >= INF)
			printf("impossible\n");
		else
			printf("%d\n", min);
	}

	return 0;
}
