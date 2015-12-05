/**
 * 
 */
package gitolite.domain.models;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import main.gitolite.domain.models.ConfigGroup;
import main.gitolite.domain.models.ConfigGroups;

/**
 * ConfigGroupsTests.java
 * 
 * Description: Unit tests for the ConfigGroups model
 * 
 * @author jeramy
 *
 */
public class ConfigGroupsTests {

	/**
	 * Test method for
	 * {@link main.gitolite.domain.models.ConfigGroups#add(main.gitolite.domain.models.ConfigGroup)}
	 * .
	 */
	@Test
	public final void testAdd() {
		// Arrange
		ConfigGroups groups = new ConfigGroups();
		ConfigGroup newGroup = new ConfigGroup("newGroup");

		// Act
		groups.add(newGroup);

		// Assert
		assertThat(groups, hasItem(newGroup));
	}

	/**
	 * Test method for
	 * {@link main.gitolite.domain.models.ConfigGroups#remove(main.gitolite.domain.models.ConfigGroup)}
	 * .
	 */
	@Test
	public final void testRemove() {
		// Arrange
		ConfigGroups groups = new ConfigGroups();
		ConfigGroup newGroup = new ConfigGroup("newGroup");

		// Act
		groups.add(newGroup);
		groups.remove(newGroup);

		// Assert
		assertThat(groups, not(hasItem(newGroup)));
	}

}
