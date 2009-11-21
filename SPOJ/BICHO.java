import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

class BICHO {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("0.00");
		DecimalFormat df2 = new DecimalFormat("0000");
		DecimalFormatSymbols pt = new DecimalFormatSymbols();
		pt.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(pt);

		int grp = 1;
		HashMap<Integer, Integer> grupo = new HashMap<Integer, Integer>();
		for (int i = 1; i <= 99; i += 4) {
			for (int j = i; j < i + 4; j++) {
				if (j == 100) {
					j = 0;
					grupo.put(j, grp);
					break;
				}
				grupo.put(j, grp);
			}
			grp++;
		}

		while (true) {
			StringTokenizer tok = new StringTokenizer(in.nextLine());

			double v = Double.parseDouble(tok.nextToken());
			int n = Integer.parseInt(tok.nextToken());
			int m = Integer.parseInt(tok.nextToken());

			if (v == 0 && n == 0 && m == 0) {
				break;
			}

			String apost = df2.format(n);
			String sorteado = df2.format(m);
			int t1 = apost.length();
			int t2 = sorteado.length();

			double valor = 0.0;
			if (apost.charAt(t1 - 1) == sorteado.charAt(t2 - 1)
					&& apost.charAt(t1 - 2) == sorteado.charAt(t2 - 2)
					&& apost.charAt(t1 - 3) == sorteado.charAt(t2 - 3)
					&& apost.charAt(t1 - 4) == sorteado.charAt(t2 - 4)) {
				valor = v * 3000.0;
			} else if (apost.charAt(t1 - 1) == sorteado.charAt(t2 - 1)
					&& apost.charAt(t1 - 2) == sorteado.charAt(t2 - 2)
					&& apost.charAt(t1 - 3) == sorteado.charAt(t2 - 3)) {
				valor = v * 500.0;
			} else if (apost.charAt(t1 - 1) == sorteado.charAt(t2 - 1)
					&& apost.charAt(t1 - 2) == sorteado.charAt(t2 - 2)) {
				valor = v * 50.0;
			} else {
				int grupo1 = Integer.parseInt(apost.substring(t1 - 2));
				int grupo2 = Integer.parseInt(sorteado.substring(t2 - 2));
				grupo1 = grupo.get(grupo1);
				grupo2 = grupo.get(grupo2);
				if (grupo1 == grupo2) {
					valor = v * 16.0;
				}
			}

			System.out.println(df.format(valor));
		}
	}
}
