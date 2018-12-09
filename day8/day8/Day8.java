package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {

    static String fileName = "resources/day8.txt";

    public static void main(String[] args) {
        Task1 t1 = new Task1();
        t1.runIt();

        Task2 t2 = new Task2();
        t2.runIt();
    }

    public static class Task1 {

        public Integer runIt() {

            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                String[] s = stream.findFirst().get().split(" ");
                Node node = createNode(s, 0);
                int rootNodeSum = node.getMetaData().stream().mapToInt(Integer::intValue).sum();
                int childNodeSum = node.getAllChildNodes().stream().mapToInt(n -> n.getMetaData().stream().mapToInt(Integer::intValue).sum()).sum();
                System.out.println(rootNodeSum + childNodeSum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }


    public static class Task2 {


        public Integer runIt() {
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                String[] s = stream.findFirst().get().split(" ");
                Node node = createNode(s, 0);
                int value = calculateValue(node);
                System.out.println(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        private int calculateValue(Node node) {
            if (node.getChildNodes().isEmpty()) {
                return node.getMetaData().stream().mapToInt(Integer::intValue).sum();
            }
            List<Node> childNodes = node.getChildNodes();
            int size = childNodes.size();
            List<Integer> actualMetaData = node.getMetaData().stream().filter(md -> md <= size).collect(Collectors.toList());
            return actualMetaData.stream().map(amd -> calculateValue(childNodes.get(amd - 1))).mapToInt(Integer::intValue).sum();
        }
    }

    private static Node createNode(String[] s, int startIndex) {
        int index = startIndex;
        int childNodes = Integer.parseInt(s[index]);
        int numMetaData = Integer.parseInt(s[index + 1]);

        Node n = new Node(childNodes, numMetaData);

        for (int i = 0; i < childNodes; i++) {
            index = startIndex + (n.getSumOfNumberOfChildNodes() + i + 1) * 2 + n.getSumOfNumberOfMetaData();
            Node subNode = createNode(s, index);
            n.addChild(subNode);
        }

        index = startIndex + 2 + (n.getSumOfNumberOfChildNodes() + n.getNumOfChilds()) * 2 + n.getSumOfNumberOfMetaData();

        for (int j = index; j < index + numMetaData; j++) {
            n.addMetaData(Integer.parseInt(s[j]));
        }

        return n;
    }
}
