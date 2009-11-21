import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class PARADA2 {
	static Linha[] linhas;
	static ArrayList<Integer> entradas;
	static HashMap<Integer, Integer> memorization;

	static int get(String op, int[] memoria) {
		if (!op.startsWith("R"))
			return Integer.parseInt(op);
		if (op.equals("R0"))
			return memoria[0];
		if (op.equals("R1"))
			return memoria[1];
		if (op.equals("R2"))
			return memoria[2];
		if (op.equals("R3"))
			return memoria[3];
		if (op.equals("R4"))
			return memoria[4];
		if (op.equals("R5"))
			return memoria[5];
		if (op.equals("R6"))
			return memoria[6];
		if (op.equals("R7"))
			return memoria[7];
		if (op.equals("R8"))
			return memoria[8];
		return memoria[9];
	}

	static void atualizar(String op, int novo, int[] memoria) {
		if (op.equals("R0"))
			memoria[0] = novo;
		else if (op.equals("R1"))
			memoria[1] = novo;
		else if (op.equals("R2"))
			memoria[2] = novo;
		else if (op.equals("R3"))
			memoria[3] = novo;
		else if (op.equals("R4"))
			memoria[4] = novo;
		else if (op.equals("R5"))
			memoria[5] = novo;
		else if (op.equals("R6"))
			memoria[6] = novo;
		else if (op.equals("R7"))
			memoria[7] = novo;
		else if (op.equals("R8"))
			memoria[8] = novo;
		else
			memoria[9] = novo;
	}

	static int executaPrograma(int entrada) {
		if (entradas.contains(entrada)) {
			return -1;
		} else if (memorization.containsKey(entrada)) {
			return memorization.get(entrada);
		}
		entradas.add(entrada);

		int[] memoriaLocal = new int[10];
		memoriaLocal[0] = entrada;
		int i = 0;

		while (true) {
			Linha l = linhas[i++];
			String comando = l.entrada[0];
			String op1 = l.entrada[1];
			String op2 = l.entrada[2];

			if (comando.equals("MOV")) {
				atualizar(op1, get(op2, memoriaLocal), memoriaLocal);
			} else if (comando.equals("ADD")) {
				atualizar(
						op1,
						(get(op1, memoriaLocal) + get(op2, memoriaLocal)) % 1000,
						memoriaLocal);
			} else if (comando.equals("SUB")) {
				atualizar(
						op1,
						((get(op1, memoriaLocal) - get(op2, memoriaLocal)) + 1000) % 1000,
						memoriaLocal);
			} else if (comando.equals("MUL")) {
				atualizar(
						op1,
						(get(op1, memoriaLocal) * get(op2, memoriaLocal)) % 1000,
						memoriaLocal);
			} else if (comando.equals("DIV")) {
				int divisor = get(op2, memoriaLocal);
				atualizar(op1, divisor == 0 ? 0
						: (get(op1, memoriaLocal) / divisor), memoriaLocal);
			} else if (comando.equals("MOD")) {
				int divisor = get(op2, memoriaLocal);
				atualizar(op1, divisor == 0 ? 0
						: (get(op1, memoriaLocal) % divisor), memoriaLocal);
			} else if (comando.equals("IFEQ")) {
				if (get(op1, memoriaLocal) != get(op2, memoriaLocal)) {
					while (!linhas[i].entrada[0].equals("ENDIF")) {
						i++;
					}
				}
			} else if (comando.equals("IFNEQ")) {
				if (get(op1, memoriaLocal) == get(op2, memoriaLocal)) {
					while (!linhas[i].entrada[0].equals("ENDIF")) {
						i++;
					}
				}
			} else if (comando.equals("IFG")) {
				if (get(op1, memoriaLocal) <= get(op2, memoriaLocal)) {
					while (!linhas[i].entrada[0].equals("ENDIF")) {
						i++;
					}
				}
			} else if (comando.equals("IFL")) {
				if (get(op1, memoriaLocal) >= get(op2, memoriaLocal)) {
					while (!linhas[i].entrada[0].equals("ENDIF")) {
						i++;
					}
				}
			} else if (comando.equals("IFGE")) {
				if (get(op1, memoriaLocal) < get(op2, memoriaLocal)) {
					while (!linhas[i].entrada[0].equals("ENDIF")) {
						i++;
					}
				}
			} else if (comando.equals("IFLE")) {
				if (get(op1, memoriaLocal) > get(op2, memoriaLocal)) {
					while (!linhas[i].entrada[0].equals("ENDIF")) {
						i++;
					}
				}
			} else if (comando.equals("ENDIF")) {
				continue;
			} else if (comando.equals("CALL")) {
				memoriaLocal[9] = executaPrograma(get(op1, memoriaLocal));
				if (memoriaLocal[9] == -1) {
					break;
				}
			} else if (comando.equals("RET")) {
				memoriaLocal[9] = get(op1, memoriaLocal);
				break;
			}
		}

		entradas.remove(entradas.indexOf(entrada)); // pra essa subrotina
		memorization.put(entrada, memoriaLocal[9]);
		return memoriaLocal[9];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		while (true) {
			StringTokenizer config = new StringTokenizer(in.readLine());
			int L = Integer.parseInt(config.nextToken());
			int N = Integer.parseInt(config.nextToken());

			if (L == 0 && N == 0) {
				break;
			}

			linhas = new Linha[L];
			entradas = new ArrayList<Integer>();
			memorization = new HashMap<Integer, Integer>();

			for (int i = 0; i < L; i++) {
				linhas[i] = new Linha(in.readLine());
			}

			int resultado = executaPrograma(N);
			out.append(resultado == -1 ? "*\n" : resultado + "\n");
		}

		System.out.print(out);
	}
}

class LinhaCodigo {
	String[] entrada;

	public LinhaCodigo(String linha) {
		entrada = new String[4];
		StringTokenizer c = new StringTokenizer(linha, " ,");
		int i = 0;
		while (c.hasMoreTokens()) {
			entrada[i++] = c.nextToken();
		}
	}
}