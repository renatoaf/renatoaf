/*
 * Climbing Trees
 */

#include <string>
#include <climits>
#include <map>
#include <stdio.h>

using namespace std;

#define MAXLEN 35
#define MAX 305
#define INF (INT_MAX/2)

int grafo[MAX][MAX];
char p1[MAXLEN], p2[MAXLEN];

int min(int a, int b) {
	return a < b ? a : b;
}

void floyd(int n) {
	for (int k = 1; k <= n; k++)
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				grafo[i][j] = min(grafo[i][j], grafo[i][k] + grafo[k][j]);
}

void imprimeGrau(int grau) {
	if (grau > 1) {
		grau -= 2; // grand e parent
		for (int i = 0; i < grau; i++)
			printf("great ");
		printf("grand ");
	}
}

int main() {
	for (int i = 0; i < MAX; i++)
		for (int j = 0; j < MAX; j++)
			grafo[i][j] = INF;

	int id = 1;
	map<string, int> pessoas;

	while (1) {
		scanf("%s %s", p1, p2);
		string s1 = string(p1);
		string s2 = string(p2);

		if (s1.compare("no.child") == 0)
			break;

		if (!pessoas[s1])
			pessoas[s1] = id++;
		if (!pessoas[s2])
			pessoas[s2] = id++;
		grafo[pessoas[s1]][pessoas[s2]] = 1;
	}

	int N = id;
	floyd(N);

	while (scanf("%s %s", p1, p2) != EOF) {
		string s1 = string(p1);
		string s2 = string(p2);

		int id1 = pessoas[s1];
		int id2 = pessoas[s2];

		if (!id1 || !id2) {
			puts("no relation");
			continue;
		}

		int distancia12 = grafo[id1][id2];
		int distancia21 = grafo[id2][id1];

		if (distancia12 < INF && distancia21 == INF) {
			imprimeGrau(distancia12);
			puts("child");
		} else if (distancia21 < INF && distancia12 == INF) {
			imprimeGrau(distancia21);
			puts("parent");
		} else if (distancia12 == INF && distancia21 == INF) { // sem ligacao direta
			int th = INF;
			int j = 0;

			for (int k = 1; k <= N; k++) { // olha intermediarios
				int n = grafo[id1][k], m = grafo[id2][k];
				if (k == id1 || k == id2 || n == INF || m == INF)
					continue;
				int atual = min(n, m);
				if (atual < th)
					th = atual, j = abs(n - m);
			}

			if (th == INF)
				puts("no relation");
			else {
				if ((th - 1) == 0 && j == 0)
					puts("sibling");
				else {
					printf("%d cousin", th - 1);
					if (j > 0)
						printf(" removed %d", j);
					puts("");
				}
			}
		}
	}

	return 0;
}
