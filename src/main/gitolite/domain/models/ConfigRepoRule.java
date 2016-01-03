package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.gitolite.utility.StringUtility;

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
        this.branchProperty.set(StringUtility.convertListToStringForTable(branches));
    }
    
    public List<String> getBranches()
    {
        return this.branches;
    }
    
    public StringProperty branchProperty()
    {
        return this.branchProperty;
    }
    
    public void addGroupOrUser(String groupOrUser)
    {
        this.groupsOrUsers.add(groupOrUser);
        this.groupUserProperty.set(StringUtility.convertListToStringForTable(groupsOrUsers));
    }
    
    public List<String> getGroupsAndUsers()
    {
        return this.groupsOrUsers;
    }
    
    public StringProperty groupUserProperty()
    {
        return this.groupUserProperty;
    }
    
    public StringProperty permissionProperty()
    {
        return this.permission;
    }

}
