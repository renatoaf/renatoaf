import java.util.StringTokenizer;

class ELETRICI {
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
			int n = Integer.parseInt(readln());
			if (n == 0) {
				break;
			}
			Data[] datas = new Data[n];
			for (int i = 0; i < n; i++) {
				StringTokenizer config = new StringTokenizer(readln());
				int d = Integer.parseInt(config.nextToken());
				int m = Integer.parseInt(config.nextToken());
				int a = Integer.parseInt(config.nextToken());
				int c = Integer.parseInt(config.nextToken());
				datas[i] = new Data(d, m, a, c);
			}

			int qt = 0;
			int soma = 0;
			for (int i = 1; i < n; i++) {
				Data dAnt = datas[i - 1];
				Data dAtual = datas[i];
				if (dAtual.ehSucessor(dAnt)) {
					qt++;
					soma += (dAtual.consumo - dAnt.consumo);
				}
			}

			saida.append(qt + " " + soma + "\n");
		}

		System.out.print(saida);
	}
	/*
	 * public static void main(String[] args) { int[] resps = new int[201]; for
	 * (int i = 1900; i <= 2100; i++) { resps[i - 1900] = new Data(0, 0, i,
	 * 0).diasPassados; } System.out.println(Arrays.toString(resps)); }/ }
	 */
}

class Data { /* pre calculado, tempo spoj */
	final int[] anos = { 693608, 693974, 694339, 694704, 695069, 695435,
			695800, 696165, 696530, 696896, 697261, 697626, 697991, 698357,
			698722, 699087, 699452, 699818, 700183, 700548, 700913, 701279,
			701644, 702009, 702374, 702740, 703105, 703470, 703835, 704201,
			704566, 704931, 705296, 705662, 706027, 706392, 706757, 707123,
			707488, 707853, 708218, 708584, 708949, 709314, 709679, 710045,
			710410, 710775, 711140, 711506, 711871, 712236, 712601, 712967,
			713332, 713697, 714062, 714428, 714793, 715158, 715523, 715889,
			716254, 716619, 716984, 717350, 717715, 718080, 718445, 718811,
			719176, 719541, 719906, 720272, 720637, 721002, 721367, 721733,
			722098, 722463, 722828, 723194, 723559, 723924, 724289, 724655,
			725020, 725385, 725750, 726116, 726481, 726846, 727211, 727577,
			727942, 728307, 728672, 729038, 729403, 729768, 730133, 730499,
			730864, 731229, 731594, 731960, 732325, 732690, 733055, 733421,
			733786, 734151, 734516, 734882, 735247, 735612, 735977, 736343,
			736708, 737073, 737438, 737804, 738169, 738534, 738899, 739265,
			739630, 739995, 740360, 740726, 741091, 741456, 741821, 742187,
			742552, 742917, 743282, 743648, 744013, 744378, 744743, 745109,
			745474, 745839, 746204, 746570, 746935, 747300, 747665, 748031,
			748396, 748761, 749126, 749492, 749857, 750222, 750587, 750953,
			751318, 751683, 752048, 752414, 752779, 753144, 753509, 753875,
			754240, 754605, 754970, 755336, 755701, 756066, 756431, 756797,
			757162, 757527, 757892, 758258, 758623, 758988, 759353, 759719,
			760084, 760449, 760814, 761180, 761545, 761910, 762275, 762641,
			763006, 763371, 763736, 764102, 764467, 764832, 765197, 765563,
			765928, 766293, 766658 };

	int dia;
	int mes;
	int ano;
	int consumo;

	int diasPassados;

	public Data(int d, int m, int a, int c) {
		this.dia = d;
		this.mes = m;
		this.ano = a;
		this.consumo = c;

		diasPassados = anos[ano - 1900];
		for (int i = 1; i < mes; i++) {
			diasPassados += nDias(i, ano);
		}
		diasPassados += dia;
	}

	public boolean ehSucessor(Data d) {
		return Math.abs(d.diasPassados - diasPassados) <= 1;
	}

	boolean bissexto(int ano) {
		return (ano % 4 == 0 && ano % 1000 != 0) || ano % 400 == 0;
	}

	int nDias(int mes, int ano) {
		if (mes == 2 && bissexto(ano)) {
			return 29;
		} else {
			switch (mes) {
			case 1:
				return 31;
			case 2:
				return 28;
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
			}
		}
		return 30;
	}
}