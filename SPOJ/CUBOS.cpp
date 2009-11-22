#include <vector>
#include <stdio.h>

using namespace std;

bool saoIguais(int array1[6], int array2[6]) {
	for (int i = 0; i < 6; i++) {
		if (array1[i] != array2[i]) {
			return false;
		}
	}
	return true;
}

class Cubo {

public:
	int config[24][6];
	Cubo();
	Cubo(int face5, int face1, int face3, int face6, int face4, int face2) {
		int possib[24][6] = { { face1, face3, face6, face4, face2, face5 }, {
				face1, face5, face6, face2, face3, face4 }, { face1, face4,
				face6, face3, face5, face2 }, { face1, face2, face6, face5,
				face4, face3 }, { face2, face6, face5, face1, face4, face3 }, {
				face2, face3, face5, face4, face6, face1 }, { face2, face1,
				face5, face6, face3, face4 }, { face2, face4, face5, face3,
				face1, face6 }, { face3, face6, face4, face1, face2, face5 }, {
				face3, face2, face4, face5, face1, face6 }, { face3, face1,
				face4, face6, face5, face2 }, { face3, face5, face4, face2,
				face6, face1 }, { face4, face1, face3, face6, face2, face5 }, {
				face4, face2, face3, face5, face6, face1 }, { face4, face6,
				face3, face1, face5, face2 }, { face4, face5, face3, face2,
				face1, face6 }, { face5, face1, face2, face6, face4, face3 }, {
				face5, face4, face2, face3, face6, face1 }, { face5, face6,
				face2, face1, face3, face4 }, { face5, face3, face2, face4,
				face1, face6 }, { face6, face3, face1, face4, face5, face2 }, {
				face6, face5, face1, face2, face4, face3 }, { face6, face4,
				face1, face3, face2, face5 }, { face6, face2, face1, face5,
				face3, face4 } };

		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 6; j++) {
				config[i][j] = possib[i][j];
			}
		}
	}
};

int main() {
	int n, v1, v2, v3, v4, v5, v6;

	while (true) {
		scanf("%d", &n);
		vector<Cubo> cubos;

		if (n == 0) {
			break;
		}

		int qt = 0;
		for (int i = 0; i < n; i++) {
			scanf("%d %d %d %d %d %d", &v1, &v2, &v3, &v4, &v5, &v6);
			Cubo c = Cubo(v1, v2, v3, v4, v5, v6);

			int add = 1;

			for (int k = 0; k < i; k++) {

				for (int m = 0; m < 24; m++) {
					if (saoIguais(cubos[k].config[m], c.config[0])) {
						add = 0;
					}
				}

			}

			qt += add;
			cubos.push_back(c);
		}
		printf("%d\n", qt);
	}

	return 0;
}
