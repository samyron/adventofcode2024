package adventofcode.day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainREPart2 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL resource = MainREPart2.class.getClassLoader().getResource("day3/input.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

		String input = String.join("", lines);

		int sum = 0;
		boolean enabled = true;
		
		Pattern pattern = Pattern.compile("(?<e>do|don't)\\(\\)|mul\\((?<num1>\\d+),(?<num2>\\d+)\\)");
		Matcher matcher = pattern.matcher(input);
		
		while(matcher.find()) {
			String e = matcher.group("e");
			if("do".equals(e)) {
				enabled = true;
			} else if ("don't".equals(e)) {
				enabled = false;
			} else if (enabled) {
				String num1 = matcher.group("num1");
				String num2 = matcher.group("num2");
				
				int res = Integer.valueOf(num1) * Integer.valueOf(num2);
				sum += res;
			}
		}
		
		System.out.println(sum);

	}
}
