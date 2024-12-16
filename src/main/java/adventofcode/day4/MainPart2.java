package adventofcode.day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainPart2 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL resource = MainPart2.class.getClassLoader().getResource("day4/input.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

		System.out.println(lines);

		int matches = 0;

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);

			for (int j = 0; j < line.length(); j++) {
				if (matchForward(lines, i, j) || matchForward2(lines, i, j) || matchBackward(lines, i, j)
						|| matchBackward2(lines, i, j)) {
					matches++;
				}
			}
		}

		System.out.println(matches);
	}

	private static boolean matchForward(List<String> lines, int row, int column) {
		if (matchChar(lines, row, column, 'M') && matchChar(lines, row, column + 2, 'S')
				&& matchChar(lines, row + 1, column + 1, 'A') && matchChar(lines, row + 2, column, 'M')
				&& matchChar(lines, row + 2, column + 2, 'S')) {
			return true;
		}
		return false;
	}

	private static boolean matchForward2(List<String> lines, int row, int column) {
		if (matchChar(lines, row, column, 'M') && matchChar(lines, row, column + 2, 'M')
				&& matchChar(lines, row + 1, column + 1, 'A') && matchChar(lines, row + 2, column, 'S')
				&& matchChar(lines, row + 2, column + 2, 'S')) {
			return true;
		}
		return false;
	}

	private static boolean matchBackward(List<String> lines, int row, int column) {
		if (matchChar(lines, row, column, 'S') && matchChar(lines, row, column + 2, 'M')
				&& matchChar(lines, row + 1, column + 1, 'A') && matchChar(lines, row + 2, column, 'S')
				&& matchChar(lines, row + 2, column + 2, 'M')) {
			return true;
		}
		return false;
	}

	private static boolean matchBackward2(List<String> lines, int row, int column) {
		if (matchChar(lines, row, column, 'S') && matchChar(lines, row, column + 2, 'S')
				&& matchChar(lines, row + 1, column + 1, 'A') && matchChar(lines, row + 2, column, 'M')
				&& matchChar(lines, row + 2, column + 2, 'M')) {
			return true;
		}
		return false;
	}

	private static boolean matchChar(List<String> lines, int row, int column, char c) {
		if (row < 0 || row >= lines.size()) {
			return false;
		}

		String line = lines.get(row);

		if (column < 0 || column >= line.length()) {
			return false;
		}

		return line.charAt(column) == c;
	}
}
