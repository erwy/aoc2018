import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

public class Claim {
    private int height;
    private int width;
    private int id;
//	private int x;
//	private int y;
//	private int height;
//	private int width;
    
    private Point lowerLeft;
    private Point upperRight;
    
    
    public Claim(String claimDefinition) {
        Matcher matcher = ClaimPattern.getPattern().matcher(claimDefinition);
        if (matcher.find()) {
            id = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int x = Integer.parseInt(matcher.group(3));
            height = Integer.parseInt(matcher.group(4));
            width = Integer.parseInt(matcher.group(5));
            lowerLeft = new Point(x, y);
            upperRight = new Point(x + width, y + height);
        }
    }
    
    public Claim(Point lowerLeft, Point upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.width = upperRight.getX() - lowerLeft.getX();
        this.height = upperRight.getY() - lowerLeft.getY();
    }
    
    public Point getLowerLeft() {
        return lowerLeft;
    }
    
    public Point getUpperRight() {
        return upperRight;
    }
    
    public Optional<Claim> intersection(Claim claim) {
        // gives bottom-left point
        // of intersection rectangle
        int lowerX = Math.max(lowerLeft.getX(), claim.getLowerLeft().getX());
        int lowerY = Math.max(lowerLeft.getY(), claim.getLowerLeft().getY());
        
        // gives top-right point
        // of intersection rectangle
        int upperX = Math.min(upperRight.getX(), claim.getUpperRight().getX());
        int upperY = Math.min(upperRight.getY(), claim.getUpperRight().getY());
        
        // no intersection
        if (lowerX >= upperX || lowerY >= upperY) {
            return Optional.empty();
        }
        
        return Optional.of(new Claim(new Point(lowerX, lowerY), new Point(upperX, upperY)));
    }
    
    public int area() {
        return (upperRight.getX() - lowerLeft.getX()) * (upperRight.getY() - lowerLeft.getY());
    }
    
    public List<Point> points() {
        List<Point> points = new ArrayList<>();
        for (int x = lowerLeft.getX(); x < lowerLeft.getX() + width; x++) {
            for (int y = lowerLeft.getY(); y < lowerLeft.getY() + height; y++) {
                points.add(new Point(x, y));
            }
        }
        return points;
    }
    
    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", lowerLeft=" + lowerLeft +
                ", upperRight=" + upperRight +
                '}';
    }
    
    public int getId() {
        return id;
        
    }
}
