package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * ConfigGroup.java
 * 
 * Description: The Group class represents a group in the Gitolite configuration
 * file. A group, in the configuration file, represents a common set of users or
 * repositories.
 * 
 * @author jeramy
 *
 */
public class ConfigGroup {

	private String name;
	private String comments;

	private List<String> items;

	public ConfigGroup() {
		this("");
	}

	public ConfigGroup(String name) {
		this.name = name;
		this.items = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public List<String> getItems() {
		return this.items;
	}

	public void addItem(String item) {
		if(item != null && !item.isEmpty())
			this.items.add(item.trim());
	}

	public String getComments() {
		return this.comments;
	}

	public void addComments(String comments) {
		this.comments = comments.trim();
	}

}
