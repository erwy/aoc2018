import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {
    
    public static void main(String[] args) {
        //Task1 t1 = new Task1();
        //t1.runIt();
        
        Task2 t2 = new Task2();
        t2.runIt();
    }
    
    public static class Task1 {
        String fileName = "resources/day2.txt";
        
        public Integer runIt() {
            //read file into stream, try-with-resources
            AtomicInteger numberOf2 = new AtomicInteger();
            AtomicInteger numberOf3 = new AtomicInteger();
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                
                stream.forEach(s -> {
                    Map<Integer, Long> charFrequency = s.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                    if (charFrequency.values().stream().filter(c -> c == 2).findAny().isPresent()) {
                        numberOf2.incrementAndGet();
                    };
                    if (charFrequency.values().stream().filter(c -> c == 3).findAny().isPresent()) {
                        numberOf3.incrementAndGet();
                    };
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(numberOf2.intValue() * numberOf3.intValue());
            return 0;
        }
    }
    
    public static class Task2 {
        String fileName = "resources/day2.txt";
        Set<String> capturedWords = new HashSet<>();
        
        public Integer runIt() {
            try (Stream<String> words = Files.lines(Paths.get(fileName))) {
                words.forEach(s -> {
                    Set<String> variantsOfWord = new HashSet<>();
                    for (int i = 0; i < s.length(); i++) {
                        String s1 = s.substring(0, i) + s.substring(i + 1);
                        variantsOfWord.add(s1);
                    };
                    for (String s2 : variantsOfWord) {
                        if (!capturedWords.add(s2)) {
                            System.out.println(s2);
                        }
                    }
                });
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
