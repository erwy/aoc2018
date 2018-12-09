package day7;

public class Step {

    private final String prerequisite;
    private final String step;

    public Step(String prerequisite, String step) {
        this.prerequisite = prerequisite;
        this.step = step;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public String getStep() {
        return step;
    }
}
