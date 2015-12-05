package main.gitolite.domain.models;

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
	
	public ConfigModel() {
		this.groups = new ConfigGroups();
	}

	public void add(ConfigGroup g) {
		this.groups.add(g);
	}

	public ConfigGroups getGroups() {
		return this.groups;
	}
}
