/*
 * Nao foi aceito
 */

#include <stdio.h>

long long posicoes[1000005];

int main() {
	int casos, c, m, n, i, j, l, t, a, b, q, p;

	scanf("%d", &casos);

	for (c = 1; c <= casos; c++) {
		scanf("%d%d", &n, &m);

		if (c > 1) {
			for (i = 0; i <= n; i++) {
				posicoes[i] = 0;
			}
		}

		for (i = 0; i < m; i++) {
			scanf("%d%d%d", &t, &a, &b);
			l = b - a;
			if (t == 1) {
				for (j = 0; j <= l; j++) {
					posicoes[a + j] += j;
				}
			} else if (t == 2) {
				for (j = 0; j <= l; j++) {
					posicoes[a + j] += (j * j);
				}
			} else {
				for (j = 0; j <= l; j++) {
					posicoes[a + j] += (j * j * j);
				}
			}
		}

		for (i = 1; i <= n; i++) {
			posicoes[i] += posicoes[i - 1];
		}

		scanf("%d", &q);
		printf("Case %d:\n", c);
		for (i = 0; i < q; i++) {
			scanf("%d", &p);
			printf("%llu\n", posicoes[p]);
		}
	}

	return 0;
}
