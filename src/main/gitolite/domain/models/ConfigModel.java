package main.gitolite.domain.models;

import java.util.HashMap;
import java.util.Map;

/**
 * GitoliteConfigModel.java
 * 
 * Description: The gitolite.conf file holds information about repositories that
 * the Gitolite Application manages. A repository, in the configuration file, is
 * represented by a name and a list of users or groups that have permissions to
 * read and/or write to the repository.
 * 
 * @author jeramy
 *
 */
public class ConfigModel {

	private ConfigGroups groups;
	private ConfigRepos repos;
	private Map<String, ConfigGroup> nameToGroupMap;
	
	public ConfigModel() {
		this.groups = new ConfigGroups();
		this.repos = new ConfigRepos();
		nameToGroupMap = new HashMap<>();
	}

	public void add(ConfigGroup g) {
		this.groups.add(g);
		this.nameToGroupMap.put(g.getName(), g);
	}
	
	public ConfigGroup getGroupByName(String name)
	{
	    return this.nameToGroupMap.get(name);
	}

	public ConfigGroups getGroups() {
		return this.groups;
	}
	
	public void addRepo(ConfigRepo repo)
	{
	    this.repos.addRepo(repo);
	}
	
	public ConfigRepos getRepos()
	{
	    return this.repos;
	}
}
