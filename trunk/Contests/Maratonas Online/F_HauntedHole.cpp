/*
 * Nao foi aceito
 */

#include <stdio.h>
#include <queue>
#include <vector>

using namespace std;

#define GRAMA -2
#define RIP -1
#define MAX 30
#define INF 1000000

struct Estado {
	int x, y, sec, teleportado;

	Estado() {
	}

	Estado(int x1, int y1, int segundos, int warp) {
		x = x1;
		y = y1;
		sec = segundos;
		teleportado = warp;
	}

	bool operator <(const Estado &outra) const {
		return sec > outra.sec;
	}
};

struct HauntedHole {
	int xo, yo, xDest, yDest, tempo;

	HauntedHole() {
	}

	HauntedHole(int x0, int y0, int x, int y, int s) {
		xo = x0;
		yo = y0;
		xDest = x;
		yDest = y;
		tempo = s;
	}
};

vector<HauntedHole> haunted;
int grid[MAX][MAX];
int visitado[MAX][MAX][2];
int adj[4][2] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

int podeVisitar(int i, int j, int W, int H, int w) {
	return i >= 0 && j >= 0 && i < W && j < H && !visitado[i][j][w]
			&& grid[i][j] != RIP;
}

int dfs(int xFonte, int yFonte, int xDest, int yDest, int W, int H, int warp) {
	if (xFonte == xDest && yFonte == yDest) {
		return 1;
	}
	visitado[xFonte][yFonte][warp] = 1;
	for (int i = 0; i < 4; i++) {
		int dx = xFonte + adj[i][0];
		int dy = yFonte + adj[i][1];
		if (!podeVisitar(dx, dy, W, H, warp)) {
			continue;
		}
		if (dx == xDest && dy == yDest) {
			return 1;
		}
		if (grid[dx][dy] >= 0) {
			HauntedHole h = haunted[grid[dx][dy]];
			dx = h.xDest;
			dy = h.yDest;
			warp = 1;
		}
		if (dfs(dx, dy, xDest, yDest, W, H, warp)) {
			return 1;
		}
	}
	return 0;
}

int bfs(int W, int H) {
	int i, dx, dy, t, w;
	priority_queue<Estado> q;

	q.push(Estado(0, 0, 0, 0));
	visitado[0][0][0] = 1;

	while (!(q.empty())) {
		Estado atual = q.top();
		q.pop();

		for (i = 0; i < 4; i++) {
			dx = atual.x + adj[i][0];
			dy = atual.y + adj[i][1];
			w = atual.teleportado;

			if (!podeVisitar(dx, dy, W, H, w)) {
				continue;
			}

			t = atual.sec + 1;

			if (grid[dx][dy] >= 0) {
				HauntedHole h = haunted[grid[dx][dy]];
				dx = h.xDest;
				dy = h.yDest;
				t += h.tempo;
				w = 1;
			}

			Estado novo = Estado(dx, dy, t, w);
			if (novo.x == W - 1 && novo.y == H - 1) {
				return novo.sec;
			}
			visitado[novo.x][novo.y][novo.teleportado] = 1;
			q.push(novo);
		}
	}

	return INF;
}

int main() {
	int W, H, G, E, x, y, x2, y2, t, i, j, minTime;

	while (1) {
		scanf("%d %d", &W, &H);

		if (W == 0 && H == 0)
			break;

		for (i = 0; i < W; i++) {
			for (j = 0; j < H; j++) {
				grid[i][j] = GRAMA;
				visitado[i][j][0] = visitado[i][j][1] = 0;
			}
		}

		scanf("%d", &G);
		for (i = 0; i < G; i++) {
			scanf("%d %d", &x, &y);
			grid[x][y] = RIP;
		}

		haunted.clear();

		scanf("%d", &E);
		for (i = 0; i < E; i++) {
			scanf("%d %d %d %d %d", &x, &y, &x2, &y2, &t);
			grid[x][y] = i;
			haunted.push_back(HauntedHole(x, y, x2, y2, t));
		}

		int never = 0;
		for (int k = 0; k < E; k++) {
			for (i = 0; i < W; i++) {
				for (j = 0; j < H; j++) {
					visitado[i][j][0] = visitado[i][j][1] = 0;
				}
			}
			HauntedHole h = haunted[k];
			if (h.tempo < 0 && dfs(h.xDest, h.yDest, h.xo, h.yo, W, H,
					grid[h.xDest][h.yDest] < 0)) {
				never = 1;
				break;
			}
		}

		if (never) {
			printf("Never\n");
			continue;
		}

		minTime = bfs(W, H);
		if (minTime == INF) {
			printf("Impossible\n");
		} else {
			printf("%d\n", minTime);
		}
	}

	return 0;
}
