import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Stream;

public class Day5 {
    public static void main(String[] args) {
        // System.out.println(Math.abs('a' - 'A'));
//        Task1 t1 = new Task1();
//        t1.runIt();
        Instant start = Instant.now();
        Task2 t2 = new Task2();
        t2.runIt();
        System.out.println(Duration.between(start, Instant.now()));
    }

    public static class Task1 {
        String fileName = "resources/day5.txt";

        public Integer runIt() {
            Stack<Character> resultingPolymer = new Stack<>();
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                String polymer = stream.findAny().get();
                polymer.chars().forEach(chr -> {
                    if (resultingPolymer.isEmpty()) {
                        resultingPolymer.push((char) chr);
                    } else {
                        Character oldChar = resultingPolymer.pop();
                        if (Math.abs(chr - oldChar) != 32) {
                            resultingPolymer.push(oldChar);
                            resultingPolymer.push((char) chr);
                        }
                    }
                });
                System.out.println(resultingPolymer.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }


    public static class Task2 {
        String fileName = "resources/day5.txt";
        String units = "abcdefghijklmnopqrstuvwxyz";

        public Integer runIt() {

            int max = 0;

            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                String polymer = stream.findAny().get();
                List<Integer> sizes = new ArrayList<>();
                units.chars().forEach(unit -> {
//                    Stack<Character> unitCleanedPolymer = new Stack<>();
                    List<Character> unitCleanedPolymer = new ArrayList<>();
                    polymer.chars().forEach(chr -> {
                        if (!(chr == unit || chr == unit-32)) {
                            unitCleanedPolymer.add((char)chr);
                        }
                    });
                    Stack<Character> reactedPolymer = new Stack<>();
                    unitCleanedPolymer.forEach(chr -> {
                        if (reactedPolymer.isEmpty()) {
                            reactedPolymer.push((char) chr);
                        } else {
                            Character oldChar = reactedPolymer.pop();
                            if (Math.abs(chr - oldChar) != 32) {
                                reactedPolymer.push(oldChar);
                                reactedPolymer.push((char) chr);
                            }
                        }
                    });
                    sizes.add(reactedPolymer.size());

                });
                Optional<Integer> minPolymerLength = sizes.stream().min(Comparator.naturalOrder());

                System.out.println(minPolymerLength.get());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

}
