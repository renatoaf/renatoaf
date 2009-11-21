import java.util.Stack;

class Problem_2007 {
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
		while (true) {
			String texto = "";

			while (true) {
				String linha = readln().trim();

				int ind;
				if ((ind = linha.indexOf("#")) != -1) {
					texto += linha.substring(0, ind + 1);
					break;
				}

				texto += linha;
			}

			texto = texto.trim();
			if (texto.equals("#")) {
				break;
			}

			HTML h = new HTML(texto);
			System.out.println(h);
		}
	}
}

@SuppressWarnings("unchecked")
class HTML {
	String texto;
	String retorno;
	Stack tagsAbertas;

	public HTML(String texto) {
		this.texto = texto;
		this.tagsAbertas = new Stack();
	}

	boolean ehTagAberta(String t) {
		char c1 = t.charAt(0);
		char c2 = t.charAt(1);
		char c3 = t.charAt(2);
		return c1 == '<' && c3 == '>' && c2 >= 'A' && c2 <= 'Z';
	}

	boolean ehTagFechada(String t) {
		char c1 = t.charAt(0);
		char c2 = t.charAt(1);
		char c3 = t.charAt(2);
		char c4 = t.charAt(3);
		return c1 == '<' && c2 == '/' && c4 == '>' && c3 >= 'A' && c3 <= 'Z';
	}

	public String toString() {
		int i = 0;
		while (i < texto.length()) {
			if (i + 4 < texto.length()) {
				String tagF = texto.substring(i, i + 4);

				if (ehTagFechada(tagF)) {
					if (tagsAbertas.size() == 0) {
						return "Expected # found " + tagF;
					} else {
						String tagAb = (String) tagsAbertas.pop();
						String esperada = tagAb.charAt(0) + "/"
								+ tagAb.charAt(1) + tagAb.charAt(2);

						if (!esperada.equals(tagF)) {
							return "Expected " + esperada + " found " + tagF;
						}
					}
				}
			}

			if (i + 3 < texto.length()) {
				String tagA = texto.substring(i, i + 3);
				if (ehTagAberta(tagA)) {
					tagsAbertas.push(tagA);
				}
			}

			i++;
		}

		if (tagsAbertas.size() > 0) {
			String tagAb = (String) tagsAbertas.pop();
			String esperada = tagAb.charAt(0) + "/" + tagAb.charAt(1)
					+ tagAb.charAt(2);
			return "Expected " + esperada + " found #";
		}

		return "Correctly tagged paragraph";
	}
}