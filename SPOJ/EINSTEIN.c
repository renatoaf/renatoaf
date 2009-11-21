#include <stdio.h>

#define NN 101
#define MAXW 10001

int g[NN][NN], v[NN], w[NN], na[NN], a[NN];

int min(int a, int b) {
	return a < b ? a : b;
}

int minCut(int n) {
	int i, j, zj;
	// init the remaining vertex set
	for (i = 0; i < n; i++)
		v[i] = i;
	// run Stoer-Wagner
	int best = MAXW * n * n;
	while (n > 1) { // initialize the set A and vertex weights
		a[v[0]] = 1;
		for (i = 1; i < n; i++) {
			a[v[i]] = 0;
			na[i - 1] = i;
			w[i] = g[v[0]][v[i]];
		}
		// add the other vertices
		int prev = v[0];
		for (i = 1; i < n; i++) {
			// find the most tightly connected non-A vertex
			zj = -1;
			for (j = 1; j < n; j++)
				if (!a[v[j]] && (zj < 0 || w[j] > w[zj]))
					zj = j;
			a[v[zj]] = 1; // add it to A
			if (i == n - 1) { // last vertex?
				best = min(best, w[zj]); // remember the cut weight
				for (j = 0; j < n; j++)
					// merge prev and v[zj]
					g[v[j]][prev] = g[prev][v[j]] += g[v[zj]][v[j]];
				v[zj] = v[--n];
				break;
			}
			prev = v[zj];
			// update the weights of its neighbours
			for (j = 1; j < n; j++)
				if (!a[v[j]])
					w[j] += g[v[zj]][v[j]];
		}
	}
	return best;
}

int main() {
	int casos, n, m, a, b, c, i, j, caso;

	scanf("%d", &casos);

	for (caso = 1; caso <= casos; caso++) {
		scanf("%d %d", &n, &m);

		/* preencher com 0 a matriz de adj sempre */
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				g[i][j] = 0;

		for (i = 0; i < m; i++) {
			scanf("%d %d %d", &a, &b, &c);
			a--;
			b--;
			g[a][b] = g[b][a] = c;
		}

		printf("%d\n", minCut(n));
	}

	return 0;
}
