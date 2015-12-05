package main.gitolite.domain.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ConfigGroups.java
 * 
 * Description: This class maintains one or more ConfigGroup models.
 * 
 * @author jeramy
 *
 */
public class ConfigGroups implements Iterable<ConfigGroup> {

	private List<ConfigGroup> groups;

	public ConfigGroups() {
		this.groups = new ArrayList<>();
	}

	public void add(ConfigGroup g) {
		this.groups.add(g);
	}
	
	public void remove(ConfigGroup g) {
		this.groups.remove(g);
	}
	
	public ConfigGroup get(int index) {
		if(index >= groups.size())
			throw new ArrayIndexOutOfBoundsException(index);
		
		return groups.get(index);
	}
	
	@Override
	public Iterator<ConfigGroup> iterator() {
		return groups.iterator();
	}

}
