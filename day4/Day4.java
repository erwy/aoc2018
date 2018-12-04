import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class Day4 {
    
    public static void main(String[] args) {
        Task1 t1 = new Task1();
        t1.runIt();
        
        Task2 t2 = new Task2();
        t2.runIt();
    }
    
    public static class Task1 {
        String fileName = "resources/day4.txt";
        
        public Integer runIt() {
            
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                List<Event> eventsSorted = stream.map(Event::new)
                        .sorted(Comparator.comparing(Event::getLocalDateTime))
                        .collect(Collectors.toList());
                eventsSorted.forEach(System.out::println);
                
                int currentGuard = -1;
                Event currentFallAsleepEvent = null;
                Map<Integer, Map<Integer, List<LocalDate>>> guardToMinutesForDate = new HashMap<>();
                for (Event event : eventsSorted) {
                    if (event.getEventDescription().startsWith("Guard")) {
                        currentGuard = event.getGuard();
                    }
                    if (event.getEventDescription().startsWith("falls")) {
                        currentFallAsleepEvent = event;
                    }
                    if (event.getEventDescription().startsWith("wakes")) {
                        event.getLocalDateTime().toLocalDate();
                        int fallAsleep = currentFallAsleepEvent.getLocalDateTime().getMinute();
                        int wake = event.getLocalDateTime().getMinute();
                        System.out.println("Guard " + currentGuard + " sleep from " + fallAsleep + " " + wake);
                        Map<Integer, List<LocalDate>> minuteToDates = guardToMinutesForDate.getOrDefault(currentGuard, new HashMap<>());
                        guardToMinutesForDate.put(currentGuard, minuteToDates);
                        IntStream.range(fallAsleep, wake).forEach(minute -> {
                            System.out.println(minute);
                            List<LocalDate> listOfDates = minuteToDates.getOrDefault(minute, new ArrayList<>());
                            minuteToDates.put(minute, listOfDates);
                            listOfDates.add(event.getLocalDateTime().toLocalDate());
                        });
                    }
                }
                Optional<Map.Entry<Integer, Map<Integer, List<LocalDate>>>> max = guardToMinutesForDate.entrySet().stream().max((e1, e2) -> {
                    Integer collect = e1.getValue().values().stream().map(List::size).collect(Collectors.summingInt(Integer::intValue));
                    Integer collect1 = e2.getValue().values().stream().map(List::size).collect(Collectors.summingInt(Integer::intValue));
                    return collect.compareTo(collect1);
                });
                
                if (max.isPresent()) {
                    System.out.println("Guard: " + max.get().getKey());
                    Map<Integer, List<LocalDate>> value = max.get().getValue();
                    Optional<Map.Entry<Integer, List<LocalDate>>> max1 = value.entrySet().stream().max((e1, e2) -> {
                        int size = e1.getValue().size();
                        int size1 = e2.getValue().size();
                        return Integer.compare(size, size1);
                    });
                    
                    System.out.println("Minute: " + max1.get().getKey());
                }
                ;
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    
    
    public static class Task2 {
        String fileName = "resources/day4.txt";
        
        public Integer runIt() {
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                List<Event> eventsSorted = stream.map(Event::new)
                        .sorted(Comparator.comparing(Event::getLocalDateTime))
                        .collect(Collectors.toList());
                eventsSorted.forEach(System.out::println);
                
                int currentGuard = -1;
                Event currentFallAsleepEvent = null;
                Map<Integer, Map<Integer, List<LocalDate>>> guardToMinutesForDate = new HashMap<>();
                for (Event event : eventsSorted) {
                    if (event.getEventDescription().startsWith("Guard")) {
                        currentGuard = event.getGuard();
                    }
                    if (event.getEventDescription().startsWith("falls")) {
                        currentFallAsleepEvent = event;
                    }
                    if (event.getEventDescription().startsWith("wakes")) {
                        event.getLocalDateTime().toLocalDate();
                        int fallAsleep = currentFallAsleepEvent.getLocalDateTime().getMinute();
                        int wake = event.getLocalDateTime().getMinute();
                        System.out.println("Guard " + currentGuard + " sleep from " + fallAsleep + " " + wake);
                        Map<Integer, List<LocalDate>> minuteToDates = guardToMinutesForDate.getOrDefault(currentGuard, new HashMap<>());
                        guardToMinutesForDate.put(currentGuard, minuteToDates);
                        IntStream.range(fallAsleep, wake).forEach(minute -> {
                            System.out.println(minute);
                            List<LocalDate> listOfDates = minuteToDates.getOrDefault(minute, new ArrayList<>());
                            minuteToDates.put(minute, listOfDates);
                            listOfDates.add(event.getLocalDateTime().toLocalDate());
                        });
                    }
                }
                Optional<Map.Entry<Integer, Map<Integer, List<LocalDate>>>> max = guardToMinutesForDate.entrySet().stream().max((e1, e2) -> {
                    Integer integer = e1.getValue().values().stream().max(Comparator.comparing(List::size)).map(List::size).orElse(0);
                    Integer integer1 = e2.getValue().values().stream().max(Comparator.comparing(List::size)).map(List::size).orElse(0);
                    return integer.compareTo(integer1);
                });
                
                if (max.isPresent()) {
                    System.out.println("Guard: " + max.get().getKey());
                    Map<Integer, List<LocalDate>> value = max.get().getValue();
                    Optional<Map.Entry<Integer, List<LocalDate>>> max1 = value.entrySet().stream().max((e1, e2) -> {
                        int size = e1.getValue().size();
                        int size1 = e2.getValue().size();
                        return Integer.compare(size, size1);
                    });
                    
                    System.out.println("Minute: " + max1.get().getKey());
                }
                ;
                // 971 * 45
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
