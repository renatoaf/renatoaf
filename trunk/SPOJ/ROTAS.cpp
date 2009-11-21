#include <iostream>
#include <string.h>
#include <string>
#include <map>

#define MAX_CIDADES 101
#define MAX_ROTAS 10001
#define CODIGO_APAGADA -1

using namespace std;

int cidades, estradas, i, j, apagada;

int lista[MAX_CIDADES][MAX_CIDADES];
int graus[MAX_CIDADES];
bool visited[MAX_CIDADES];
string cidade, origem, destino;
bool pode;

void dfs(int no) {
	if (no == 0) {
		pode = true;
		return;
	}
	if (visited[no]) {
		return;
	}

	visited[no] = true;

	for (int index = 0; index < graus[no]; index++) {
		int no2 = lista[no][index];
		if (no2 != CODIGO_APAGADA && !visited[no2]) {
			dfs(no2);
		}
	}
}

int main() {
	while (true) {
		scanf("%d %d", &cidades, &estradas);
		if (cidades == 0 && estradas == 0) {
			break;
		}
		memset(lista, 0, sizeof(lista));
		memset(graus, 0, sizeof(graus));

		int estr[estradas][3];
		map<string, int> mapa;
		string nomes[cidades];

		for (i = 0; i < cidades; i++) {
			char axu[22];
			scanf("%s", axu);
			cidade = string(axu);
			mapa[cidade] = i;
			nomes[i] = cidade;
		}

		for (j = 0; j < estradas; j++) {
			char c1[22];
			char c2[22];
			scanf("%s %s", c1, c2);
			origem = string(c1);
			destino = string(c2);
			lista[mapa[origem]][graus[mapa[origem]]++] = mapa[destino];
			estr[j][0] = mapa[origem];
			estr[j][1] = mapa[destino];
			estr[j][2] = graus[mapa[origem]] - 1;
		}

		bool imprimi = false;
		for (i = 0; i < estradas; i++) {
			memset(visited, false, sizeof(visited));
			int o = estr[i][0];
			int d = estr[i][1];
			int ind = estr[i][2];
			pode = false;
			int x = lista[o][ind];

			lista[o][ind] = CODIGO_APAGADA;
			dfs(o);
			lista[o][ind] = x;

			if (!pode) {
				imprimi = true;
				printf("%s %s\n", nomes[o].c_str(), nomes[d].c_str());
			}
		}

		if (!imprimi) {
			printf("Nenhuma\n");
		}

		printf("\n");
	}

	return 0;
}

