import java.util.regex.Pattern;

public class ClaimPattern {
    
    
    private static String re1=".*?";	// Non-greedy match on filler
    private static String re2="(\\d+)";	// Integer Number 1
    private static String re3=".*?";	// Non-greedy match on filler
    private static String re4="(\\d+)";	// Integer Number 2
    private static String re5=".*?";	// Non-greedy match on filler
    private static String re6="(\\d+)";	// Integer Number 3
    private static String re7=".*?";	// Non-greedy match on filler
    private static String re8="(\\d+)";	// Integer Number 4
    private static String re9=".*?";	// Non-greedy match on filler
    private static String re10="(\\d+)";	// Integer Number 5
    private static Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    
    public static Pattern getPattern() {
        return p;
    }
}
