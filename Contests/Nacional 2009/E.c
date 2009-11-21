#include <stdio.h>

long long min(long long a, long long b) {
	return a < b ? a : b;
}

long long valorAPagar(long long consumo) {
	long long valor = 0;
	if (consumo > 0) {
		valor += 2 * min(consumo, 100);
		consumo -= 100;
	}
	if (consumo > 0) {
		valor += 3 * min(consumo, 10000 - 100);
		consumo -= 10000 - 100;
	}
	if (consumo > 0) {
		valor += 5 * min(consumo, 1000000 - 10000);
		consumo -= 1000000 - 10000;
	}
	if (consumo > 0) {
		valor += 7 * consumo;
	}
	return valor;
}

long long consumo(long long valorAPagar) {
	long long consumo = 0;
	if (valorAPagar > 2 * 100) {
		consumo += 100;
		valorAPagar -= (2 * 100);
	} else {
		consumo += valorAPagar / 2;
		valorAPagar = 0;
	}
	if (valorAPagar > (10000 - 100) * 3) {
		consumo += (10000 - 100);
		valorAPagar -= ((10000 - 100) * 3);
	} else {
		consumo += valorAPagar / 3;
		valorAPagar = 0;
	}
	if (valorAPagar > (1000000 - 10000) * 5) {
		consumo += (1000000 - 10000);
		valorAPagar -= ((1000000 - 10000) * 5);
	} else {
		consumo += valorAPagar / 5;
		valorAPagar = 0;
	}
	if (valorAPagar > 0) {
		consumo += valorAPagar / 7;
	}
	return consumo;
}

int main() {
	// A = soma dos consumos, B = diferenca dos valores
	int A, B;
	long long consumoConjunto, ini, fim, meio, pagamentoIndividual,
			pagamentoVizinho, diferenca;

	while (1) {
		scanf("%d %d", &A, &B);

		if (A == 0 && B == 0)
			break;

		consumoConjunto = consumo(A);
		ini = 0;
		fim = consumoConjunto;

		while (ini <= fim) {
			meio = (ini + fim) / 2;
			pagamentoIndividual = valorAPagar(meio);
			pagamentoVizinho = valorAPagar(consumoConjunto - meio);
			diferenca = pagamentoVizinho - pagamentoIndividual;

			if (diferenca == B) {
				break;
			} else if (diferenca < B) {
				fim = meio - 1;
			} else {
				ini = meio + 1;
			}
		}

		printf("%lld\n", pagamentoIndividual);
	}

	return 0;
}
