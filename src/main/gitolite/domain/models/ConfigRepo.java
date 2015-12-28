package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.List;

public class ConfigRepo {
    
    private String repoName;
    private List<String> users;
    private List<ConfigGroup> groups;
    
    public ConfigRepo(String repoName)
    {
        this.users = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.repoName = repoName;
    }
    
    public String getName()
    {
        return this.repoName;
    }
    
    public List<String> getUsers()
    {
        return this.users;
    }
    
    public void addUser(String user)
    {
        this.users.add(user);
    }
    
    public List<ConfigGroup> getGroups()
    {
        return this.groups;
    }
    
    public void addGroup(ConfigGroup group)
    {
        this.groups.add(group);
    }

}
