#include <stdio.h>

int next[61];
long long padrao[61];
long long totem[1001][1001];
char padraoSTR[61];
char totemSTR[1001];

void preKMP(int m) {
	int i;
	next[0] = -1;
	for (i = 0; i < m; i++) {
		next[i + 1] = next[i] + 1;
		while (next[i + 1] > 0 && padrao[i] != padrao[next[i + 1] - 1])
			next[i + 1] = next[next[i + 1] - 1] + 1;
	}
}

int KMP(int coluna, int m, int n) {
	int i, j = 0, found = 0;
	for (i = 0; i < n; i++)
		while (1) {
			if (totem[i][coluna] == padrao[j]) {
				j++;
				if (j == m) {
					printf("%d %d\n", (i - m + 1), coluna);
					found = 1;
					j = next[j];
				}
				break;
			} else if (j == 0)
				break;
			else
				j = next[j];
		}
	return found;
}

int main() {
	int n, m, i, j, found, coluna, caso = 1, max;
	long long potencias[61], base = 1, mask, num;

	for (i = 0; i < 61; i++) {
		potencias[i] = base;
		base <<= 1;
	}

	while (scanf("%d %d ", &n, &m) > 0) {
		max = n - m + 1;

		mask = 1;
		for (i = 1; i < m; i++) {
			mask = (mask << 1) | 1;
		}

		for (i = 0; i < n; i++) {
			num = 0;
			gets(totemSTR);

			for (j = 0; j < m; j++) {
				if (totemSTR[j] == '|')
					num = (num << 1) | 1;
				else
					num <<= 1;
			}

			totem[i][0] = num;
			for (; j < n; j++) {
				if (totemSTR[j] == '|') {
					num = ((num << 1) | 1) & mask;
				} else {
					num = (num << 1) & mask;
				}
				totem[i][j - m + 1] = num;
			}
		}

		for (i = 0; i < m; i++) {
			padrao[i] = 0;
			gets(padraoSTR);
			for (j = 0; j < m; j++) {
				if (padraoSTR[j] == '|') {
					padrao[i] += potencias[m - j - 1];
				}
			}
		}

		printf("Instancia %d\n", caso++);

		preKMP(m);

		found = 0;
		for (coluna = 0; coluna < max; coluna++) {
			if (KMP(coluna, m, n))
				found = 1;
		}

		if (!found) {
			printf("nenhuma ocorrencia\n");
		}
		printf("\n");
	}

	return 0;
}
