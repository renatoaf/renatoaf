#include <stdio.h>

char MOVE = 'I';
char N_MOVE = '*';
char N_TEM = '.';
char HORARIO = '(';
char A_HORARIO = ')';
char LIVRE = 'F';
char BLOQUEADA = 'B';

int adj[6][2];
char estados[101][101];
char motor[101][101];
char linha[101];

void adjacentes(int x, int y, int adj[6][2]) {
	adj[0][0] = x - 1;
	adj[0][1] = y;
	adj[1][0] = x - 1;
	adj[1][1] = y + 1;
	adj[2][0] = x;
	adj[2][1] = y - 1;
	adj[3][0] = x;
	adj[3][1] = y + 1;
	adj[4][0] = x + 1;
	adj[4][1] = y;
	adj[5][0] = x + 1;
	adj[5][1] = y - 1;
}

int podeVisitar(int i, int j, int lin, int col) {
	if (i >= 0 && j >= 0 && j < col && i < lin && estados[i][j] != N_TEM) {
		return 1;
	}
	return 0;
}

void floodBloqueando(int i, int j, int lin, int col) {
	int k, x, y;
	estados[i][j] = BLOQUEADA;

	int adj[6][2];
	adjacentes(i, j, adj);

	// bloqueando enquanto houver adjacente nao-bloqueado
	for (k = 0; k < 6; k++) {
		x = adj[k][0];
		y = adj[k][1];
		if (podeVisitar(x, y, lin, col) && estados[x][y] != BLOQUEADA) {
			floodBloqueando(x, y, lin, col);
		}
	}
}

// retorna 1 se bloqueou, 0 caso contrario
int flood(int i, int j, int lin, int col) {
	int k, x, y;

	int adj[6][2];
	adjacentes(i, j, adj);

	for (k = 0; k < 6; k++) {
		x = adj[k][0];
		y = adj[k][1];

		if (podeVisitar(x, y, lin, col)) {
			if (estados[i][j] == estados[x][y]) {
				// Uma engrenagem está bloqueada quando ela
				// está tentando girar em ambos sentidos ao mesmo tempo.
				floodBloqueando(i, j, lin, col);
				return 1;

			} else if (estados[x][y] == LIVRE) {
				// Quando uma engrenagem tenta girar em um sentido, todas
				// as outras adjacentes tentam girar no sentido oposto.
				if (estados[i][j] == HORARIO) {
					estados[x][y] = A_HORARIO;
				} else if (estados[i][j] == A_HORARIO) {
					estados[x][y] = HORARIO;
				}

				if (flood(x, y, lin, col) == 1) {
					return 1;
				}
			}
		}
	}

	return 0;
}

int main() {
	int linhas, colunas, i, j;

	while (1) {
		scanf("%d%d", &linhas, &colunas);

		if (linhas == 0 && colunas == 0) {
			break;
		}

		gets(linha);
		for (i = 0; i < linhas; i++) {
			gets(linha);
			for (j = 0; j < colunas; j++) {
				motor[i][j] = linha[j];
				if (motor[i][j] == MOVE) {
					// São inicialmente ativadas e tentam giram em sentido
					// horário
					estados[i][j] = HORARIO;
				} else if (motor[i][j] == N_MOVE) {
					// Uma engrenagem está livre quando ela não é ativada
					estados[i][j] = LIVRE;
				} else {
					estados[i][j] = N_TEM;
				}
			}
		}

		for (i = 0; i < linhas; i++) {
			for (j = 0; j < colunas; j++) {
				if (motor[i][j] == MOVE) {
					flood(i, j, linhas, colunas);
				}
			}
		}

		printf("\n");
		for (i = 0; i < linhas; i++) {
			for (j = 0; j < colunas; j++) {
				printf("%c", estados[i][j]);
			}
			printf("\n");
		}
	}

	return 0;
}
