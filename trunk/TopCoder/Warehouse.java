import java.util.ArrayList;
import java.util.Collections;

public class Warehouse {
	public static int feetWide(int[] x, int[] y) {
		ArrayList<Ponto> pontos = new ArrayList<Ponto>();
		for (int i = 0; i < x.length; i++) {
			pontos.add(new Ponto(x[i], y[i]));
		}
		Ponto zero = new Ponto(0, 0);
		Ponto fim = new Ponto(200, 200);
		if (!pontos.contains(zero)) {
			pontos.add(zero);
		}
		if (!pontos.contains(fim)) {
			pontos.add(fim);
		}

		Collections.sort(pontos);

		System.out.println(pontos);
		int maxDist = 0;
		for (int i = 0; i < pontos.size() - 1; i++) {
			maxDist = Math.max(maxDist, pontos.get(i).dist(pontos.get(i + 1)));
		}
		return maxDist - 1;
	}

	public static void main(String[] args) {
		System.out.println(feetWide(new int[] { 100, 120 }, new int[] {
				60, 140 }));
	}
}

class Ponto implements Comparable<Ponto> {
	int x, y;

	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int compareTo(Ponto o) {
		return y - o.y;
	}

	public int dist(Ponto outro) {
		return Math.abs(y - outro.y);
	}

	public boolean equals(Object o) {
		if (!(o instanceof Ponto)) {
			return false;
		}
		Ponto p = (Ponto) o;
		return p.y == y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}