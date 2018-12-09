package day6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {

    private List<Point> points;

    public Grid(List<Point> points) {
        this.points = points;
    }

    public Point lowerCorner() {
        return new Point(points.stream().map(Point::getX).min(Comparator.naturalOrder()).get(), points.stream().map(Point::getY).min(Comparator.naturalOrder()).get());
    }

    public Point upperCorner() {
        return new Point(points.stream().map(Point::getX).max(Comparator.naturalOrder()).get(), points.stream().map(Point::getY).max(Comparator.naturalOrder()).get());
    }

    public List<Coordinate> coordinates() {
        IntStream xStream = IntStream.rangeClosed(lowerCorner().getX(), upperCorner().getX());
        List<Coordinate> coordinates = new ArrayList<>();

        xStream.boxed().forEach(x -> {
                    IntStream yStream = IntStream.rangeClosed(lowerCorner().getY(), upperCorner().getY());
                    yStream.boxed().forEach(y -> coordinates.add(new Coordinate(x, y)));
                }
        );
        return coordinates;
    }

    public boolean onBorder(Coordinate coordinate) {
        return lowerCorner().getX() == coordinate.getX() || upperCorner().getX() == coordinate.getX() ||
                lowerCorner().getY() == coordinate.getY() || upperCorner().getY() == coordinate.getY();
    }
}
