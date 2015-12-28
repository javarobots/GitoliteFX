package main.gitolite.domain.parsers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.gitolite.domain.mocks.MockConf;
import main.gitolite.domain.models.ConfigGroup;
import main.gitolite.domain.models.ConfigModel;
import main.gitolite.domain.models.ConfigRepo;

public class GitoliteConfParserTests {

	@Test
	public final void parseDoesNotProduceANullConfigModel() {
		// Arrange
		GitoliteConfParser confParser = new GitoliteConfParser();
		MockConf mockFile = new MockConf();

		// Act
		ConfigModel configModel = confParser.parse(mockFile.file);

		// Assert
		assertThat(configModel, notNullValue());
	}

	@Test
	public final void parseProducesAConfigModelThatContainsGroups() {
		// Arrange
		GitoliteConfParser confParser = new GitoliteConfParser();
		MockConf mockFile = new MockConf();

		// Act
		ConfigModel configModel = confParser.parse(mockFile.file);
		List<String> groupNames = new ArrayList<>();
		configModel.getGroups().forEach(g -> groupNames.add(g.getName()));

		// Assert
		assertThat(groupNames, hasItems("@staff", "@projects"));

	}

	@Test
	public final void parseProducesAConfigModelThatContainsGroupsThatContainItems() {
		// Arrange
		GitoliteConfParser confParser = new GitoliteConfParser();
		MockConf mockFile = new MockConf();

		// Act
		ConfigModel configModel = confParser.parse(mockFile.file);
		List<String> items = new ArrayList<>();
		configModel.getGroups().forEach(g -> {
			g.getItems().forEach(item -> items.add(item));
		});

		// Assert
		assertThat(items, hasItems("dilbert", "alice", "foo", "bar"));
	}

	@Test
	public final void parseProducesAConfigModelThatContainsGroupsThatContainComments() {
		// Arrange
		GitoliteConfParser confParser = new GitoliteConfParser();
		MockConf mockFile = new MockConf();

		// Act
		ConfigModel configModel = confParser.parse(mockFile.file);
		ConfigGroup group = configModel.getGroups().get(0);

		// Assert
		assertThat(group.getComments(), containsString("groups"));
	}
	
	@Test
	public final void parseProducesAConfigModelThatContainsRepos()
	{
	    // Arrange
        GitoliteConfParser confParser = new GitoliteConfParser();
        MockConf mockFile = new MockConf();

        // Act
        ConfigModel configModel = confParser.parse(mockFile.file);
        List<ConfigRepo> repos = new ArrayList<>();
        configModel.getRepos().forEach(repo -> repos.add(repo));
        ConfigRepo repo = repos.get(0);
        
        //Assert
        assertEquals(1, repos.size());
        assertThat(repo.getName(), containsString("@projects baz"));
	}

}
