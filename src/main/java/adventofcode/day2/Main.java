package adventofcode.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL resource = Main.class.getClassLoader().getResource("day2/input.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

		List<Integer[]> reports = lines.stream().map(String::trim).filter(Predicate.not(String::isEmpty))
				.map((String s) -> s.split("\s+"))
				.map((String[] s) -> Arrays.stream(s).map(Integer::valueOf).toArray(Integer[]::new)).toList();

		int safe = 0;
		int safe2 = 0;

		for (Integer[] report : reports) {
			if (isSafe(report)) {
				safe++;
				safe2++;
			} else {
				for (int i = 0; i < report.length; i++) {
					List<Integer> rep = new ArrayList<>(Arrays.asList(report));
					rep.remove(i);
					
					if (isSafe(rep.toArray(new Integer[0]))) {
						safe2++;
						break;
					}
				}
			}

		}

		System.out.println(safe);
		System.out.println(safe2);

	}

	public static boolean isSafe(Integer[] report) {
		boolean positive = false;
		boolean negative = false;
		boolean notSafe = false;

		for (int i = 0; i < report.length - 1; i++) {
			int diff = report[i] - report[i + 1];
			positive = positive || diff > 0;
			negative = negative || diff < 0;

			diff = Math.abs(diff);

			if (diff == 0 || diff > 3) {
				notSafe = true;
				break;
			} else if (positive && negative) {
				notSafe = true;
				break;
			}
		}

		return !notSafe;
	}
}

// 352 too high
