package main.gitolite.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTester {
    
    /**
     * Tests whether a value is a valid start to a repo permission
     * line. The values possible are R, -, RW, and RW+
     * @param value the test value
     * @return true if valid repo rule
     */
    public static boolean isStringValueARepoRule(String value)
    {
        Pattern p = Pattern.compile("(R|-|RW|RW+)");
        Matcher m = p.matcher(value);
        return m.find();
    }

}
