package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import main.gitolite.gui.comparators.RepositoryNameComparator;

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
    
    public List<ConfigRepo> getSortedRepos()
    {
        Collections.sort(repos, new RepositoryNameComparator());
        return repos;
    }

    @Override
    public Iterator<ConfigRepo> iterator()
    {
        return repos.iterator();
    }
}
