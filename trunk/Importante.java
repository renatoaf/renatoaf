import java.util.Vector;

class Main {
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
	static Vector splitBySingleSpace(String s) {
		Vector v = new Vector();
		StringBuffer builder = new StringBuffer();
		boolean charRead = false;
		boolean first = true;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				if (builder.length() > 0 && charRead) {
					v.addElement(builder.toString());
					builder = new StringBuffer();
					charRead = false;
				} else if (!first) {
					builder.append(c);
				}
			} else {
				charRead = true;
				builder.append(c);
			}
			first = false;
		}
		if (builder.length() > 0 && charRead) {
			v.addElement(builder.toString());
			builder = new StringBuffer();
			charRead = false;
		}
		return v;
	}

	static String round(double valor, int casas) {
		long numero = Math.round(valor * Math.pow(10, casas));

		String retorno = "" + (numero / (long) Math.pow(10, casas));
		retorno += ".";
		String resto = "" + (numero % (long) Math.pow(10, casas));
		resto = str('0', casas - resto.length()) + resto;
		retorno += resto;

		return retorno;
	}

	static String str(char ch, int n) {
		String resultado = "";
		for (int i = 0; i < n; i++) {
			resultado += ch;
		}
		return resultado;
	}

	// 4 - Maneira de pegar o double a partir de uma string [o mesmo que faz o
	// método Double.parseDouble(string)]:
	// double num = new Double(linha).doubleValue();

	// String linha = readln() //Usando o metodo acima para leitura de dados
	// StringTokenizer s = new StringTokenizer(linha) //Definimos o objeto
	// StringTokenizer para a String linha
	//
	// //Caso a linha seja: "Amaury Solon Wener" ao fazermos:
	// s.nextToken()
	// //Esse método retorna "Amaury". Se fizésemos outro s.nextToken() ele
	// retornaria "Wener" e assim por diante.
	//    
	// Pode-se também definir separadores para o tokenizer [como não definimos
	// acima, ele considera espacos em branco]. Um exemplo é:
	//
	// String linha = readln(); //Suponha a linha "Amaury, Solon; Wener"
	// StringTokenizer s = new StringTokenizer(linha, ",;"); //Agora ele
	// considera espacos, virgulas e pontos-e-virgulas como separadores
	//
	// s.nextToken() //Retorna "Amaury"

}