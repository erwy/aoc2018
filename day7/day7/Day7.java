package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {

    static String fileName = "resources/day7.txt";

    public static void main(String[] args) {
        Task1 t1 = new Task1();
        t1.runIt();

        Task2 t2 = new Task2();
        t2.runIt();
    }

    public static class Task1 {

        public Integer runIt() {

            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

                List<Step> allStepsData = stream.map(StepFactory::step).collect(Collectors.toList());
                Set<String> allPreReq = allStepsData.stream().map(Step::getPrerequisite).collect(Collectors.toSet());

                Map<String, Set<String>> stepToPreReq = allStepsData.stream().collect(Collectors.groupingBy(Step::getStep, Collectors.mapping(Step::getPrerequisite, Collectors.toSet())));

                Map<String, List<String>> preRequisiteToSteps = allStepsData.stream().collect(Collectors.groupingBy(Step::getPrerequisite, Collectors.mapping(Step::getStep, Collectors.toList())));

                Set<String> allSteps = new HashSet<>();
                allSteps.addAll(stepToPreReq.keySet());
                allSteps.addAll(preRequisiteToSteps.keySet());

                allPreReq.removeAll(stepToPreReq.keySet());
                TreeSet<String> withoutPreReq = new TreeSet<>(allPreReq);

                String currentDone = withoutPreReq.first();

                withoutPreReq.remove(currentDone);
                TreeSet<String> available = new TreeSet<>(withoutPreReq);

                List<String> done = new ArrayList<>();
                done.add(currentDone);

                while (done.size() < allSteps.size()) {
                    List<String> steps = preRequisiteToSteps.get(currentDone);
                    for (String step : steps) {
                        if (done.containsAll(stepToPreReq.get(step))) {
                            available.add(step);
                        }
                    }
                    currentDone = available.first();
                    done.add(currentDone);
                    available.remove(currentDone);
                }

                done.forEach(System.out::print);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
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
}
