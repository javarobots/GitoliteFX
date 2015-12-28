package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConfigRepos implements Iterable<ConfigRepo> {
    
    private List<ConfigRepo> repos;
    
    public ConfigRepos()
    {
        this.repos = new ArrayList<>();
    }
    
    public void addRepo(ConfigRepo repo)
    {
        this.repos.add(repo);
    }

    @Override
    public Iterator<ConfigRepo> iterator()
    {
        return repos.iterator();
    }
}
