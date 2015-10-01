import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
 * Route Change.
 */
public class URI1123 {
	private static final int INF = Integer.MAX_VALUE;

	protected static class State implements Comparable<State> {
		int city;
		int value;
		
		public State(int city, int value) {
			this.city = city;
			this.value = value;
		}
		
		@Override
		public int compareTo(State o) {
			return value - o.value;
		}
	}

	protected static class Road {
		int destination;
		int toll;
		
		public Road(int destination, int toll) {
			this.destination = destination;
			this.toll = toll;
		}
	}

	protected static int minTollValue(int origin, int destination, Collection<Road>[] roads) {
		PriorityQueue<State> states = new PriorityQueue<State>();
		states.add(new State(origin, 0));
		
		int[] min = new int[roads.length];
		for (int i = 0; i < min.length; i++) {
			if (i != origin) {
				min[i] = INF;
			}
		}
		
		while (!states.isEmpty()) {
			State state = states.poll();
			
			int current = state.city;

			if (current == destination) {
				break;
			}

			int target = current < destination ? ( current + 1 ) : -1;
			
			for (Road road : roads[current]) {
				if (target == -1 || road.destination == target) {
					int newValue = state.value + road.toll;
					if (newValue < min[road.destination]) {
						min[road.destination] = newValue;

						states.add(new State(road.destination, newValue));
					}
				}
			}
		}
		
		return min[destination];
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder output = new StringBuilder();
		
		while (true) {
			String[] config = reader.readLine().split("\\s+");
			
			int numberOfCities = Integer.parseInt(config[0]);
			int numberOfRoads = Integer.parseInt(config[1]);
			
			int deliverRouteLength = Integer.parseInt(config[2]);
			int source = Integer.parseInt(config[3]);
			
			if (numberOfCities == 0 && numberOfRoads == 0 && deliverRouteLength == 0 && source == 0) {
				break;
			}
			
			Collection<Road>[] roads = new Collection[numberOfCities];

			for (int i = 0; i < numberOfCities; i++) {
				roads[i] = new LinkedList<Road>();
			}
			
			for (int i = 0; i < numberOfRoads; i++) {
				String[] roadConfig = reader.readLine().split("\\s+");
				
				int origin = Integer.parseInt(roadConfig[0]);
				int destination = Integer.parseInt(roadConfig[1]);
				int value = Integer.parseInt(roadConfig[2]);

				roads[origin].add(new Road(destination, value));
				roads[destination].add(new Road(origin, value));
			}
			
			int toll = minTollValue(source, deliverRouteLength - 1, roads);
			
			output.append(toll + "\n");
		}
		
		System.out.print(output);
	}
}
