package adventofcode.day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainRE {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL resource = MainRE.class.getClassLoader().getResource("day3/input.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

		String input = String.join("", lines);

		int sum = 0;
		
		Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
		Matcher matcher = pattern.matcher(input);
		
		while(matcher.find()) {
			String num1 = matcher.group(1);
			String num2 = matcher.group(2);
			
			int res = Integer.valueOf(num1) * Integer.valueOf(num2);
			sum += res;
		}
		
		System.out.println(sum);

	}
}
