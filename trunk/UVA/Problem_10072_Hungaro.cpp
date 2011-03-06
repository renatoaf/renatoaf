/*
 * Bob Laptop Woolmer
 */

#include <stdio.h>
#include <string.h>
#include <vector>
#include <cmath>

using namespace std;

#define NN 200
#define INF 10000000
#define EPS 1e-7

/* algoritmo hungaro */

bool S[NN], T[NN]; // sets S and T in algorithm
int cost[NN][NN]; // cost matrix
int lx[NN], ly[NN]; // labels of X and Y parts
int xy[NN]; // xy[x] - vertex that is matched with x
int yx[NN]; // yx[y] - vertex that is matched with y
int slack[NN]; // as in the algorithm description
int slackx[NN]; // l(slackx[y]) + l(y) - w(slackx[y],y) = slack[y]
int prev[NN];

int N, max_match; // n workers and n jobs

int min(int a, int b) {
	return a < b ? a : b;
}

int max(int a, int b) {
	return a > b ? a : b;
}

void add_to_tree(int x, int prevx) {
	// x - current vertex, prevx - vertex from X before x in the alternating path,
	// so we add edges (prevx, xy[x]), (xy[x], x)
	S[x] = true; // add x to S
	prev[x] = prevx; // we need this when augmenting
	for (int y = 0; y < N; y++)
		//update slacks, because we add new vertex to S
		if (lx[x] + ly[y] - cost[x][y] < slack[y]) {
			slack[y] = lx[x] + ly[y] - cost[x][y];
			slackx[y] = x;
		}
}
void update_labels() {
	int x, y, delta = INF; // init delta as infinity
	for (y = 0; y < N; y++) // calculate delta using slack
		if (!T[y])
			delta = min(delta, slack[y]);
	for (x = 0; x < N; x++) // update X labels
		if (S[x])
			lx[x] -= delta;
	for (y = 0; y < N; y++) // update Y labels
		if (T[y])
			ly[y] += delta;
	for (y = 0; y < N; y++) // update slack array
		if (!T[y])
			slack[y] -= delta;
}

void augment() { //main function of the algorithm
	if (max_match == N)
		return; // check wether matching is already perfect
	int x, y, root; // just counters and root vertex
	int q[NN], wr = 0, rd = 0; // q - queue for bfs, wr,rd - write and read pos in queue
	memset(S, false, sizeof(S)); // init set S
	memset(T, false, sizeof(T)); // init set T
	memset(prev, -1, sizeof(prev)); // init set prev - for the alternating tree
	for (x = 0; x < N; x++) //finding root of the tree
		if (xy[x] == -1) {
			q[wr++] = root = x;
			prev[x] = -2;
			S[x] = true;
			break;
		}
	for (y = 0; y < N; y++) { // initializing slack array
		slack[y] = lx[root] + ly[y] - cost[root][y];
		slackx[y] = root;
	}
	while (true) { // main cycle
		while (rd < wr) { // building tree with bfs cycle
			x = q[rd++]; // current vertex from X part
			for (y = 0; y < N; y++) // iterate through all edges in equality graph
				if (cost[x][y] == lx[x] + ly[y] && !T[y]) {
					if (yx[y] == -1)
						break; // an exposed vertex in Y found, so augmenting path exists!
					T[y] = true; // else just add y to T,
					q[wr++] = yx[y]; // add vertex yx[y], which is matched with y, to the queue
					add_to_tree(yx[y], x); // add edges (x,y) and (y,yx[y]) to the tree
				}
			if (y < N)
				break; // augmenting path found!
		}
		if (y < N)
			break; // augmenting path found!
		update_labels(); // augmenting path not found, so improve labeling
		wr = rd = 0;
		for (y = 0; y < N; y++)
			//in this cycle we add edges that were added to the equality graph as a
			//result of improving the labeling, we add edge (slackx[y], y) to the tree if
			//and only if !T[y] &&  slack[y] == 0, also with this edge we add another one
			//(y, yx[y]) or augment the matching, if y was exposed
			if (!T[y] && slack[y] == 0) {
				if (yx[y] == -1) {//exposed vertex in Y found - augmenting path exists!
					x = slackx[y];
					break;
				} else {
					T[y] = true; //else just add y to T,
					if (!S[yx[y]]) {
						q[wr++] = yx[y]; //add vertex yx[y], which is matched with
						//y, to the queue
						add_to_tree(yx[y], slackx[y]); //and add edges (x,y) and (y,
						//yx[y]) to the tree
					}
				}
			}
		if (y < N)
			break; // augmenting path found!
	}
	if (y < N) { // we found augmenting path!
		max_match++; //increment matching
		//in this cycle we inverse edges along augmenting path
		for (int cx = x, cy = y, ty; cx != -2; cx = prev[cx], cy = ty) {
			ty = xy[cx];
			yx[cy] = cx;
			xy[cx] = cy;
		}
		augment(); //recall function, go to step 1 of the algorithm
	}
}

