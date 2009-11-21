import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

class AlienLanguage {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer config = new StringTokenizer(in.readLine());

		int L = Integer.parseInt(config.nextToken());
		int D = Integer.parseInt(config.nextToken());
		int N = Integer.parseInt(config.nextToken());

		char[][] palavras = new char[D][L];

		for (int i = 0; i < D; i++) {
			palavras[i] = in.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			int tot = 0;

			Tok tokenizer = new Tok(in.readLine());
			for (int m = 0; m < D; m++) {
				boolean iguais = true;

				tokenizer.renew();
				for (int j = 0; j < L; j++) {
					String p = tokenizer.next();
					boolean achou = false;
					for (int k = 0; k < p.length(); k++) {
						if (p.charAt(k) == palavras[m][j]) {
							achou = true;
							break;
						}
					}
					if (!achou) {
						iguais = false;
						break;
					}
				}

				if (iguais) {
					tot++;
				}
			}

			System.out.println("Case #" + (i + 1) + ": " + tot);
		}
	}
}

class Tok {
	ArrayList<String> toks = new ArrayList<String>();
	Iterator<String> value;

	public Tok(String s) {
		int i = 0;

		boolean parent = false;

		String buf = "";
		while (i < s.length()) {
			char c = s.charAt(i);
			if (c == '(') {
				parent = true;
			} else if (c == ')') {
				toks.add(buf);
				buf = "";
				parent = false;
			} else {
				if (parent) {
					buf += c;
				} else {
					toks.add(c + "");
				}
			}
			i++;
		}
	}

	public void renew() {
		value = toks.iterator();
	}

	public String next() {
		return value.next();
	}
}