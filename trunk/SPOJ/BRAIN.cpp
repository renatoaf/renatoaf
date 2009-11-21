#include <iostream>
#include <stack>
#include <string>

using namespace std;

char bytes[30000];
int ponteiroCodigo;
int ponteiroEntrada;
int ponteiro;
string out;
string entrada;
string codigo;
stack<int> lastPonteiro;

void executa(char instrucao) {
	if (instrucao == '>') {
		ponteiro++;
	} else if (instrucao == '<') {
		ponteiro--;
	} else if (instrucao == '+') {
		bytes[ponteiro]++;
	} else if (instrucao == '-') {
		bytes[ponteiro]--;
	} else if (instrucao == '.') {
		out += bytes[ponteiro];
	} else if (instrucao == ',') {
		int tam = entrada.size();
		if (ponteiroEntrada < tam) {
			bytes[ponteiro] = entrada[ponteiroEntrada++];
		} else {
			bytes[ponteiro] = 0;
		}
	} else if (instrucao == '[') {
		if (bytes[ponteiro] == 0) {
			int quantos = 1;
			while (quantos > 0) {
				char c = codigo[ponteiroCodigo];
				if (c == ']') {
					quantos--;
				} else if (c == '[') {
					quantos++;
				}
				ponteiroCodigo++;
			}
		} else {
			lastPonteiro.push(ponteiroCodigo - 1);
		}
	} else if (instrucao == ']') {
		if (bytes[ponteiro] == 0) {
			lastPonteiro.pop();
		} else {
			ponteiroCodigo = lastPonteiro.top();
			lastPonteiro.pop();
		}
	} else if (instrucao == '#') {
		for (int i = 0; i < 10; i++) {
			out += bytes[i];
		}
	}
}

void compila() {
	int tam = codigo.size();
	while (ponteiroCodigo < tam) {
		executa(codigo[ponteiroCodigo++]);
	}
}

int main() {
	int casos;
	cin >> casos;
	for (int c = 1; c <= casos; c++) {
		char in[100000];
		char code[100000];

		while (true) {
			cin.getline(in, 100000, '\n');
			if (in[0] != '\0' && in[0] != ' ' && in[0] != '\r' && in[0] != '\n') {
				break;
			}
		}
		entrada = in;
		cin.getline(code, 100000, '\n');
		codigo = code;

		if (c > 1) {
			memset(bytes, 0, sizeof(bytes));
		}

		ponteiro = ponteiroCodigo = ponteiroEntrada = 0;

		out = "";

		compila();

		cout << "Instancia " << c << "\n" << out << endl << endl;
	}

	return 0;
}
