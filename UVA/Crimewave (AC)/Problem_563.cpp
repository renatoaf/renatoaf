#include <stdio.h>

static const int NN = (50* 50 ) * 2 * 2;
static const int FONTE = 0;
static const int SORVEDOURO = 1;

int R, C, N_BANCOS;
int vizinhos[4][2] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

/* fluxo */
int cap[NN][NN], deg[NN], adj[NN][NN];
int q[NN], prev[NN];

int min(int a, int b) {
	return a < b ? a : b;
}

int dinic(int n, int s, int t) {
	int flow = 0, i, v, u, z;
	while (1) {
		for (i = 0; i < n; i++) {
			q[i] = 0;
			prev[i] = -1;
		}
		int qf = 0, qb = 0;
		prev[q[qb++] = s] = -2;
		while (qb > qf && prev[t] == -1)
			for (u = q[qf++], i = 0, v = 0; i < deg[u]; i++)
				if (prev[v = adj[u][i]] == -1 && cap[u][v])
					prev[q[qb++] = v] = u;
		if (prev[t] == -1)
			break;
		for (z = 0; z < n; z++)
			if (cap[z][t] && prev[z] != -1) {
				int bot = cap[z][t];
				for (v = z, u = prev[v]; u >= 0; v = u, u = prev[v])
					bot = min(bot, cap[u][v]);
				if (!bot)
					continue;
				cap[z][t] -= bot;
				cap[t][z] += bot;
				for (v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {
					cap[u][v] -= bot;
					cap[v][u] += bot;
				}
				flow += bot;
			}
	}
	return flow;
}

/* questao */
void reseta(int n) {
	for (int u = 0; u < n; u++) {
		deg[u] = 0;
		for (int v = 0; v < n; v++)
			cap[u][v] = adj[u][v] = 0;
	}
}

void liga(int u, int v) {
	cap[u][v] = 1;
}

bool escape(int i, int j) {
	return i == 0 || j == 0 || i == R - 1 || j == C - 1;
}

bool valido(int i, int j) {
	return i >= 0 && j >= 0 && i < R && j < C;
}

int mapeiaNo(int i, int j) {
	return SORVEDOURO + ((i * R) + j);
}

int entrada(int i, int j) {
	return 2 * mapeiaNo(i, j);
}

int saida(int i, int j) {
	return entrada(i, j) + 1;
}

int main() {
	int casos, x, y;

	scanf("%d", &casos);
	for (int c = 1; c <= casos; c++) {
		scanf("%d %d %d", &R, &C, &N_BANCOS);

		int N = saida(R - 1, C - 1) + 1;
		reseta(N);

		for (int i = 0; i < N_BANCOS; i++) {
			scanf("%d %d", &x, &y);
			liga(saida(x - 1, y - 1), SORVEDOURO);
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				liga(entrada(i, j), saida(i, j));

				if (escape(i, j))
					liga(FONTE, entrada(i, j));

				for (int k = 0; k < 4; k++) {
					int di = i + vizinhos[k][0];
					int dj = j + vizinhos[k][1];
					if (valido(di, dj))
						liga(saida(i, j), entrada(di, dj));
				}
			}
		}

		for (int u = 0; u < N; u++)
			for (int v = 0; v < N; v++)
				if (cap[u][v])
					adj[u][deg[u]++] = v;

		int emparelhamento = dinic(N, FONTE, SORVEDOURO);
		puts(emparelhamento == N_BANCOS ? "possible" : "not possible");
	}

	return 0;
}
