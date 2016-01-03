package main.gitolite.domain.parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;

import com.sun.org.apache.regexp.internal.REProgram;

import main.gitolite.domain.models.ConfigGroup;
import main.gitolite.domain.models.ConfigModel;
import main.gitolite.domain.models.ConfigRepo;
import main.gitolite.domain.models.ConfigRepoRule;
import main.gitolite.utility.StringUtility;

public class GitoliteConfParser {

	public GitoliteConfParser() {
	}

	public ConfigModel parse(String conf) {
		ConfigModel configModel = new ConfigModel();
		ConfigRepo currentRepo = null;
		
		String[] lines = conf.split("\n");
		boolean inRepo = false;

		for (String line : lines) {
		    //Process a group line
			if (lineRepresentsAGroup(line)) {
				configModel.add(buildGroup(line));
			}
			
			//Process line within a repo
			if (inRepo)
			{
			    if (lineRepresentsARepo(line))
			    {
			        inRepo = false;
			    }
			    else
			    {
			        processLineWithinARepo(currentRepo, line);
			    }
			}

			//Process a repo line
			if (lineRepresentsARepo(line) && !inRepo) {
			    currentRepo = buildRepo(line);
			    configModel.addRepo(currentRepo);
			    inRepo = true;
			}
		}

		return configModel;
	}
	
	private void processLineWithinARepo(ConfigRepo repo, String line)
	{
	    String[] lineValues = line.split("\\s+");
	    if (StringUtility.isStringValueARepoRule(lineValues[0]))
	    {
	        ConfigRepoRule rule = new ConfigRepoRule(lineValues[0]);
	        int valueIndex = 1;
	        //Process branches
	        do
	        {
	            if (!lineValues[valueIndex].equalsIgnoreCase("="))
	            {
	                rule.addBranch(lineValues[valueIndex]);
	            }
	        }while(!lineValues[valueIndex++].equalsIgnoreCase("="));
	        
	        //Process groups and users
	        do
	        {
	            if (!lineValues[valueIndex].startsWith("#"))
	            {
	                rule.addGroupOrUser(lineValues[valueIndex]);
	            }
	        }while(!lineValues[valueIndex++].startsWith("#") && valueIndex < lineValues.length);
	        
	        repo.addRule(rule);
	    }
	}

	public void parse(Path path) throws IOException {
		if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS))
			throw new FileNotFoundException();

		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
	}

	private boolean lineRepresentsAGroup(String line) {
		return line.startsWith("@");
	}

	private boolean lineRepresentsARepo(String line) {
		return line.startsWith("repo");
	}

	private ConfigGroup buildGroup(String line) {
		String[] groupString = removeEquals(line).split(" ");
		ConfigGroup group = new ConfigGroup();
		group.setName(groupString[0]);
		groupString = removeGroupNameFromStringArray(groupString);
		buildGroupItemsAndComments(group, groupString);
		return group;
	}
	
	private ConfigRepo buildRepo(String line)
	{
	    ConfigRepo returnRepo = null;
	    int index = line.indexOf(" ");
	    if (index == -1)
	    {
	        return returnRepo;
	    }
	    
	    String repoName = line.substring(++index).trim();
	    index = repoName.indexOf("#");
	    if (index > -1)
	    {
	        repoName = repoName.substring(0, index).trim();
	    }
	    returnRepo = new ConfigRepo(repoName);
	    return returnRepo;
	}

	private void buildGroupItemsAndComments(ConfigGroup group, String[] groupString) {
		int length = groupString.length;
		for(int i = 0; i < length; i++) {
			if(!groupString[i].isEmpty())
			{
				if(isBeginningOfComments(groupString[i])) {
					addCommentsToGroup(Arrays.copyOfRange(groupString, i, length), group);
					break;
				}
				group.addItem(groupString[i]);
			}
		}
	}

	private void addCommentsToGroup(String[] stringArray, ConfigGroup group) {
		StringBuilder comment = new StringBuilder();
		for (String word : stringArray) {
			if (!word.isEmpty())
				comment.append(word).append(" ");
		}
		group.addComments(comment.toString());
	}

	private boolean isBeginningOfComments(String s) {
		return s.startsWith("#");
	}

	private String[] removeGroupNameFromStringArray(String[] groupString) {
		return Arrays.copyOfRange(groupString, 1, groupString.length);
	}

	private String removeEquals(String line) {
		return line.replace("=", "").trim();
	}

}
