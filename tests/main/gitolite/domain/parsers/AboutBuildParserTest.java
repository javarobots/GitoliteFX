package main.gitolite.domain.parsers;

import static org.junit.Assert.*;

import org.junit.Test;

import main.gitolite.domain.mocks.MockBuildInfo;

public class AboutBuildParserTest {

    @Test
    public void getBuildNumberTest()
    {
        AboutBuildParser instance = new AboutBuildParser(MockBuildInfo.BUILD_INFO);
        assertEquals("r98",instance.getBuildNumber());
    }

    @Test
    public void getBuildDateTest()
    {
        AboutBuildParser instance = new AboutBuildParser(MockBuildInfo.BUILD_INFO);
        assertEquals("2015-12-12", instance.getBuildDate());
    }
}
