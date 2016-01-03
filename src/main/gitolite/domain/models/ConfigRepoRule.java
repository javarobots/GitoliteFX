package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConfigRepoRule {
    
    private StringProperty permission;
    private StringProperty branchProperty;
    private StringProperty groupUserProperty;
    private List<String> branches;
    private List<String> groupsOrUsers;
    
    public ConfigRepoRule(String permission)
    {
        this.permission = new SimpleStringProperty(permission);
        branchProperty = new SimpleStringProperty("");
        groupUserProperty = new SimpleStringProperty("");
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
    
    public StringProperty getPermission()
    {
        return this.permission;
    }

}
