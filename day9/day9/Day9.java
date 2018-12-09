package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day9 {

    static String fileName = "resources/day9.txt";

    public static void main(String[] args) {
        Instant instant = Instant.now();
        Task1 t1 = new Task1();
        t1.runIt();

        Task2 t2 = new Task2();
        t2.runIt();
        System.out.println(Duration.between(instant, Instant.now()));
    }

    public static class Task1 {

        public Integer runIt() {
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                GameData gameData = stream.map(Day9::parse).findFirst().get();
                int numberPlayers = gameData.getNumberPlayers();
                int currentPlayer = 0;

                Map<Integer, Long> playerToScore = new HashMap<>();

                Node firstNode = new Node(0);
                firstNode.setNext(firstNode);
                firstNode.setPrevious(firstNode);
                Node currentNode = firstNode;

                for (int marble = 1; marble <= gameData.getNumberMarbles() * 100; marble++) {

                    currentPlayer = currentPlayer % numberPlayers;

                    if (marble % 23 == 0) {
                        for (int i = 0; i < 7; i++) {
                            currentNode = currentNode.getPrevious();
                        }
                        Node remove = currentNode.remove();
                        currentNode = remove.getNext();
                        playerToScore.putIfAbsent(currentPlayer, 0L);
                        Long currentMarbleScore = playerToScore.get(currentPlayer);
                        playerToScore.put(currentPlayer, currentMarbleScore + marble + remove.getValue());
                    } else {
                        Node newNode = new Node(marble);
                        currentNode.getNext().addAfter(newNode);
                        currentNode = newNode;
                    }
                    currentPlayer++;
                }
                Optional<Long> max = playerToScore.values().stream().max(Comparator.naturalOrder());
                System.out.println("TEST: " + max.get());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        // 2
        // 12
        // -> 7
        private int getWrappedIndexCounterClockWise(int currentIndex, int currentSize) {
            currentIndex = currentIndex - 7; // -5
            if (currentIndex < 0) {
                return currentSize - 1 + currentIndex + 1; // 12-1 + (-5) + 1
            } else {
                return currentIndex;
            }
        }

        private int getWrappedIndexClockWise(int currentIndex, int currentSize, int steps) {
            currentIndex = currentIndex + steps;
            if (currentIndex <= currentSize) {
                return currentIndex;
            } else {
                return currentIndex - currentSize;
            }

        }
    }


    public static class Task2 {

        public Integer runIt() {
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {


            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }


    public static GameData parse(String input) {
        String re1 = "(\\d+)";    // Integer Number 1
        String re2 = ".*?";    // Non-greedy match on filler
        String re3 = "(\\d+)";    // Integer Number 2

        Pattern p = Pattern.compile(re1 + re2 + re3, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find()) {
            String int1 = m.group(1);
            String int2 = m.group(2);
            return new GameData(Integer.parseInt(int2), Integer.parseInt(int1));
        }
        return new GameData(0, 0);
    }

}
