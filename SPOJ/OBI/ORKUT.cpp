#include <stdio.h>
#include <string>
#include <vector>
#include <map>
#include <queue>

using namespace std;

#define MAX 30
#define MAXLEN 15

int indegree[MAX], ordenacaoTop[MAX];
vector<int> adj[MAX];
string nomes[MAX];
char nome[MAXLEN];

bool topsort(int N) {
	queue<int> q; // Vertices de indegree (no de arestas que 'entram') 0
	int atual, proximo, j = 0;
	for (int i = 0; i < N; i++)
		if (indegree[i] == 0)
			q.push(i);
	while (!q.empty()) {
		atual = q.front();
		q.pop();
		ordenacaoTop[j++] = atual;
		int degree = adj[atual].size();
		for (int i = 0; i < degree; i++) {
			proximo = adj[atual][i];
			indegree[proximo]--;
			if (indegree[proximo] == 0)
				q.push(proximo);
		}
	}
	return j == N; // se j < N, nao eh um DAG, possui ciclo
}

void reset(int N) {
	for (int i = 0; i < N; i++) {
		indegree[i] = 0;
		adj[i].clear();
	}
}

int main() {
	int N, M, caso = 1;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;

		map<string, int> pessoas;
		for (int i = 0; i < N; i++) {
			scanf("%s", nome);
			string pessoa = string(nome);
			pessoas[pessoa] = i;
			nomes[i] = pessoa;
		}

		for (int i = 0; i < N; i++) {
			scanf("%s %d", nome, &M);
			int u = pessoas[string(nome)];

			for (int j = 0; j < M; j++) {
				scanf("%s", nome);
				int v = pessoas[string(nome)];

				adj[v].push_back(u);
				indegree[u]++;
			}
		}

		bool sucesso = topsort(N);

		printf("Teste %d\n", caso++);
		if (sucesso)
			for (int i = 0; i < N; i++) {
				if (i > 0)
					printf(" ");
				printf("%s", nomes[ordenacaoTop[i]].c_str());
			}
		else
			printf("impossivel");

		printf("\n\n");

		reset(N);
	}

	return 0;
}
