package day8;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final int numOfChilds;
    private final int numMetaData;
    private List<Node> childNodes;
    private List<Integer> metaData;

    public Node(int numOfChilds, int numMetaData) {
        this.numOfChilds = numOfChilds;
        this.numMetaData = numMetaData;
        childNodes = new ArrayList<>();
        metaData = new ArrayList<>();
    }

    public void addChilds(List<Node> nodes) {
        childNodes.addAll(nodes);
    }

    public void addChild(Node nodes) {
        childNodes.add(nodes);
    }

    public void addMetaData(Integer metaData) {
        this.metaData.add(metaData);
    }

    public int getNumOfChilds() {
        return numOfChilds;
    }

    public int getNumMetaData() {
        return numMetaData;
    }

    public int getSumOfNumberOfMetaData() {
        return getAllChildNodes().stream()
                .map(Node::getNumMetaData)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getSumOfNumberOfChildNodes() {
        return getAllChildNodes().stream()
                .map(Node::getNumOfChilds)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<Node> getAllChildNodes() {
        List<Node> a = new ArrayList<>();
        childNodes.forEach(n -> a.addAll(n.getAllChildNodes()));
        a.addAll(childNodes);
        return a;
    }


    public List<Node> getChildNodes() {
        return childNodes;
    }

    public List<Integer> getMetaData() {
        return metaData;
    }
}
