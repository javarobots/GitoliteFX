package main.gitolite.utility;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class SystemUtilityTest {

    @Test
    public void testGetSystemUsername()
    {
        assertThat(SystemUtility.getSyetmUserName(), not(nullValue()));
    }

}
