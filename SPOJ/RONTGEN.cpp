#include <stdio.h>
#include <string>

using namespace std;

#define MAX 1005

char seq[MAX];

char tochar(int n) {
	return n + '0';
}

int main() {
	int casos, c, k, d, i, n, contagem;
	char atual;

	scanf("%d", &casos);
	for (c = 1; c <= casos; c++) {
		scanf("%s %d", seq, &k);

		puts(seq);

		string sequencia = string(seq);
		for (d = 0; d < k; d++) {
			string nova = "";
			i = 0;
			n = sequencia.size();
			while (i < n) {
				contagem = 0;
				atual = sequencia[i];
				while (i < n && sequencia[i] == atual) {
					contagem++;
					i++;
				}
				nova += tochar(contagem);
				nova += atual;
			}
			sequencia = nova;
			puts(sequencia.c_str());
		}

		puts("");
	}

	return 0;
}
