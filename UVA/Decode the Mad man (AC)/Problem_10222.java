import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_10222 {
	public static void main(String[] args) throws IOException {
		char[] decode = new char[300];
		decode['q'] = 'q';
		decode['w'] = 'w';
		decode['e'] = 'q';
		decode['r'] = 'w';
		decode['t'] = 'e';
		decode['y'] = 'r';
		decode['u'] = 't';
		decode['i'] = 'y';
		decode['o'] = 'u';
		decode['p'] = 'i';
		decode['['] = 'o';
		decode[']'] = 'p';
		decode['a'] = '[';
		decode['s'] = ']';
		decode['d'] = 'a';
		decode['f'] = 's';
		decode['g'] = 'd';
		decode['h'] = 'f';
		decode['j'] = 'g';
		decode['k'] = 'h';
		decode['l'] = 'j';
		decode[';'] = 'k';
		decode['\''] = 'l';
		decode['\\'] = ';';
		decode['z'] = '\'';
		decode['x'] = '\\';
		decode['c'] = 'z';
		decode['v'] = 'x';
		decode['b'] = 'c';
		decode['n'] = 'v';
		decode['m'] = 'b';
		decode[','] = 'n';
		decode['.'] = 'm';
		decode['/'] = ',';
		decode[' '] = ' ';
		decode['\n'] = '\n';

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer saida = new StringBuffer();

		while (true) {
			int read = in.read();

			if (read == -1) {
				break;
			}
			char ant = (char) read;
			if (Character.isLetter(ant)) {
				ant = Character.toLowerCase(ant);
			}
			if (decode[ant] == 0) {
				saida.append(ant);
			} else {
				saida.append(decode[ant]);
			}
		}

		System.out.print(saida);
	}
}