#include <stdio.h>
#include <queue>

using namespace std;

int resolve(queue<int> &q, int k, int n, int R) {
	int total = 0;
	for (int i = 0; i < R; i++) {
		int parcial = 0, qtGrupos = 0;
		while (qtGrupos < n) {
			int next = q.front();
			if (parcial + next <= k) {
				q.pop();
				q.push(next);
				parcial += next;
				qtGrupos++;
			} else {
				break;
			}
		}
		total += parcial;
	}
	return total;
}

int main() {
	int T, R, K, N, gi;
	scanf("%d", &T);

	for (int c = 1; c <= T; c++) {
		int qtPessoas = 0;
		scanf("%d %d %d", &R, &K, &N);

		queue<int> q;
		for (int i = 0; i < N; i++) {
			scanf("%d", &gi);
			q.push(gi);
			qtPessoas += gi;
		}

		long long sol;
		if (qtPessoas <= K) // evitar calculo desnecessario
			sol = (qtPessoas * R);
		else
			sol = resolve(q, K, N, R);

		printf("Case #%d: %lld\n", c, sol);
	}

	return 0;
}