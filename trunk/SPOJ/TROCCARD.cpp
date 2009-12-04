#include <stdio.h>
#include <map>

using namespace std;

int main() {
	int A, B, xi, yi, qt1, qt2;

	while (1) {
		scanf("%d %d", &A, &B);

		if (A == 0 && B == 0) {
			break;
		}

		map<int, int> mapa1, mapa2;
		map<int, int>::iterator it1, it2;

		for (int i = 0; i < A; i++) {
			scanf("%d", &xi);
			mapa1[xi] = 1;
		}

		for (int i = 0; i < B; i++) {
			scanf("%d", &yi);
			mapa2[yi] = 1;
		}

		qt1 = qt2 = 0;
		for (it1 = mapa1.begin(); it1 != mapa1.end(); it1++)
			if (!mapa2[(*it1).first])
				qt1++;
		for (it2 = mapa2.begin(); it2 != mapa2.end(); it2++)
			if (!mapa1[(*it2).first])
				qt2++;

		if (qt1 < qt2)
			printf("%d\n", qt1);
		else
			printf("%d\n", qt2);
	}

	return 0;
}
