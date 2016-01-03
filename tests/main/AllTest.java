package main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   main.gitolite.domain.models.ConfigGroupsTests.class,
   main.gitolite.domain.parsers.GitoliteConfParserTests.class,
   main.gitolite.domain.parsers.AboutBuildParserTest.class,
   main.gitolite.utility.StringUtilityTest.class
})
public class AllTest {

}
