import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Problem_476 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		ArrayList<Rec> figuras = new ArrayList<Rec>();

		while (true) {
			String linha = in.readLine();
			if (linha.equals("*")) {
				break;
			}
			String[] config = linha.split("\\s+");

			double x1 = Double.parseDouble(config[1]);
			double y1 = Double.parseDouble(config[2]);
			double x2 = Double.parseDouble(config[3]);
			double y2 = Double.parseDouble(config[4]);
			double w = x2 - x1;
			double h = y1 - y2;
			figuras.add(new Rec(x1, y1, w, h));
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
				Rec r = figuras.get(i);
				if (r.contains(x, y)) {
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

class Rec {
	double x;
	double y;
	double w;
	double h;

	public Rec(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public boolean contains(double x, double y) {
		return (x > this.x && x < this.x + w) && (y < this.y && y > this.y - h);
	}
}