import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
 * Even Obsession. 
 */
public class URI1931 {
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

			for (Road firstRoad : roads[state.city]) {
				for (Road secondRoad : roads[firstRoad.destination]) {
					int newValue = state.value + firstRoad.toll + secondRoad.toll;
					if (newValue < min[secondRoad.destination]) {
						min[secondRoad.destination] = newValue;

						states.add(new State(secondRoad.destination, newValue));
					}
				}
			}
		}
		
		return min[destination];
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String[] config = reader.readLine().split("\\s+");
		
		int numberOfCities = Integer.parseInt(config[0]);
		int numberOfRoads = Integer.parseInt(config[1]);
		
		Collection<Road>[] roads = new Collection[numberOfCities];

		for (int i = 0; i < numberOfCities; i++) {
			roads[i] = new LinkedList<Road>();
		}
		
		for (int i = 0; i < numberOfRoads; i++) {
			String[] roadConfig = reader.readLine().split("\\s+");
			
			int origin = Integer.parseInt(roadConfig[0]) - 1;
			int destination = Integer.parseInt(roadConfig[1]) - 1;
			int value = Integer.parseInt(roadConfig[2]);

			roads[origin].add(new Road(destination, value));
			roads[destination].add(new Road(origin, value));
		}
		
		int originCity = 0;
		int destinationCity = numberOfCities - 1;
		
		int toll = minTollValue(originCity, destinationCity, roads);
		
		System.out.println(toll == INF ? -1 : toll);
	}
}
