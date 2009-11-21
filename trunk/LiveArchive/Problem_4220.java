import java.util.StringTokenizer;

class Problem_4220 {
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

	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();

		while (true) {
			int N = Integer.parseInt(readln());

			if (N == 0) {
				break;
			}

			int[] arcos = new int[N];
			int perimetroTotal = 0;

			StringTokenizer config = new StringTokenizer(readln());
			for (int i = 0; i < N; i++) {
				arcos[i] = Integer.parseInt(config.nextToken());
				perimetroTotal += arcos[i];
			}

			boolean[] temPonto = new boolean[perimetroTotal];

			// trata o poligono como uma "reta", do tamanho do perimetro, onde
			// eh true, tem um ponto
			int ponto = 0;
			for (int i = 0; i < N; i++) {
				temPonto[ponto] = true;
				ponto += arcos[i];
			}

			int melhorSol = -1;

			// tenta formar um poligono regular usando o maior numero possivel
			ITER: for (int i = N; i >= 3; i--) {
				if (perimetroTotal % i == 0) { // eh possivel usar i vertices
					int tamLadoSuposto = (perimetroTotal / i);
					for (int j = 0; j < tamLadoSuposto; j++) {
						boolean possivel = true;
						int k = j;

						// testa se eh possivel atingir os pontos usando esse
						// tamanho de lado suposto
						while (k < perimetroTotal) {
							if (!temPonto[k]) {
								possivel = false;
								break;
							}
							k += tamLadoSuposto;
						}

						if (possivel) {
							melhorSol = i;
							break ITER;
						}
					}
				}
			}

			int minimoCortes = (melhorSol == -1) ? -1 : N - melhorSol;
			saida.append(minimoCortes + "\n");
		}

		System.out.print(saida);
	}
}