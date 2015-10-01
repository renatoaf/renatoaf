import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/*
 * I Hate SPAM, But Some People Love It.
 */
public class URI1317 {
	public static int[] bfs(int source, Collection<Integer>[] friends) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		
		int[] sent = new int[friends.length];
		for (int i = 0; i < sent.length; i++) {
			sent[i] = -1;
		}
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			
			sent[current] = friends[current].size();
			
			for (int friend : friends[current]) {
				if (sent[friend] == -1) {
					queue.add(friend);
				}
			}
		}
		
		return sent;
	}
	
	public static int reachedCount(int source, int person, int[][] reach, boolean[] found, Collection<Integer>[] friends) {
		if (!found[source]) {
			reach[source] = bfs(source, friends);
			
			found[source] = true;
		}
		return reach[source][person];
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder output = new StringBuilder();
		
		while (true) {
			String[] config = reader.readLine().split("\\s+");
			
			int numberOfPersons = Integer.parseInt(config[0]);
			if (numberOfPersons == 0) {
				break;
			}

			int[][] reach = new int[numberOfPersons][numberOfPersons];
			
			boolean[] found = new boolean[numberOfPersons];

			Collection<Integer>[] friends = new Collection[numberOfPersons];
			Collection<String>[] attributes = new Collection[numberOfPersons];
			
			for (int i = 0; i < numberOfPersons; i++) {
				String[] friendsConfig = reader.readLine().split("\\s+");
				
				friends[i] = new LinkedList<Integer>();
				attributes[i] = new LinkedList<String>();
				
				for (String friend : friendsConfig) {
					if (friend.equals("0")) {
						break;
					}
					
					friends[i].add(Integer.parseInt(friend) - 1);
				}
			}

			String spamMessage = "";
			
			while ( !(spamMessage = reader.readLine()).equals("0") ) {
				String[] messageConfig = spamMessage.split("\\s+");
				
				int source = Integer.parseInt(messageConfig[0]) - 1; 
				int threshold1 = Integer.parseInt(messageConfig[1]); 
				int threshold2 = Integer.parseInt(messageConfig[2]);
				
				String attribute1 = messageConfig[3];
				String attribute2 = messageConfig[4];
				String attribute3 = messageConfig[5];

				for (int i = 0; i < numberOfPersons; i++) {
					int reached = reachedCount(source, i, reach, found, friends);
					if (reached < threshold1) {
						attributes[i].add(attribute1);
					} else if (reached < threshold2) {
						attributes[i].add(attribute2);
					} else {
						attributes[i].add(attribute3);
					}
				}
			}

			for (int i = 0; i < numberOfPersons; i++) {
				String name = reader.readLine().trim();
				
				output.append(name + ": ");
				
				for (String attribute : attributes[i]) {
					output.append(attribute + " ");
				}

				output.append("\n");
			}
		}
		
		System.out.print(output);
	}
}
