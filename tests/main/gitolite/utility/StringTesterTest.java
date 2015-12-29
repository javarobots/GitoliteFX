package main.gitolite.utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringTesterTest {

    @Test
    public void testRWPermission()
    {
        System.out.println("Test: RW permission");
        assertTrue(StringTester.isStringValueARepoRule("RW"));
    }
    
    @Test
    public void testRWPlusPermission()
    {
        System.out.println("Test: RW+ permission");
        assertTrue(StringTester.isStringValueARepoRule("RW+"));
    }
    
    @Test
    public void testRPermission()
    {
        System.out.println("Test: R permission");
        assertTrue(StringTester.isStringValueARepoRule("R"));
    }
    
    @Test
    public void testDenyPermission()
    {
        System.out.println("Test: Deny permission");
        assertTrue(StringTester.isStringValueARepoRule("-"));
    }
    
    @Test
    public void testInvalidPermission()
    {
        System.out.println("Test: Invalid permission");
        assertFalse(StringTester.isStringValueARepoRule("#"));
    }

}
