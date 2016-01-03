package main.gitolite.utility;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtility {
    
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
    
    public static String convertListToStringForTable(List<String> listToConvert)
    {
        StringBuilder builder = new StringBuilder();
        listToConvert.forEach(item -> builder.append(item).append("\n"));
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

}