void init_labels() {
	memset(lx, 0, sizeof(lx));
	memset(ly, 0, sizeof(ly));
	for (int x = 0; x < N; x++)
		for (int y = 0; y < N; y++)
			lx[x] = max(lx[x], cost[x][y]);
}

long long hungarian() {
	long long ret = 0; // weight of the optimal matching
	max_match = 0; // number of vertices in current matching
	memset(xy, -1, sizeof(xy));
	memset(yx, -1, sizeof(yx));
	init_labels(); // step 0
	augment(); // steps 1-3
	for (int x = 0; x < N; x++)
		ret += cost[x][xy[x]]; // forming answer there
	return ret;
}

/* questao */

int btscore[NN], blscore[NN], arscore[NN];

int batsmanScore(int batting, int bowling, int fielding) {
	return (int) round(0.8 * batting + 0.2 * fielding + EPS);
}

int bowlerScore(int batting, int bowling, int fielding) {
	return (int) round(0.7 * bowling + 0.1 * batting + 0.2 * fielding + EPS);
}

int allRounderScore(int batting, int bowling, int fielding) {
	return (int) round(0.4 * batting + 0.4 * bowling + 0.2 * fielding + EPS);
}

int main() {
	int BT, BL, AR, bt, bl, fl, caso = 1;

	while (1) {
		scanf("%d", &N);

		if (N == 0)
			break;
		else if (caso > 1)
			printf("\n");

		memset(btscore, 0, sizeof(btscore));
		memset(blscore, 0, sizeof(blscore));
		memset(arscore, 0, sizeof(arscore));
		memset(cost, 0, sizeof(cost));

		for (int i = 0; i < N; i++) {
			scanf("%d %d %d", &bt, &bl, &fl);
			btscore[i] = batsmanScore(bt, bl, fl);
			blscore[i] = bowlerScore(bt, bl, fl);
			arscore[i] = allRounderScore(bt, bl, fl);
		}

		scanf("%d %d %d", &BT, &BL, &AR);
		int limBT = BT;
		int limBL = BT + BL;
		int limAR = BT + BL + AR;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < limBT; j++)
				cost[i][j] = btscore[i];
			for (int j = limBT; j < limBL; j++)
				cost[i][j] = blscore[i];
			for (int j = limBL; j < limAR; j++)
				cost[i][j] = arscore[i];
		}

		vector<int> batsmen;
		vector<int> bowlers;
		vector<int> all_rounders;

		int MAXIMO_SCORE = hungarian();
		for (int i = 0; i < N; i++) {
			if (xy[i] < limBT)
				batsmen.push_back(i + 1);
			else if (xy[i] < limBL)
				bowlers.push_back(i + 1);
			else if (xy[i] < limAR)
				all_rounders.push_back(i + 1);
		}

		printf("Team #%d", caso++);
		printf("\nMaximum Effective Score = %d", MAXIMO_SCORE);
		printf("\nBatsmen :");
		for (int i = 0; i < BT; i++)
			printf(" %d", batsmen[i]);
		printf("\nBowlers :");
		for (int i = 0; i < BL; i++)
			printf(" %d", bowlers[i]);
		printf("\nAll-rounders :");
		for (int i = 0; i < AR; i++)
			printf(" %d", all_rounders[i]);
		printf("\n");
	}

	return 0;
}
