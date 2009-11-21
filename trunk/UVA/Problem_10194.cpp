/*
 * Football (aka Soccer)
 */

#include <vector>
#include <map>
#include <string>
#include <stdio.h>

using namespace std;

char nomeTorneio[100];
char jogo[100];
char nomeTime[30];
char nomeTime1[30];
char nomeTime2[30];
char placar1[30];
char placar2[30];

string lower(string s) {
	string out = "";
	int tam = s.size();
	for (int i = 0; i < tam; i++) {
		out += (tolower(s[i]));
	}
	return out;
}

struct Time {
	string nome, lowerName;
	int empates, derrotas, vitorias, jogos, golsSofridos, golsMarcados, pontos;

	Time() {

	}

	Time(string name) {
		empates = derrotas = vitorias = jogos = golsSofridos = golsMarcados
				= pontos = 0;
		nome = name;
		lowerName = lower(name);
	}
};

int compare(const void * a, const void* b) {
	Time *ta = (Time*) a;
	Time *tb = (Time*) b;
	int saldoA = (ta->golsMarcados - ta->golsSofridos);
	int saldoB = (tb->golsMarcados - tb->golsSofridos);
	if (ta->pontos != tb->pontos) {
		return tb->pontos - ta->pontos;
	}
	if (tb->vitorias != ta->vitorias) {
		return tb->vitorias - ta->vitorias;
	}
	if (saldoA != saldoB) {
		return saldoB - saldoA;
	}
	if (tb->golsMarcados != ta->golsMarcados) {
		return tb->golsMarcados - ta->golsMarcados;
	}
	if (ta->jogos != tb->jogos) {
		return ta->jogos - tb->jogos;
	}
	return ta->lowerName.compare(tb->lowerName);
}

int main() {
	Time arrayTimes[1000];
	int casos, nTimes, jogos, golsCasa, golsVis;
	string nome, nome1, nome2;

	map<string, Time>::iterator it;

	scanf("%d\n", &casos);
	for (int c = 0; c < casos; c++) {
		if (c > 0)
			puts("");

		gets(nomeTorneio);

		scanf("%d\n", &nTimes);

		map<string, Time> times;

		for (int i = 0; i < nTimes; i++) {
			gets(nomeTime);
			string nomeDoTime = string(nomeTime);
			times[nomeDoTime] = Time(nomeDoTime);
		}

		scanf("%d\n", &jogos);
		for (int i = 0; i < jogos; i++) {
			gets(jogo);
			char *team1 = strtok(jogo, "#");
			char *goals1_aux = strtok(NULL, "@");
			golsCasa = atoi(goals1_aux);
			char *goals2_aux = strtok(NULL, "#");
			golsVis = atoi(goals2_aux);
			char *team2 = strtok(NULL, "");

			Time casa = times[string(team1)];
			Time vis = times[string(team2)];

			casa.golsMarcados += golsCasa;
			casa.golsSofridos += golsVis;
			vis.golsMarcados += golsVis;
			vis.golsSofridos += golsCasa;

			if (golsCasa == golsVis) {
				casa.empates++;
				casa.pontos += 1;
				vis.empates++;
				vis.pontos += 1;
			} else if (golsCasa > golsVis) {
				casa.vitorias++;
				casa.pontos += 3;
				vis.derrotas++;
			} else {
				casa.derrotas++;
				vis.pontos += 3;
				vis.vitorias++;
			}

			casa.jogos++;
			vis.jogos++;

			times[string(team1)] = casa;
			times[string(team2)] = vis;
		}

		int i = 0;
		for (it = times.begin(); it != times.end(); it++) {
			arrayTimes[i++] = (*it).second;
		}
		qsort(arrayTimes, nTimes, sizeof(Time), compare);

		puts(nomeTorneio);
		for (int i = 0; i < nTimes; i++) {
			Time team = arrayTimes[i];
			printf("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)\n", (i + 1),
					team.nome.c_str(), team.pontos, team.jogos, team.vitorias,
					team.empates, team.derrotas, (team.golsMarcados
							- team.golsSofridos), team.golsMarcados,
					team.golsSofridos);
		}
	}

	return 0;
}
