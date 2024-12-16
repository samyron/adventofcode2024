package adventofcode.day3;

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
		URL resource = Main.class.getClassLoader().getResource("day3/input.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));

		String input = String.join("", lines);

		System.out.println(input);

		int start = 0;
		int sum = 0;
		
		Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
		Matcher matcher = pattern.matcher(input);
		
		while(start < input.length()) {
			if(!matcher.find(start)) {
				break;
			}
			
			start = matcher.end();
			
			String num1 = matcher.group(1);
			String num2 = matcher.group(2);
			
			int res = Integer.valueOf(num1) * Integer.valueOf(num2);
			sum += res;
		}
		
		// 187825547 is the right answer.
		
//		while (start < input.length()) {
//			int idx = input.indexOf("mul", start);
//
//			if (idx == -1) {
//				break;
//			}
//
//			// Skip past the "mul".
//			idx += 3;
//			
//			if (expect(input, idx, '(')) {
//				idx++;
//			} else {
//				start = idx;
//				continue;
//			}
//			
//			String num1 = readNumber(input, idx);
//			if (num1.length() > 0) {
//				idx += num1.length();
//			} else {
//				start = idx;
//				continue;
//			}
//			
//			if (expect(input, idx, ',')) {
//				idx++;
//			} else {
//				start = idx;
//				continue;
//			}
//			
//			String num2 = readNumber(input, idx);
//			if (num2.length() > 0) {
//				idx += num2.length();
//			} else {
//				start = idx;
//				continue;
//			}
//			
//			if (expect(input, idx, ')')) {
//				idx++;
//			} else {
//				start = idx;
//				continue;
//			}
//			
//			int res = Integer.valueOf(num1) * Integer.valueOf(num2);
//			sum += res;
//			
//			start = idx;
//		}
		
		System.out.println(sum);

	}
	
	public static String readNumber(String input, int start) {
		StringBuilder b = new StringBuilder();
		for(int i=start; i<input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isDigit(c)) {
				b.append(c);
			} else {
				break;
			}
		}
		return b.toString();
	}
	
	public static boolean expect(String input, int idx, char c) {
		if (idx >= input.length()) {
			return false;
		}
		
		if (input.charAt(idx) == c) {
			return true;
		}
		
		return false;
	}
}
