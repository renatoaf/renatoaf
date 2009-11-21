/*
 * Sending Email
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Vector;

class Problem_10986 {
	static final int INF = Integer.MAX_VALUE - 1;

	static int dijkstra(int fonte, int destino, Vector<Aresta>[] grafo, int n) {
		int[] custo = new int[n];
		Arrays.fill(custo, INF);
		PriorityQueue<Aresta> q = new PriorityQueue<Aresta>();
		q.add(new Aresta(fonte, 0));
		while (!q.isEmpty()) {
			int node = q.peek().destino;
			int pesoParcial = q.peek().peso;
			q.poll();
			if (pesoParcial > custo[node]) {
				continue;
			}
			if (node == destino) {
				break;
			}
			Vector<Aresta> adj = grafo[node];
			int tam = (adj == null) ? 0 : adj.size();
			for (int i = 0; i < tam; i++) {
				Aresta a = adj.get(i);
				if (pesoParcial + a.peso < custo[a.destino]) {
					custo[a.destino] = pesoParcial + a.peso;
					q.add(new Aresta(a.destino, custo[a.destino]));
				}
			}
		}
		return custo[destino];
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int casos = Integer.parseInt(in.readLine());

		for (int caso = 1; caso <= casos; caso++) {
			String[] entrada = in.readLine().split("\\s+");
			int n = Integer.parseInt(entrada[0]);
			int m = Integer.parseInt(entrada[1]);
			int S = Integer.parseInt(entrada[2]);
			int T = Integer.parseInt(entrada[3]);

			Vector<Aresta>[] grafo = new Vector[n];
			for (int i = 0; i < m; i++) {
				entrada = in.readLine().split("\\s+");
				int server1 = Integer.parseInt(entrada[0]);
				int server2 = Integer.parseInt(entrada[1]);
				int latencia = Integer.parseInt(entrada[2]);

				if (grafo[server1] == null) {
					grafo[server1] = new Vector<Aresta>();
				}
				if (grafo[server2] == null) {
					grafo[server2] = new Vector<Aresta>();
				}
				grafo[server1].add(new Aresta(server2, latencia));
				grafo[server2].add(new Aresta(server1, latencia));
			}
			int menorCusto = dijkstra(S, T, grafo, n);
			saida.append("Case #" + caso + ": "
					+ (menorCusto != INF ? menorCusto : "unreachable")
					+ separador);
		}

		System.out.print(saida);
	}
}

class Aresta implements Comparable<Aresta> {
	int destino;
	int peso;

	public Aresta(int d, int p) {
		this.destino = d;
		this.peso = p;
	}

	public int compareTo(Aresta o) {
		if (this.peso < o.peso) {
			return -1;
		} else if (this.peso > o.peso) {
			return 1;
		}
		return 0;
	}
}