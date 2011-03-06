#include <stdio.h>

int main() {
	int n, caso = 1;
	long long res;
	while (scanf("%d", &n) && n) {
		res = ((1 << n) - 1);
		printf("Teste %d\n%lld\n\n", caso++, res);
	}
	return 0;
}
