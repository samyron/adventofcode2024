package adventofcode.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

record Pair(int first, int second) {
}

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL resource = Main.class.getClassLoader().getResource("day1/input.txt");
		List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));
		
		List<Pair> pairs = lines.stream().map(String::trim).filter(Predicate.not(String::isEmpty))
				.map((String s) -> s.split("\s+"))
				.map((String[] s) -> new Integer[] { Integer.valueOf(s[0]), Integer.valueOf(s[1])} )
				.map((Integer[] p) -> new Pair(p[0], p[1]))
				.collect(Collectors.toList());
		
		
		Integer[] firsts = pairs.stream().map(Pair::first).toArray(Integer[]::new);
		Integer[] seconds = pairs.stream().map(Pair::second).toArray(Integer[]::new);
		
		Arrays.sort(firsts);
		Arrays.sort(seconds);
		
		
		int result = 0;
		for(int i=0; i<firsts.length; i++) {
			result += Math.abs(firsts[i] - seconds[i]);
		}
		
		System.out.println(result);
	}
}
