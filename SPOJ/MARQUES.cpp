#include <stdio.h>
#include <string.h>
#include <map>
#include <string>

using namespace std;

#define NN 205

int flow;
int cap[NN][NN], fnet[NN][NN];
int q[NN], qf, qb, prev[NN];
char horario[10];

int min(int a, int b) {
	return a < b ? a : b;
}

int fordFulkerson(int n, int s, int t) {
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

int main() {
	int casos, caso, i, j, n, m, k;

	scanf("%d", &casos);
	for (caso = 1; caso <= casos; caso++) {
		memset(fnet, 0, sizeof(fnet));
		memset(cap, 0, sizeof(cap));

		scanf("%d %d", &n, &m);

		int fonte = 0;
		int nHorarios = 0;
		int destino = 1;

		map<string, int> horarios;
		map<string, int> existe;

		for (i = 1; i <= m; i++) {
			scanf("%s", horario);
			string hora = string(horario);
			existe[hora] = 1;
			if (horarios[hora] == 0)
				horarios[hora] = destino + 1 + nHorarios++;
			cap[horarios[hora]][destino]++; // pode ter 2 onibus saindo no msm horario
		}

		nHorarios += 2;
		int totalVertices = nHorarios + n;

		for (i = nHorarios; i < totalVertices; i++) {
			scanf("%d", &k);
			for (j = 0; j < k; j++) {
				scanf("%s", horario);
				string hora = string(horario);
				if (existe[hora])
					cap[i][horarios[hora]] = 1;
			}
			cap[fonte][i] = 1;
		}

		flow = 0;

		int capacidadeMinima = 1; // min passageiros
		int emparelhamento = fordFulkerson(totalVertices, fonte, destino);
		while (emparelhamento < n) {
			for (i = 2; i < nHorarios; i++) {
				cap[i][destino]++;
			}
			emparelhamento = fordFulkerson(totalVertices, fonte, destino);
			capacidadeMinima++;
		}
		printf("%d\n", capacidadeMinima);
	}

	return 0;
}
