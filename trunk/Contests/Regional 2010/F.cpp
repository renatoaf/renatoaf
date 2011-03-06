#include <stdio.h>
#include <vector>

using namespace std;

#define MAXG 101
#define MAXP 101
#define MAXS 11

int sistemas[MAXS][MAXP], pontuacaoPiloto[MAXP], posicaoPiloto[MAXG][MAXP],
		ganhadores[MAXP];

void limpaTudo() {
	for (int i = 0; i < MAXS; i++)
		for (int j = 0; j < MAXP; j++)
			sistemas[i][j] = 0;

	for (int i = 0; i < MAXG; i++)
		for (int j = 0; j < MAXP; j++)
			posicaoPiloto[i][j] = 0;
}

int main() {
	int G, P, S, K;

	while (scanf("%d %d", &G, &P) && G && P) {
		limpaTudo();

		for (int i = 0; i < G; i++)
			for (int j = 0; j < P; j++)
				scanf("%d", &posicaoPiloto[i][j]);

		scanf("%d", &S);
		for (int i = 0; i < S; i++) {
			scanf("%d", &K);
			for (int j = 0; j < K; j++) {
				scanf("%d", &sistemas[i][j]);
			}
		}

		for (int sistema = 0; sistema < S; sistema++) {
			for (int i = 0; i < MAXP; i++)
				pontuacaoPiloto[i] = 0;

			for (int corrida = 0; corrida < G; corrida++) {
				for (int piloto = 0; piloto < P; piloto++) {
					pontuacaoPiloto[piloto]
							+= sistemas[sistema][posicaoPiloto[corrida][piloto]
									- 1];
				}
			}

			int maiorP = 0;
			vector<int> vencedores;
			for (int i = 0; i < P; i++) {
				if (pontuacaoPiloto[i] > maiorP) {
					maiorP = pontuacaoPiloto[i];
					vencedores.clear();
					vencedores.push_back(i + 1);
				} else if (pontuacaoPiloto[i] == maiorP) {
					vencedores.push_back(i + 1);
				}
			}

			int qtd = vencedores.size();
			for (int i = 0; i < qtd; i++) {
				printf("%d", vencedores[i]);
				if (i != qtd - 1) {
					printf(" ");
				}
			}
			puts("");
		}
	}

	return 0;
}
