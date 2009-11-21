import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class flow {
	public static class MaxFlow {
		public final static int INF = 0X3F3F3F3F;

		public List<Vertex> graph;

		public class Vertex {
			public List<Edge> edges = new ArrayList<Edge>();
			public boolean visited = false;
			// // For BFS and MinCost version ////
			public int bottleNeck = 0;
			public int id;
			// ///////////////////////////////////
			// ///// For Dinic's Algorithm ///////
			public int dist;

			// ///////////////////////////////////
			public Vertex() {
				graph.add(this);
				// // For BFS and MinCost version ////
				id = graph.size() - 1;
				// ///////////////////////////////////
				// ///// For Dinic's Algorithm ///////
				dist = INF;
				// ///////////////////////////////////
			}
		}

		public class Edge {
			public Vertex src;
			public Vertex dst;
			public Edge rev;
			public int cap;
			public int flow;

			public Edge(Vertex src, Vertex dst, int cap) {
				this.src = src;
				this.dst = dst;
				this.cap = cap;
				this.flow = 0;
				this.src.edges.add(this);
				new Edge(this);
			}

			public Edge(Edge toRev) {
				this.src = toRev.dst;
				this.dst = toRev.src;
				this.cap = 0;
				this.flow = 0;
				this.rev = toRev;
				toRev.rev = this;
				this.src.edges.add(this);
			}
		}

		public MaxFlow() {
			graph = new ArrayList<Vertex>();
		}

		private Edge[] prevEdge;

		private void BFS(Vertex s, Vertex t) {
			Queue<Vertex> q = new LinkedList<Vertex>();
			q.add(s);
			s.bottleNeck = INF;
			prevEdge[s.id] = null;

			while (!q.isEmpty()) {
				Vertex act = q.poll();
				if (act == t)
					break;
				for (Edge e : act.edges) {
					if (e.cap - e.flow == 0)
						continue;
					if (e.dst.bottleNeck != -INF)
						continue;
					q.add(e.dst);
					e.dst.bottleNeck = Math.min(act.bottleNeck, e.cap - e.flow);
					// ///// For Dinic's Algorithm ///////
					e.dst.dist = act.dist + 1;
					// ///////////////////////////////////
					prevEdge[e.dst.id] = e;
				}
			}
		}

		// ////////////// Dinic's Implementation... ///////////////////
		private int DinicDFS(Vertex s, Vertex t, int cap) {
			if (cap == 0)
				return 0;
			if (s == t)
				return cap;
			int ret = 0;
			for (Edge e : s.edges) {
				if (e.dst.dist == s.dist + 1 && e.cap - e.flow > 0) {
					int tadd = DinicDFS(e.dst, t, Math.min(e.cap - e.flow, cap));
					ret += tadd;
					cap -= tadd;
					e.flow += tadd;
					e.rev.flow -= tadd;
					if (cap == 0)
						break;
				}
			}
			return ret;
		}

		public int MaxFlowDinic(Vertex s, Vertex t) {
			prevEdge = new Edge[graph.size()];
			int result = 0;
			while (true) {
				for (Vertex v : graph)
					v.bottleNeck = -INF;
				BFS(s, t);

				if (t.bottleNeck == -INF)
					break;

				result += DinicDFS(s, t, INF);
			}
			return result;
		}

		// ////////////////////////////////////////////////////////////

		// //////// All Pairs MaxFlow. Gusfield's Algorithm ///////////
		// ////////// Only works with undirected graphs. //////////////
		public int[][] AllPairsMaxFlow() {
			int N = graph.size();
			int[] parent = new int[N];
			int[][] res = new int[N][N];
			Arrays.fill(parent, 0);
			for (int i = 0; i < N; ++i) {
				Arrays.fill(res[i], INF);
			}

			for (int i = 1; i < N; ++i) {
				for (Vertex v : graph)
					for (Edge e : v.edges)
						e.flow = 0;
				int F = MaxFlowDinic(graph.get(i), graph.get(parent[i]));

				for (int j = i + 1; j < N; ++j) {
					if (graph.get(j).bottleNeck != -INF
							&& parent[j] == parent[i]) {
						parent[j] = i;
					}
				}
				res[i][parent[i]] = res[parent[i]][i] = F;
				for (int j = 0; j < i; ++j) {
					res[i][j] = res[j][i] = Math.min(F, res[i][j]);
				}
			}

			return res;
		}
		// ////////////////////////////////////////////////////////////
	}

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		int cases = Integer.parseInt(reader.readLine());
		for (int cc = 0; cc < cases; ++cc) {

			int N = Integer.parseInt(reader.readLine());
			MaxFlow mf = new MaxFlow();
			for (int i = 0; i < N; ++i)
				mf.new Vertex();
			for (int i = 0; i < N; ++i) {
				StringTokenizer st = new StringTokenizer(reader.readLine());
				for (int j = 0; j < N; ++j) {
					int ff = Integer.parseInt(st.nextToken());
					if (i == j)
						continue;
					mf.new Edge(mf.graph.get(i), mf.graph.get(j), ff);
				}
			}

			int[][] res = mf.AllPairsMaxFlow();
			System.out.println("Case #" + (cc + 1) + ":");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j != 0)
						System.out.print(" ");
					if (i == j)
						System.out.print("0");
					else
						System.out.print(res[i][j]);
				}
				System.out.println();
			}
		}
	}

}
