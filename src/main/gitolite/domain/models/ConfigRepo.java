package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConfigRepo {
    
    private StringProperty repoName;
    private List<ConfigRepoRule> rules;
    
    public ConfigRepo(String repoName)
    {
        this.repoName = new SimpleStringProperty(repoName);
        this.rules = new ArrayList<>();
    }
    
    public StringProperty getRepoName()
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
