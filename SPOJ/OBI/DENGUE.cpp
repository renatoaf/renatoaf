#include <stdio.h>
#include <vector>
#include <queue>

using namespace std;

#define MAX 100
#define INF 500

vector<int> adj[MAX];
int visitado[MAX];

struct Estado {
	int v, d;

	Estado() {
	}

	Estado(int v, int d) :
		v(v), d(d) {
	}
};

int bfs(int v, int n) {
	for (int i = 0; i < n; i++)
		visitado[i] = 0;
	queue<Estado> q;
	q.push(Estado(v, 0));

	int max = -1;
	while (!q.empty()) {
		Estado next = q.front();
		q.pop();

		if (next.d > max)
			max = next.d;

		visitado[next.v] = 1;
		int grau = adj[next.v].size();
		for (int i = 0; i < grau; i++)
			if (!visitado[adj[next.v][i]])
				q.push(Estado(adj[next.v][i], next.d + 1));
	}
	return max;
}

int main() {
	int N, u, v, caso = 1;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;

		for (int i = 0; i < N - 1; i++) {
			scanf("%d %d", &u, &v);
			u--, v--;
			adj[u].push_back(v);
			adj[v].push_back(u);
		}

		int melhor = INF, escolhido = -1;
		for (int i = 0; i < N; i++) {
			int max = bfs(i, N);
			if (max < melhor)
				melhor = max, escolhido = i + 1;
		}

		printf("Teste %d\n%d\n\n", caso++, escolhido);

		for (int i = 0; i < N; i++)
			adj[i].clear();
	}

	return 0;
}
