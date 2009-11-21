/*
 * Nao foi aceito 
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class I_ConjPotencia {
	static ArrayList<ArrayList<Integer>> conjPartes(ArrayList<Integer> l) {
		ArrayList<ArrayList<Integer>> p = new ArrayList<ArrayList<Integer>>();
		p.add(new ArrayList<Integer>());
		for (Iterator<Integer> i = l.iterator(); i.hasNext();) {
			Integer x = (Integer) i.next();
			ArrayList<ArrayList<Integer>> q = new ArrayList<ArrayList<Integer>>();
			for (Iterator<ArrayList<Integer>> j = p.iterator(); j.hasNext();) {
				ArrayList<Integer> y = (ArrayList<Integer>) j.next();
				ArrayList<Integer> z = new ArrayList<Integer>();
				for (Iterator<Integer> k = y.iterator(); k.hasNext();) {
					z.add((Integer) k.next());
				}
				z.add(x);
				q.add(z);
			}
			p.addAll(q);
		}
		return p;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int casos = in.nextInt();
		for (int c = 1; c <= casos; c++) {
			int n = in.nextInt();
			ArrayList<Integer> conj = new ArrayList<Integer>();
			for (int i = 0; i < n; i++) {
				conj.add(in.nextInt());
			}
			ArrayList<ArrayList<Integer>> conjPartes = conjPartes(conj);

			int count = -1;
			for (ArrayList<Integer> arr : conjPartes) {
				int dif = -1;

				boolean ehPA = true;
				for (int j = 1; j < arr.size(); j++) {
					int difAtual = arr.get(j) - arr.get(j - 1);
					if (j == 1) {
						dif = difAtual;
					} else {
						if (difAtual != dif) {
							ehPA = false;
							break;
						}
					}
				}

				if (ehPA) {
					count++;
				}
			}

			System.out.println("Case " + c + ": " + count);
		}

	}
}
