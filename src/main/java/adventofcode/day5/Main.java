package adventofcode.day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL resource = Main.class.getClassLoader().getResource("day5/input.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

		Map<String, Set<String>> afters = new HashMap<>();

		int i = 0;
		for (; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.isBlank()) {
				i++;
				break;
			}

			String[] numbers = line.split("\\|");

			addRule(afters, numbers[0], numbers[1]);
		}

		int sum = 0;
		int sum2 = 0;

		for (; i < lines.size(); i++) {
			String line = lines.get(i);
			if (isValid(line, afters)) {
				sum += middle(line);
			} else {
				System.out.println(line);
				// This is invalid
				String[] pages = line.split(",");
				int j = pages.length - 1;
				while (j > 0) {
					String page = pages[j];
					Set<String> mustBeAfter = afters.get(page);
					boolean advance = true;

					for (int z = j - 1; z >= 0; z--) {
						String page2 = pages[z];

						if (mustBeAfter != null && mustBeAfter.contains(page2)) {
							pages[j] = page2;
							pages[z] = page;
							advance = false;
							break;
						}
					}

					if (advance) {
						j--;
					}
				}
				line = String.join(",", pages);
				System.out.println(isValid(line, afters));
				sum2 += middle(line);
			}
		}

		System.out.println(sum);
		System.out.println(sum2);
	}

	// The key must become before any of the pages in the value.
	private static void addRule(Map<String, Set<String>> map, String key, String value) {
		Set<String> values = map.get(key);
		if (values == null) {
			values = new HashSet<String>();
			map.put(key, values);
		}
		values.add(value);
	}

	private static boolean isValid(String line, Map<String, Set<String>> afters) {
		Set<String> visited = new HashSet<String>();

		String[] updates = line.split(",");
		for (String page : updates) {
			if (containAny(visited, afters.get(page))) {
				return false;
			}

			visited.add(page);
		}

		return true;
	}

	private static boolean containAny(Set<String> visited, Set<String> other) {
		if (other == null) {
			return false;
		}

		return !Collections.disjoint(visited, other);
	}

	private static int middle(String line) {
		String[] updates = line.split(",");

		return Integer.valueOf(updates[updates.length / 2]);
	}
}
