package main.gitolite.domain.parsers;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.gitolite.domain.mocks.MockConf;
import main.gitolite.domain.models.ConfigGroup;
import main.gitolite.domain.models.ConfigModel;
import main.gitolite.domain.parsers.GitoliteConfParser;

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

}
