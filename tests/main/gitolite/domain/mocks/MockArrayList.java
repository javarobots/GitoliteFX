package main.gitolite.domain.mocks;

import java.util.ArrayList;
import java.util.List;

public class MockArrayList {

    public static String expectedListConversion = "One\nTwo\nThree";
    
    public static List<String> getList()
    {
        List<String> mock = new ArrayList<>();
        mock.add("One");
        mock.add("Two");
        mock.add("Three");
        return mock;
    }

}
