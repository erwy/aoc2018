package day7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepFactory {
    static String txt="Step C must be finished before step A can begin.";

    static String re1=".*?";	// Non-greedy match on filler
    static String re2="(?:[a-z][a-z0-9_]*)";	// Uninteresting: var
    static String re3=".*?";	// Non-greedy match on filler
    static String re4="((?:[a-z][a-z0-9_]*))";	// Variable Name 1
    static String re5=".*?";	// Non-greedy match on filler
    static String re6="(?:[a-z][a-z0-9_]*)";	// Uninteresting: var
    static String re7=".*?";	// Non-greedy match on filler
    static String re8="(?:[a-z][a-z0-9_]*)";	// Uninteresting: var
    static String re9=".*?";	// Non-greedy match on filler
    static String re10="(?:[a-z][a-z0-9_]*)";	// Uninteresting: var
    static String re11=".*?";	// Non-greedy match on filler
    static String re12="(?:[a-z][a-z0-9_]*)";	// Uninteresting: var
    static String re13=".*?";	// Non-greedy match on filler
    static String re14="(?:[a-z][a-z0-9_]*)";	// Uninteresting: var
    static String re15=".*?";	// Non-greedy match on filler
    static String re16="((?:[a-z][a-z0-9_]*))";	// Variable Name 2



    public static Step step(String instruction) {
        Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10+re11+re12+re13+re14+re15+re16,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(instruction);
        if (m.find())
        {
            String var1=m.group(1);
            String var2=m.group(2);
            return new Step(var1, var2);
        }
        return null;
    }
}
