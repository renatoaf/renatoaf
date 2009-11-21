#include <stdio.h>
#include <vector>
#include <algorithm>

using namespace std;

#define MAXN 64805
#define X 0
#define E 1
#define INCOG 2

int maximoDentro[MAXN], incognitas[MAXN];

int min(int a, int b) {
	return a < b ? a : b;
}

int max(int a, int b) {
	return a > b ? a : b;
}

struct Cartao {
	int H, M, S, T;

	Cartao();
	Cartao(int hh, int mm, int ss, int t) {
		H = hh;
		M = mm;
		S = ss;
		T = t;
	}
};

bool compare(Cartao a, Cartao b) {
	if (b.H != a.H) {
		return a.H < b.H;
	} else if (a.M != b.M) {
		return a.M < b.M;
	}
	return a.S < b.S;
}

int main() {
	int N, hh, mm, ss;
	char tipo;

	while (true) {
		scanf("%d", &N);

		if (N == 0)
			break;

		vector<Cartao> cartoes;

		int nX = 0, nE = 0;

		for (int i = 0; i < N; i++) {
			scanf("%d:%d:%d %c", &hh, &mm, &ss, &tipo);
			int t = 0;
			switch (tipo) {
			case 'X':
				t = X;
				nX++;
				break;
			case 'E':
				nE++;
				t = E;
				break;
			case '?':
				t = INCOG;
				break;
			}

			cartoes.push_back(Cartao(hh, mm, ss, t));
		}

		sort(cartoes.begin(), cartoes.end(), compare);

		int maxIncogDentro = (N / 2) - nE;
		for (int i = 0; i < N; i++) {
			Cartao atual = cartoes[i];
			int maxAtual = (i == 0) ? 0 : (maximoDentro[i - 1]);
			int incogAtual = (i == 0) ? 0 : (incognitas[i - 1]);
			if (atual.T == X) {
				maxAtual--;
			} else if (atual.T == E) {
				maxAtual++;
			} else if (atual.T == INCOG) {
				incogAtual++;
			}
			maximoDentro[i] = maxAtual;
			incognitas[i] = incogAtual;
		}

		int maxDentro = 0;
		for (int i = 0; i < N; i++) {
			int maxMomento = maximoDentro[i];
			int incognitasE = min(incognitas[i], maxIncogDentro);
			int incognitasX = max(0, incognitas[i] - incognitasE);
			maxDentro = max(maxDentro, maxMomento + incognitasE - incognitasX);
		}
		printf("%d\n", maxDentro);
	}

	return 0;
}
