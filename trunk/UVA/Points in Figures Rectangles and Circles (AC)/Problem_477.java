import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_477 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		ArrayList<Fig> figuras = new ArrayList<Fig>();

		while (true) {
			String linha = in.readLine();
			if (linha.equals("*")) {
				break;
			}
			String[] config = linha.split("\\s+");

			if (config[0].equals("r")) {
				double x1 = Double.parseDouble(config[1]);
				double y1 = Double.parseDouble(config[2]);
				double x2 = Double.parseDouble(config[3]);
				double y2 = Double.parseDouble(config[4]);
				double w = x2 - x1;
				double h = y1 - y2;
				figuras.add(new Retangulo(x1, y1, w, h));
			} else {
				double x = Double.parseDouble(config[1]);
				double y = Double.parseDouble(config[2]);
				double r = Double.parseDouble(config[3]);
				figuras.add(new Circulo(x, y, r));
			}
		}

		int p = 1;
		while (true) {
			String[] config = in.readLine().split("\\s+");
			double x = Double.parseDouble(config[0]);
			double y = Double.parseDouble(config[1]);

			if (x == 9999.9 && y == 9999.9) {
				break;
			}

			boolean achou = false;
			for (int i = 0; i < figuras.size(); i++) {
				Fig f = figuras.get(i);
				if (f.contains(x, y)) {
					saida.append("Point " + p + " is contained in figure "
							+ (i + 1) + separador);
					achou = true;
				}
			}

			if (!achou) {
				saida.append("Point " + p + " is not contained in any figure"
						+ separador);
			}

			p++;
		}

		System.out.print(saida);
	}
}

interface Fig {
	boolean contains(double x, double y);
}

class Retangulo implements Fig {
	double x;
	double y;
	double w;
	double h;

	public Retangulo(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean contains(double x, double y) {
		return (x >= this.x && x <= this.x + w)
				&& (y <= this.y && y >= this.y - h);
	}
}

class Circulo implements Fig {
	double x;
	double y;
	double r;

	public Circulo(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public boolean contains(double x, double y) {
		double r2 = r * r;
		double xa = x - this.x;
		double yb = y - this.y;
		return (xa * xa) + (yb * yb) <= r2; // interno ou pertence
	}
}