/*
 * A plug for UNIX
 */

#include <stdio.h>
#include <map>
#include <string>
#include <vector>
#include <queue>

#define FONTE 0
#define SORVEDOURO 1
#define MAXLEN 24
#define NN 600

using namespace std;

int indice;
char texto1[MAXLEN], texto2[MAXLEN];

/* bfs */
vector<int> plugs[NN];
int plugado[NN];
int visitado[NN];

/* fluxo */
int cap[NN][NN];
int fnet[NN][NN];
int q[NN], qf, qb, prev[NN];

int min(int a, int b) {
	return a < b ? a : b;
}

int fordFulkerson(int n, int s, int t) {
	int flow = 0;
	while (true) {
		for (int i = 0; i < n; i++) {
			prev[i] = -1;
		}
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

void liga(int u, int v) {
	cap[u][v] = 1;
}

void reseta(int N) {
	indice = SORVEDOURO + 1;
	for (int i = 0; i < N; i++) {
		plugs[i].clear();
		for (int j = 0; j < N; j++)
			fnet[i][j] = cap[i][j] = 0;
	}
}

vector<int> plugaveis(int origem, int n) { // BFS
	vector<int> plugaveis;
	queue<int> q;

	for (int i = 0; i < n; i++) {
		visitado[i] = 0;
	}

	plugaveis.push_back(origem);
	q.push(origem);
	while (!q.empty()) {
		int atual = q.front();
		q.pop();

		visitado[atual] = 1;
		int grau = plugs[atual].size();
		for (int i = 0; i < grau; i++) {
			int next = plugs[atual][i];
			if (!visitado[next]) {
				q.push(next);
				plugaveis.push_back(next);
			}
		}
	}

	return plugaveis;
}

int main() {
	int casos, N = NN, REC, DEV, ADAP;

	scanf("%d", &casos);
	for (int c = 1; c <= casos; c++) {
		reseta(N);

		map<string, int> plugins;
		map<string, int> devices;

		scanf(" %d", &REC);
		for (int i = 0; i < REC; i++) {
			scanf("%s", texto1);
			string rec = string(texto1);

			if (!plugins[rec])
				plugins[rec] = indice++;

			liga(plugins[rec], SORVEDOURO);
		}

		scanf("%d", &DEV);
		for (int i = 0; i < DEV; i++) {
			scanf("%s %s", texto1, texto2);
			string dev = string(texto1);
			string rec = string(texto2);

			if (!plugins[rec])
				plugins[rec] = indice++;
			if (!devices[dev]) {
				devices[dev] = indice++;
				plugado[devices[dev]] = plugins[rec];
			}

			liga(FONTE, devices[dev]);
		}

		scanf("%d", &ADAP);
		for (int i = 0; i < ADAP; i++) {
			scanf("%s %s", texto1, texto2);
			string rec1 = string(texto1);
			string rec2 = string(texto2);

			if (!plugins[rec1])
				plugins[rec1] = indice++;
			if (!plugins[rec2])
				plugins[rec2] = indice++;

			plugs[plugins[rec1]].push_back(plugins[rec2]);
		}

		N = indice;
		for (map<string, int>::iterator it = devices.begin(); it
				!= devices.end(); it++) {
			int dev = (*it).second;
			vector<int> opcoes = plugaveis(plugado[dev], N);
			int numOpcoes = opcoes.size();

			for (int i = 0; i < numOpcoes; i++)
				liga(dev, opcoes[i]);
		}

		int emparelhamento = fordFulkerson(N, FONTE, SORVEDOURO);
		printf("%d\n", DEV - emparelhamento);
		if (c != casos)
			printf("\n");
	}

	return 0;
}
