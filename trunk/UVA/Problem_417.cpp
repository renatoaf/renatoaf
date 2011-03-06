/*
 * Word Index
 */

#include <stdio.h>
#include <string>
#include <queue>
#include <map>

using namespace std;

map<string, int> sol;
char in[5];

void bfs() {
	queue<string> q;

	q.push("");
	int ind = 1;
	while (!q.empty()) {
		string atual = q.front();
		q.pop();

		int size = atual.size();
		if (size > 0) {
			if (size <= 5)
				sol[atual] = ind++;
			else
				continue;
		}

		char firstChild = size == 0 ? 'a' : atual[size - 1] + 1;
		for (char child = firstChild; child <= 'z'; child++) {
			string copy = atual;
			copy += child;
			q.push(copy);
		}
	}
}

int main() {
	bfs();
	while (scanf("%s", in) > 0)
		printf("%d\n", sol[string(in)]);
	return 0;
}
