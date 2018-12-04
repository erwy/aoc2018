import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
	
	public static void main(String[] args) {
//		Task1 t1 = new Task1();
//		t1.runIt();
		
		Task2 t2 = new Task2();
		t2.runIt();
	}
	
	public static class Task1 {
		String fileName = "resources/day3.txt";
		
		public Integer runIt() {
			
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
				
				List<Claim> intersections = findIntersections(stream);
				Set<Point> collect = intersections.stream().map(c -> c.points()).flatMap(List::stream).collect(Collectors.toSet());
				
				
				System.out.println("AREA: " + collect.size());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private static List<Claim> findIntersections(Stream<String> stream) {
		List<Claim> intersections = new ArrayList<>();
		List<Claim> claims = stream.map(Claim::new).collect(Collectors.toList());
		
		for (int i = 0; i < claims.size(); i++) {
			int size = intersections.size();
			Claim claim = claims.get(i);
			for (int j = i + 1; j < claims.size(); j++) {
				claim.intersection(claims.get(j)).ifPresent(intersection -> intersections.add(intersection));
			}
			if (size == intersections.size()) {
				System.out.println(claim.getId());
			}
		}
		return intersections;
	}
	
	private static Set<Integer> findIncludedIdsInIntersections(List<Claim> claims) {
		Set<Integer> intersections = new HashSet<>();
		
		for (int i = 0; i < claims.size(); i++) {
			Claim claim = claims.get(i);
			for (int j = i + 1; j < claims.size(); j++) {
				Claim intersecting = claims.get(j);
				claim.intersection(intersecting).ifPresent(intersection -> {
					intersections.add(claim.getId());
					intersections.add(intersecting.getId());
				});
			}
		}
		return intersections;
	}
	
	public static class Task2 {
		String fileName = "resources/day3.txt";
		Set<String> capturedWords = new HashSet<>();
		
		public Integer runIt() {
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
				List<Claim> claims = stream.map(Claim::new).collect(Collectors.toList());
				Set<Integer> includedIdsInIntersections = findIncludedIdsInIntersections(claims);
				Set<Integer> allClaimIds = claims.stream().map(Claim::getId).collect(Collectors.toSet());
				allClaimIds.removeAll(includedIdsInIntersections);
				allClaimIds.forEach(System.out::println);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}
}
