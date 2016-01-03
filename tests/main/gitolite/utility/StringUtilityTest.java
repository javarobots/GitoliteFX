package main.gitolite.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import main.gitolite.domain.mocks.MockArrayList;

public class StringUtilityTest {
    
    @Test
    public void testRWPermission()
    {
        System.out.println("Test: RW permission");
        assertTrue(StringUtility.isStringValueARepoRule("RW"));
    }
    
    @Test
    public void testRWPlusPermission()
    {
        System.out.println("Test: RW+ permission");
        assertTrue(StringUtility.isStringValueARepoRule("RW+"));
    }
    
    @Test
    public void testRPermission()
    {
        System.out.println("Test: R permission");
        assertTrue(StringUtility.isStringValueARepoRule("R"));
    }
    
    @Test
    public void testDenyPermission()
    {
        System.out.println("Test: Deny permission");
        assertTrue(StringUtility.isStringValueARepoRule("-"));
    }
    
    @Test
    public void testInvalidPermission()
    {
        System.out.println("Test: Invalid permission");
        assertFalse(StringUtility.isStringValueARepoRule("#"));
    }
    
    @Test
    public void testConvertListToString()
    {
        System.out.println("Test: Convert List To String");
        assertEquals(MockArrayList.expectedListConversion, StringUtility.convertListToStringForTable(MockArrayList.getList()));
    }

}
