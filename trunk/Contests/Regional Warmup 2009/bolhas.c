#include <stdio.h>

int inv;
int nums[100005];

void mescla(int min, int meio, int max, int N) {
	int i, iEsq, maxEsq, iDir, maxDir;
	int len = max - min + 1;
	int aux[max - min + 1];
	for (i = 0; i < len; i++) {
		aux[i] = nums[min + i];
	}
	i = 0;
	iEsq = 0;
	maxEsq = meio - min;
	iDir = maxEsq + 1;
	maxDir = len - 1;
	while (iEsq <= maxEsq && iDir <= maxDir) {
		if (aux[iEsq] < aux[iDir]) {
			nums[min + i] = aux[iEsq++];
		} else {
			inv += (maxEsq - iEsq + 1);
			nums[min + i] = aux[iDir++];
		}
		i++;
	}

	while (iDir <= maxDir) {
		nums[min + i++] = aux[iDir++];
	}
	while (iEsq <= maxEsq) {
		nums[min + i++] = aux[iEsq++];
	}
}

void mergesort(int min, int max, int N) {
	if (min >= max) {
		return;
	}
	int meio = (min + max) / 2;
	mergesort(min, meio, N);
	mergesort(meio + 1, max, N);
	mescla(min, meio, max, N);
}

int main() {
	int n, i;
	while (1) {
		scanf("%d", &n);

		if (n == 0) {
			break;
		}

		for (i = 0; i < n; i++) {
			scanf("%d", &nums[i]);
		}

		inv = 0;

		mergesort(0, n - 1, n);

		printf(inv % 2 == 0 ? "Carlos\n" : "Marcelo\n");
	}

	return 0;
}
