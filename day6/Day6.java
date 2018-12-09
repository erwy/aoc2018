import day6.Coordinate;
import day6.Grid;
import day6.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Day6 {

    public static void main(String[] args) {
        Task1 t1 = new Task1();
        t1.runIt();

        Task2 t2 = new Task2();
        t2.runIt();
    }

    public static class Task1 {
        String fileName = "resources/day6.txt";

        public Integer runIt() {

            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                List<Point> originalPoints = stream
                        .map(s -> s.split(","))
                        .map(s -> new Point(Integer.parseInt(s[0].trim()), Integer.parseInt(s[1].trim())))
                        .collect(Collectors.toList());

                Grid grid = new Grid(originalPoints);

                Set<Point> removedPoints = new HashSet<>();

                List<Point> points = grid.coordinates().stream()
                        .map(coordinate -> {
                            Map<Integer, List<Point>> distanceToPoints = originalPoints.stream()
                                    .collect(groupingBy(coordinate::distanceTo));
                            Integer minDistance = distanceToPoints.keySet().stream().min(Comparator.naturalOrder()).get();
                            Point point = distanceToPoints.get(minDistance).size() == 1 ? distanceToPoints.get(minDistance).get(0) : null;
                            if (point != null && grid.onBorder(coordinate)) {
                                removedPoints.add(point);
                            }
                            return point;
                        }).collect(toList());

                Optional<Long> max = points.stream().filter(Objects::nonNull).filter(p -> !removedPoints.contains(p))
                        .collect(groupingBy(Function.identity(), Collectors.counting())).values()
                        .stream()
                        .max(Long::compare);


                System.out.println("CATCH:" + max);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }


    public static class Task2 {
        String fileName = "resources/day6.txt";

        public Integer runIt() {
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                List<Point> originalPoints = stream
                        .map(s -> s.split(","))
                        .map(s -> new Point(Integer.parseInt(s[0].trim()), Integer.parseInt(s[1].trim())))
                        .collect(Collectors.toList());

                Grid grid = new Grid(originalPoints);
                List<Coordinate> coordinates = grid.coordinates();
                long count = coordinates.stream().map(c -> originalPoints.stream()
                        .map(p -> c.distanceTo(p))
                        .mapToInt(Integer::intValue).sum())
                        .filter(i -> i < 10000).count();

                System.out.println(count);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
