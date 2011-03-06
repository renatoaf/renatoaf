/*
 * Lazy Jumping Frog
 */

#include <stdio.h>
#include <queue>
#include <limits.h>

using namespace std;

#define INF (INT_MAX/2)
#define MAX 1005

int calorias[5][5] = { { 7, 6, 5, 6, 7 }, { 6, 3, 2, 3, 6 },
		{ 5, 2, -1, 2, 5 }, { 6, 3, 2, 3, 6 }, { 7, 6, 5, 6, 7 } },
		alagado[MAX][MAX], dist[MAX][MAX], visitado[MAX][MAX], R, C;

struct Estado {
	int x, y, w;

	Estado(int x, int y, int w) :
		x(x), y(y), w(w) {
	}

	bool operator <(const Estado &outro) const {
		return w > outro.w;
	}
};

int abs(int x) {
	return x < 0 ? -x : x;
}

int podeVisitar(int x, int y) {
	return (x >= 1 && y >= 1 && x <= R && y <= C && !alagado[x][y]);
}

int manhattan(int x1, int y1, int x2, int y2) {
	return abs(x1 - x2) + abs(y1 - y2);
}

int aEstrela(int xIni, int yIni, int xFin, int yFin) {
	priority_queue<Estado> q;
	q.push(Estado(xIni, yIni, 0));
	dist[xIni][yIni] = 0;
	while (!q.empty()) {
		int x = q.top().x, y = q.top().y;
		q.pop();

		if (x == xFin && y == yFin)
			break;
		else if (visitado[x][y])
			continue;

		visitado[x][y] = 1;
		for (int i = -2; i <= 2; i++) // para cada adjacente
			for (int j = -2; j <= 2; j++) {
				int dx = x + i, dy = y + j, dxy = calorias[i + 2][j + 2];
				if (podeVisitar(dx, dy) && dxy != -1)
					if (dist[dx][dy] > dist[x][y] + dxy) {
						dist[dx][dy] = dist[x][y] + dxy;
						q.push(Estado(dx, dy, dist[dx][dy] + manhattan(dx, dy,
								xFin, yFin)));
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
				alagado[i][j] = 0, dist[i][j] = INF, visitado[i][j] = 0;

		scanf("%d %d %d %d %d", &xIni, &yIni, &xFin, &yFin, &W);
		for (int i = 0; i < W; i++) {
			scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
			for (int i = x1; i <= x2; i++)
				for (int j = y1; j <= y2; j++)
					alagado[i][j] = 1;
		}

		int min = aEstrela(xIni, yIni, xFin, yFin);
		if (min >= INF)
			printf("impossible\n");
		else
			printf("%d\n", min);
	}

	return 0;
}
