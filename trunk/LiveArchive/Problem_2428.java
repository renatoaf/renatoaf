import java.util.StringTokenizer;
import java.util.Vector;

class Problem_2428 {
	static String formata(double d) {
		String s = d + "";
		int posVirgula = s.indexOf(".");
		String apos = s.substring(posVirgula + 1);
		while (apos.length() < 2) {
			apos = apos + "0";
		}
		while (apos.length() > 2) {
			apos = apos.substring(0, apos.length() - 1);
		}
		return s.substring(0, posVirgula + 1) + apos;
	}

	static String readln() {
		String newLine = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();
		int car = -1;
		try {
			car = System.in.read();
			while ((car > 0) && (car != newLine.charAt(0))) {
				buffer.append((char) car);
				car = System.in.read();
			}
			if (car == newLine.charAt(0))
				System.in.skip(newLine.length() - 1);
		} catch (java.io.IOException e) {
			return (null);
		}
		if ((car < 0) && (buffer.length() == 0))
			return (null); /* eof */
		if (buffer.length() == 0)
			return ""; // string vazia
		return (buffer.toString()).trim();
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();

		int casos = Integer.parseInt(readln());

		for (int c = 1; c <= casos; c++) {
			if (c != 1) {
				saida.append("\n");
			}

			readln();

			int nCisternas = Integer.parseInt(readln());

			Vector cisternas = new Vector();
			double volumeMaximo = 0.0;
			double menorAltura = Double.MAX_VALUE;
			for (int i = 0; i < nCisternas; i++) {
				StringTokenizer config = new StringTokenizer(readln());
				double base = new Double(config.nextToken()).doubleValue();
				double alturaMax = new Double(config.nextToken()).doubleValue();
				double largura = new Double(config.nextToken()).doubleValue();
				double profundidade = new Double(config.nextToken())
						.doubleValue();

				if (alturaMax < menorAltura) {
					menorAltura = alturaMax;
				}

				Cisterna cist = new Cisterna(largura, profundidade, alturaMax,
						base);
				cisternas.addElement(cist);
				volumeMaximo += cist.volumeMax();
			}

			double volumeNecessario = new Double(readln()).doubleValue();
			if (volumeMaximo < volumeNecessario) {
				saida.append("OVERFLOW\n");
			} else if (volumeNecessario == 0.0) {
				saida.append("0.00\n");
			} else {
				double h = menorAltura;
				while (true) {
					double v = 0.0;

					for (int i = 0; i < cisternas.size(); i++) {
						Cisterna cist = (Cisterna) cisternas.elementAt(i);
						if (h >= cist.base) {
							v += cist.getVolume(h);
						}
					}

					if (v >= volumeNecessario) {
						break;
					}

					h += 0.001;
				}

				saida.append(formata(h) + "\n");
			}
		}

		System.out.print(saida);
	}
}

class Cisterna {
	double largura;
	double comprimento;
	double alturaMaxima;
	double base;

	public Cisterna(double l, double c, double h, double b) {
		this.largura = l;
		this.comprimento = c;
		this.alturaMaxima = h;
		this.base = b;
	}

	public double getVolume(double alturaAtual) {
		if (alturaAtual >= base + alturaMaxima) {
			return volumeMax();
		}
		return largura * comprimento * (alturaAtual - base);
	}

	public double volumeMax() {
		return largura * comprimento * alturaMaxima;
	}
}