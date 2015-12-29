package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.List;

public class ConfigRepoRule {
    
    private String permission;
    private List<String> branches;
    private List<String> groupsOrUsers;
    
    public ConfigRepoRule(String permission)
    {
        this.permission = permission;
        branches = new ArrayList<>();
        groupsOrUsers = new ArrayList<>();
    }
    
    public void addBranch(String branch)
    {
        this.branches.add(branch);
    }
    
    public List<String> getBranches()
    {
        return this.branches;
    }
    
    public void addGroupOrUser(String groupOrUser)
    {
        this.groupsOrUsers.add(groupOrUser);
    }
    
    public List<String> getGroupsAndUsers()
    {
        return this.groupsOrUsers;
    }
    
    public String getPermission()
    {
        return this.permission;
    }

}
