package main.gitolite.utility;

import org.junit.Test;

/**
 * This set of tests should not be automated with the test suite.
 * It was written for testing LaunchGitBash in the windows 
 * environment and would fail it ran automatically via Jenkins or
 * other CI tools.
 */
public class LaunchGitBashTest {

    @Test
    public void testLaunchExecutable()
    {
        LaunchGitBash instance = new LaunchGitBash();
        instance.launchGitBash();
    }

}
