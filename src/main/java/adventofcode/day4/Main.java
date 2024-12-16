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

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL resource = Main.class.getClassLoader().getResource("day4/input.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

		System.out.println(lines);
		
		int matches = 0;
		
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);

			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == 'X') {
					// Match forward on the same line.
					matches += match(lines, i, j, "XMAS", 0, 0, 1) ? 1 : 0;
					
					// Match backwards on the same line.
					matches += match(lines, i, j, "XMAS", 0, 0, -1) ? 1 : 0;
					
					// Match up.
					matches += match(lines, i, j, "XMAS", 0, -1, 0) ? 1 : 0;
					
					// Match down.
					matches += match(lines, i, j, "XMAS", 0, 1, 0) ? 1 : 0;
					
					// Match diagonal down to the right.
					matches += match(lines, i, j, "XMAS", 0, 1, 1) ? 1 : 0;
					
					// Match diagonal down to the left.
					matches += match(lines, i, j, "XMAS", 0, 1, -1) ? 1 : 0;
					
					// Match diagonal up to the right.
					matches += match(lines, i, j, "XMAS", 0, -1, 1) ? 1 : 0;
					
					// Match diagonal up to the left.
					matches += match(lines, i, j, "XMAS", 0, -1, -1) ? 1 : 0;
				}
			}
		}
		
		System.out.println(matches);
	}

	private static boolean match(List<String> lines, int row, int column, String string, int stringIndex, int rowOffset, int columnOffset) {
		// Base case - we ran out of characters to match.
		if (stringIndex >= string.length()) {
			return true;
		}

		if (row < 0 || row >= lines.size()) {
			return false;
		}

		String line = lines.get(row);

		if (column < 0 || column >= line.length()) {
			return false;
		}

		if (line.charAt(column) == string.charAt(stringIndex)) {
			return match(lines, row + rowOffset, column + columnOffset, string, stringIndex + 1, rowOffset, columnOffset);
		}
		
		return false;
	}
}
