import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
	
	
	public static void main(String[] args) {
		Task2 t2 = new Task2();
		t2.runIt();
		
	}
	
	public class Task1 {
		String fileName = "resources/day1.txt";
		
		public Integer runIt() {
			//read file into stream, try-with-resources
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
				
				Integer collect = stream.map(Integer::parseInt).collect(Collectors.summingInt(Integer::intValue));
				System.out.println(collect);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	public static class Task2 {
		String fileName = "resources/day1.txt";
		
		public Integer runIt() {
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
				
				List<Integer> freqDiffs = stream.map(Integer::parseInt).collect(Collectors.toList());
				int size = freqDiffs.size();
				Set<Integer> resultingFreqs = new HashSet<>();
				int freq = 0;
				int freqIndex = 0;
				while(resultingFreqs.add(freq + freqDiffs.get(freqIndex % size))) {
					freq = freq + freqDiffs.get(freqIndex % size);
					freqIndex++;
				}
				
				System.out.println(freq + freqDiffs.get(freqIndex % size));
				return freq + freqDiffs.get(freqIndex % size);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}
}
