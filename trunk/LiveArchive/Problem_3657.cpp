#include <iostream>
#include <vector>

using namespace std;

int pontos[10003][2];
int capacidade[10013];
int total;
vector<int> listasAdjacencias[10010];

int distancia(int i, int j) {
	return (pontos[j][0] - pontos[i][0]) * (pontos[j][0] - pontos[i][0])
			+ (pontos[j][1] - pontos[i][1]) * (pontos[j][1] - pontos[i][1]);
}

int dfs(int vertice, int pai, int C) {
	int tam = listasAdjacencias[vertice].size();
	for (int i = 0; i < tam; i++) {
		if (listasAdjacencias[vertice][i] != pai) {
			dfs(listasAdjacencias[vertice][i], vertice, C);
		}
	}
	if (capacidade[vertice] >= C) {
		total++;
	} else {
		capacidade[pai] += capacidade[vertice];
	}
	return 0;
}

int main() {
	int N, C, i, minDist, dist, min;

	while (true) {
		cin >> N >> C;

		if (N == 0 && C == 0) {
			break;
		}

		for (i = 1; i <= N; i++) {
			listasAdjacencias[i].clear();
		}

		for (i = 1; i <= N; i++) {
			cin >> pontos[i][0] >> pontos[i][1] >> capacidade[i];

			if (i != 1) {
				minDist = 1000000;
				min = 0;
				for (int j = 1; j < i; j++) {
					dist = distancia(i, j);
					if (dist < minDist) {
						minDist = dist;
						min = j;
					}
				}
				listasAdjacencias[min].push_back(i);
			}
		}

		total = 0;
		dfs(1, 0, C);
		cout << total << endl;
	}

	return 0;
}
