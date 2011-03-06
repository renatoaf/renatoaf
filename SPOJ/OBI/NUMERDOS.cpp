#include <stdio.h>
#include <algorithm>
#include <cstring>
#include <string>
#include <vector>
#include <queue>
#include <map>

using namespace std;

#define MAXLEN 20
#define MAX 105
#define INF 100000

struct Escritor {
	string nome;
	char inicial;
	int numeroDeErdos;

	Escritor() {
		numeroDeErdos = INF;
	}

	bool operator <(const Escritor &outro) const {
		int cmp = strcmp(nome.c_str(), outro.nome.c_str());
		if (cmp == 0)
			return inicial < outro.inicial;
		return cmp < 0;
	}
};

Escritor escritores[MAX];
int adj[MAX][MAX], visitados[MAX];

string nomePraMapear(string nome, char inicial) {
	nome.append(" ");
	nome.append(1, inicial);
	return nome;
}

void bfs(int n) {
	for (int i = 0; i <= n; i++)
		visitados[i] = 0;

	queue<int> q;
	q.push(1);
	while (!q.empty()) {
		int u = q.front();
		q.pop();

		if (visitados[u])
			continue;

		visitados[u] = 1;
		for (int v = 1; v <= n; v++)
			if (adj[u][v]) {
				int novoErdos = escritores[u].numeroDeErdos + 1;
				if (novoErdos < escritores[v].numeroDeErdos) {
					escritores[v].numeroDeErdos = novoErdos;
					q.push(v);
				}
			}
	}
}

int main() {
	int A, caso = 1;

	Escritor erdos;
	erdos.inicial = 'P';
	erdos.nome = "Erdos";
	erdos.numeroDeErdos = 0;

	while (scanf("%d", &A) && A) {
		map<string, int> mapa;

		int nEscritores = 1;
		escritores[nEscritores] = erdos;
		mapa[nomePraMapear(erdos.nome, erdos.inicial)] = nEscritores++;

		for (int i = 0; i < A; i++) {
			char ultimo;

			vector<int> indices;
			do {
				getchar();
				Escritor e;
				char cadeia[MAXLEN];
				scanf("%c. %s", &e.inicial, cadeia);
				int indiceFim = strlen(cadeia) - 1;
				ultimo = cadeia[indiceFim];
				cadeia[indiceFim] = '\0';
				e.nome = string(cadeia);

				string nomeMap = nomePraMapear(e.nome, e.inicial);
				if (!mapa[nomeMap]) {
					escritores[nEscritores] = e;
					mapa[nomeMap] = nEscritores++;
				}
				indices.push_back(mapa[nomeMap]);
			} while (ultimo != '.');

			int numEscritores = indices.size();
			for (int i = 0; i < numEscritores; i++)
				for (int j = 0; j < i; j++) {
					int u = indices[i], v = indices[j];
					adj[u][v] = adj[v][u] = 1;
				}
		}

		nEscritores--;

		bfs(nEscritores);

		sort(escritores, escritores + nEscritores + 1);
		printf("Teste %d\n", caso++);
		for (int i = 1; i <= nEscritores; i++)
			if (escritores[i].numeroDeErdos != 0)
				if (escritores[i].numeroDeErdos == INF)
					printf("%c. %s: infinito\n", escritores[i].inicial,
							escritores[i].nome.c_str());
				else
					printf("%c. %s: %d\n", escritores[i].inicial,
							escritores[i].nome.c_str(),
							escritores[i].numeroDeErdos);
		printf("\n");

		for (int i = 0; i <= nEscritores; i++)
			for (int j = 0; j <= nEscritores; j++)
				adj[i][j] = 0;
	}

	return 0;
}
