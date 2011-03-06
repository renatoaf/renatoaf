#include <stdio.h>
#include <queue>

using namespace std;

#define MAX 10
#define SAIDA 0
#define CRISTAL 2
#define INICIO 3

int N, M;

struct Estado {
	int x, y, mov;

	Estado() {
	}

	Estado(int x, int y, int mov) :
		x(x), y(y), mov(mov) {
	}
};

int grid[MAX][MAX], visitados[MAX][MAX], adj[4][2] = { { 1, 0 }, { -1, 0 }, {
		0, 1 }, { 0, -1 } };

int podeVisitar(int x, int y) {
	return (x >= 0 && y >= 0 && x < N && y < M && !visitados[x][y]
			&& grid[x][y] != CRISTAL);
}

int bfs(int xIni, int yIni) {
	queue<Estado> q;
	q.push(Estado(xIni, yIni, 0));

	while (!q.empty()) {
		Estado atual = q.front();
		q.pop();

		if (grid[atual.x][atual.y] == SAIDA)
			return atual.mov;

		visitados[atual.x][atual.y] = 1;

		for (int i = 0; i < 4; i++) {
			int dx = atual.x + adj[i][0];
			int dy = atual.y + adj[i][1];
			if (podeVisitar(dx, dy))
				q.push(Estado(dx, dy, atual.mov + 1));
		}
	}

	return -1; // sempre havera sol
}

int main() {
	scanf("%d %d", &N, &M);

	int x, y;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++) {
			scanf("%d", &grid[i][j]);
			if (grid[i][j] == INICIO)
				x = i, y = j;
		}

	printf("%d", bfs(x, y));

	return 0;
}
