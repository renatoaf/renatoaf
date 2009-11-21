import java.util.Hashtable;
import java.util.StringTokenizer;

class Problem_3474 {
	@SuppressWarnings("unchecked")
	public static Hashtable correspondenciasDeMusicas = new Hashtable();

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

	static Integer nextInt(StringTokenizer tk) {
		return new Integer(Integer.parseInt(tk.nextToken()));
	}

	static Long nextLong(StringTokenizer tk) {
		return new Long(Long.parseLong(tk.nextToken()));
	}

	static String encontrarMusica(int nextInt, String[] musicas, int numMus) {
		int numElem = 1;
		int qntPorNumElem = qntCom(numElem, numMus);
		long total = qntPorNumElem;
		long totalAnt = 0;
		while (nextInt > total) {
			numElem++;
			qntPorNumElem = qntCom(numElem, numMus);
			totalAnt = total;
			total += qntPorNumElem;
		}
		long posNaQnt = nextInt - totalAnt;
		int posNoGrp = (int) (posNaQnt - 1) % numElem;
		int Grp = (int) (posNaQnt - 1) / numElem;

		String num = converter(Grp, numMus, numElem);
		String c = String.valueOf(num.charAt(posNoGrp));
		int posicaoFinal = ((Integer) correspondenciasDeMusicas.get(c))
				.intValue();

		return musicas[posicaoFinal];

	}

	static int qntCom(int numElem, int numMus) {
		return numElem * ((int) Math.pow(numMus, numElem));
	}

	static String converter(int Grp, int numMus, int numElem) {
		String num = Integer.toString(Grp, numMus);
		while (num.length() < numElem) {
			num = "0" + num;
		}
		return num;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		correspondenciasDeMusicas = new Hashtable();
		int val = 10;
		for (int i = 0; i <= 9; i++) {
			correspondenciasDeMusicas.put(String.valueOf(i), new Integer(i));
		}
		for (char i = 'a'; i <= 'z'; i++) {
			correspondenciasDeMusicas.put(String.valueOf(i), new Integer(val));
			val++;
		}
		while (true) {
			String linha = readln();
			StringTokenizer tk = new StringTokenizer(linha);
			int numMus = nextInt(tk).intValue();
			if (numMus == 0) {
				break;
			}
			int numPerg = nextInt(tk).intValue();

			linha = readln();
			tk = new StringTokenizer(linha);
			String[] musicas = new String[numMus];
			for (int i = 0; i < numMus; i++) {
				String pal = tk.nextToken();
				musicas[i] = pal;
			}
			linha = readln();
			tk = new StringTokenizer(linha);
			for (int i = 0; i < numPerg; i++) {
				String mus = encontrarMusica(nextInt(tk).intValue(), musicas,
						numMus);
				System.out.println(mus);
			}
			System.out.println("");
		}
	}
}