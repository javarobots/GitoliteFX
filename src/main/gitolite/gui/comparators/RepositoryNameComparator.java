package main.gitolite.gui.comparators;

import java.util.Comparator;

import main.gitolite.domain.models.ConfigRepo;

public class RepositoryNameComparator implements Comparator<ConfigRepo> {

    @Override
    public int compare(ConfigRepo o1, ConfigRepo o2)
    {
        return o1.getRepoName().get().compareToIgnoreCase(o2.getRepoName().get());
    }

}
