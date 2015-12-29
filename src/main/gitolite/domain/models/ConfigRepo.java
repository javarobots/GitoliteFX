package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.List;

public class ConfigRepo {
    
    private String repoName;
    private List<ConfigRepoRule> rules;
    
    public ConfigRepo(String repoName)
    {
        this.repoName = repoName;
        this.rules = new ArrayList<>();
    }
    
    public String getName()
    {
        return this.repoName;
    }
    
    public List<ConfigRepoRule> getRules()
    {
        return this.rules;
    }
    
    public void addRule(ConfigRepoRule rule)
    {
        this.rules.add(rule);
    }

}
