import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * Não passa (nenhuma solução aceita em Java até agora :o)
 */
class HASHADIQ {
	static class Criteria {
		private String tag;
		private String value;
		
		public Criteria(String tag, String value) {
			this.tag = tag;
			this.value = value;
		}
		
		public String getTag() {
			return tag;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	static class Person {
		private Integer id;
		private String firstName;
		private String lastName;
		private String birthday;
		private String phone;
		
		public Person(Integer id, String firstName, String lastName, String birthday, String phone) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.birthday = birthday;
			this.phone = phone;
		}
		
		public Integer getId() {
			return id;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public String getBirthday() {
			return birthday;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public String getPhone() {
			return phone;
		}
		
		public boolean matches(Collection<Criteria> criterias) {
			for (Criteria criteria : criterias) {
				if (criteria.getTag().equals("fn") && !getFirstName().equals(criteria.getValue())) {
					return false;
				}
				if (criteria.getTag().equals("ln") && !getLastName().equals(criteria.getValue())) {
					return false;
				}
				if (criteria.getTag().equals("bd") && !getBirthday().equals(criteria.getValue())) {
					return false;
				}
				if (criteria.getTag().equals("pn") && !getPhone().equals(criteria.getValue())) {
					return false;
				}
			}
			return true;
		}
	}

	private static Map<Integer, Person> byId = new HashMap<Integer, Person>();
	private static Map<String, Map<Integer, Person>> byFirstName = new HashMap<String, Map<Integer, Person>>();
	private static Map<String, Map<Integer, Person>> byLastName = new HashMap<String, Map<Integer, Person>>();
	private static Map<String, Map<Integer, Person>> byBirthDay = new HashMap<String, Map<Integer, Person>>();
	private static Map<String, Map<Integer, Person>> byPhone = new HashMap<String, Map<Integer, Person>>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		
		String line = null;
		
		while ((line = input.readLine()) != null) {
			String[] tokens = line.split("\\s+");
			
			if (tokens[0].equals("add")) {
				Integer id = Integer.parseInt(tokens[1]);
				if (!add(id, new Person(id, tokens[2], tokens[3], tokens[4], tokens[5]))) {
					output.append("ID " + tokens[1] + " ja cadastrado.\n");
				}
			}
			
			if (tokens[0].equals("del")) {
				Integer id = Integer.parseInt(tokens[1]);
				if (!delete(id)) {
					output.append("ID " + tokens[1] + " nao existente.\n");
				}
			}
			
			if (tokens[0].equals("info")) {
				Integer id = Integer.parseInt(tokens[1]);
				Person person = info(id);
				if (person == null) {
					output.append("ID " + tokens[1] + " nao existente.\n");
				} else {
					output.append(person.getFirstName() + " " + person.getLastName() + " " + person.getBirthday() + " " + person.getPhone() + "\n");
				}
			}
			
			if (tokens[0].equals("query")) {
				List<Criteria> criterias = new LinkedList<Criteria>();
				for (int i = 1; i < tokens.length; i++) {
					String[] parts = tokens[i].split(":");
					criterias.add(new Criteria(parts[0], parts[1]));
				}
				
				String separator = "";
				for (Integer id : query(criterias)) {
					output.append(separator + id);
					separator = " ";
				}
				output.append("\n");
			}
		}
		
		System.out.println(output);
	}

	private static Person info(Integer id) {
		return byId.get(id);
	}
	
	private static boolean add(Integer id, Person person) {
		if (!byId.containsKey(id)) {
			byId.put(id, person);
			
			add(byBirthDay, person.getBirthday(), id, person);
			add(byFirstName, person.getFirstName(), id, person);
			add(byLastName, person.getLastName(), id, person);
			add(byPhone, person.getPhone(), id, person);
			
			return true;
		}
		
		return false;
	}

	private static boolean delete(Integer id) {
		Person removed = byId.remove(id);
		
		if (removed != null) {
			delete(byBirthDay, removed.getBirthday(), id);
			delete(byFirstName, removed.getFirstName(), id);
			delete(byLastName, removed.getLastName(), id);
			delete(byPhone, removed.getPhone(), id);
			
			return true;
		}
		
		return false;
	}
	
	private static Set<Integer> query(List<Criteria> criterias) {
		Set<Integer> ids = new TreeSet<Integer>();
		
		for (Criteria criteria : criterias) {
			for (Person person : query(criteria, criterias)) {
				ids.add(person.getId());
			}
		}
		
		return ids;
	}

	private static List<Person> query(Criteria criteria, List<Criteria> criterias) {
		List<Person> matched = new LinkedList<Person>();
		
		Collection<Person> indexed = null;
		if (criteria.getTag().equals("fn")) {
			indexed = indexed(byFirstName, criteria);
		}
		if (criteria.getTag().equals("ln")) {
			indexed = indexed(byLastName, criteria);
		}
		if (criteria.getTag().equals("bd")) {
			indexed = indexed(byBirthDay, criteria);
		}
		if (criteria.getTag().equals("pn")) {
			indexed = indexed(byPhone, criteria);
		}
		
		for (Person person : indexed) {
			if (person.matches(criterias)) {
				matched.add(person);
			}
		}
		
		return matched;
	}

	private static void add(Map<String, Map<Integer, Person>> map, String key, Integer id, Person person) {
		Map<Integer, Person> persons = map.get(key);
		if (persons == null) {
			persons = new HashMap<Integer, Person>();
			map.put(key, persons);
		}
		persons.put(id, person);
	}

	private static void delete(Map<String, Map<Integer, Person>> map, String key, Integer id) {
		Map<Integer, Person> persons = map.get(key);
		if (persons != null) {
			persons.remove(id);
		}
	}

	private static Collection<Person> indexed(Map<String, Map<Integer, Person>> map, Criteria criteria) {
		Map<Integer, Person> indexed = map.get(criteria.getValue());
		if (indexed == null) {
			return Collections.emptyList();
		}
		return indexed.values();
	}
}
