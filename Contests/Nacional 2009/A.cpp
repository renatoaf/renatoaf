#include <stdio.h>
#include <string.h>
#include <math.h>
#include <vector>
#include <algorithm>

using namespace std;

#define MAXN 100001

vector<int> no[MAXN];

int calcula(int numero, int T) {
	int q = no[numero].size();
	if (q == 0)
		return 1;

	int qnt = (int) ceil(((T / 100.0) * q));

	vector<int> teste;
	for (int i = 0; i < q; i++) {
		teste.push_back(calcula(no[numero][i], T));
	}
	sort(teste.begin(), teste.end());
	int ans = 0;
	for (int k = 0; k < qnt; k++)
		ans += teste[k];
	return ans;
}

int main() {
	int N, T, x;

	while (true) {
		scanf("%d %d", &N, &T);

		if (N == 0 && T == 0)
			break;

		for (int i = 0; i <= N; i++) {
			no[i].clear();
		}

		for (int i = 1; i <= N; i++) {
			scanf("%d", &x);
			no[x].push_back(i);
		}

		printf("%d\n", calcula(0, T));
	}

	return 0;
}
