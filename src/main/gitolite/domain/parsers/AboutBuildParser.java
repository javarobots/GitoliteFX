package main.gitolite.domain.parsers;

public class AboutBuildParser {
    
    private String fileContents;
    private String buildNumber;
    private String buildDate;
    
    public AboutBuildParser(String aboutFileContents)
    {
        this.fileContents = aboutFileContents;
        this.parse();
    }
    
    public String getBuildNumber()
    {
        return buildNumber;
    }
    
    public String getBuildDate()
    {
        return buildDate;
    }
    
    private void parse()
    {
        String[] splitFile = fileContents.split("\\|");
        buildNumber = splitFile[0].trim();
        buildDate = splitFile[1].trim();
    }

}
