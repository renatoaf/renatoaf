import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class Problem_2006 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		while (true) {
			Hashtable frequencias = new Hashtable();
			Vector ordem = new Vector();

			String palavra = "";

			while (true) {
				char c = (char) System.in.read();

				if (c == '#') {
					break;
				} else if (!Character.isLetter(c)) {
					if (palavra.length() > 0) {
						String p = palavra.toLowerCase();
						Cont cont = (Cont) frequencias.get(p);
						if (cont == null) {
							cont = new Cont();
							frequencias.put(p, cont);
						}
						cont.c++;
						ordem.addElement(p);
					}
					palavra = "";
				} else {
					palavra += c;
				}
			}

			if (frequencias.size() == 0) {
				break;
			}

			int maiorFreq = 0;
			int ultimoIndice = -1;
			String palavraMaisFreq = "";
			for (Enumeration e = frequencias.keys(); e.hasMoreElements();) {
				String p = (String) e.nextElement();
				int freq = ((Cont) frequencias.get(p)).c;
				if (freq > maiorFreq) {
					maiorFreq = freq;
					palavraMaisFreq = p;
					ultimoIndice = ordem.lastIndexOf(p);
				} else if (freq == maiorFreq) {
					int ind = ordem.lastIndexOf(p);
					if (ind < ultimoIndice) {
						ultimoIndice = ind;
						palavraMaisFreq = p;
					}
				}
			}

			String maisFreq = maiorFreq + "";
			while (maisFreq.length() < 4) {
				maisFreq = " " + maisFreq;
			}
			System.out.println(maisFreq + " " + palavraMaisFreq);
		}
	}
}

class Cont {
	int c = 0;
}