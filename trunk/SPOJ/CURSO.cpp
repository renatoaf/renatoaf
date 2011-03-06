#include <stdio.h>
#include <algorithm>
#include <string>
#include <vector>
#include <queue>
#include <map>

using namespace std;

#define MAX 205
#define MAXLEN 8

string nomes[MAX];
char seq[MAXLEN];
int cursou[MAX], jaAdd[MAX];
vector<string> semestres[MAX];
vector<int> adj[MAX];

void reset(int n) {
	for (int i = 0; i <= n; i++) {
		cursou[i] = jaAdd[i] = 0;
		semestres[i].clear();
		adj[i].clear();
	}
}

int podeCursar(int disciplina) {
	int nPreReq = adj[disciplina].size();
	for (int i = 0; i < nPreReq; i++)
		if (!cursou[adj[disciplina][i]])
			return 0;
	return 1;
}

int escolhe(int N, int M) {
	int cursadas = 0, semestre = 0;
	priority_queue<int> next;

	while (cursadas < N) {
		for (int i = 1; i <= N; i++)
			if (podeCursar(i) && !jaAdd[i]) {
				jaAdd[i] = 1;
				next.push(-i);
			}

		for (int i = 0; i < M && !next.empty(); i++) {
			int d = -next.top();
			next.pop();
			cursou[d] = 1;
			semestres[semestre].push_back(nomes[d]);
			cursadas++;
		}

		semestre++;
	}

	return semestre;
}

int main() {
	int N, M, K;

	while (1) {
		scanf("%d %d", &N, &M);

		if (N == 0 && M == 0)
			break;

		map<string, int> disciplinas;

		int NVERT = 1;
		for (int i = 0; i < N; i++) {
			scanf("%s %d", seq, &K);
			string avancada = string(seq);

			int v = disciplinas[avancada];
			if (!v) {
				nomes[NVERT] = avancada;
				disciplinas[avancada] = v = NVERT++;
			}

			for (int j = 0; j < K; j++) {
				scanf("%s", seq);
				string prereq = string(seq);

				int u = disciplinas[prereq];
				if (!u) {
					nomes[NVERT] = prereq;
					disciplinas[prereq] = u = NVERT++;
				}

				adj[v].push_back(u);
			}
		}

		NVERT--;

		int numSemestres = escolhe(NVERT, M);
		printf("Formatura em %d semestres\n", numSemestres);
		for (int i = 0; i < numSemestres; i++) {
			printf("Semestre %d :", i + 1);

			sort(semestres[i].begin(), semestres[i].end());
			int nDisc = semestres[i].size();
			for (int k = 0; k < nDisc; k++)
				printf(" %s", semestres[i][k].c_str());
			printf("\n");
		}

		reset(NVERT);
	}
}
