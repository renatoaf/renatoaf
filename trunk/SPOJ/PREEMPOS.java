import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static String getSubstring(String s, int i, int j) {
		try {
			return s.substring(i, j + 1);
		} catch (StringIndexOutOfBoundsException e) {
			return "";
		}
	}

	static String getPosOrdem(String preOrdem, String inOrdem) {
		if (inOrdem.length() == 0)
			return "";
		else if (inOrdem.length() == 1)
			return inOrdem.charAt(0) + "";
		int indiceRaiz = getIndRaizInOrdem(preOrdem, inOrdem);
		String subEsq = getSubstring(inOrdem, 0, indiceRaiz - 1);
		String subDir = getSubstring(inOrdem, indiceRaiz + 1,
				inOrdem.length() - 1);
		String raiz = inOrdem.charAt(indiceRaiz) + "";
		return getPosOrdem(preOrdem, subEsq) + getPosOrdem(preOrdem, subDir)
				+ raiz;
	}

	private static int getIndRaizInOrdem(String preOrdem, String inOrdem) {
		for (int i = 0; i < preOrdem.length(); i++) {
			int possivelRaiz = inOrdem.indexOf(preOrdem.charAt(i));
			if (possivelRaiz != -1)
				return possivelRaiz;
		}
		return -1; // never.
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int casos = Integer.parseInt(in.readLine());
		for (int c = 1; c <= casos; c++) {
			StringTokenizer tok = new StringTokenizer(in.readLine());
			tok.nextToken();
			out.append(getPosOrdem(tok.nextToken(), tok.nextToken()) + "\n");
		}

		System.out.print(out);
	}
}
