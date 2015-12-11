package main.gitolite.domain.models;

import java.io.File;
import java.util.prefs.Preferences;

public class ApplicationModel {

    private static ApplicationModel instance;
    private Preferences prefs;
    private static String DIR_KEY = "repoDirectory";
    private static String DIR_DEFAULT = "noDirectoryDefined";
    
    private ApplicationModel()
    {
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }
    
    public static ApplicationModel getInstance()
    {
        if (instance == null)
        {
            instance = new ApplicationModel();
        }
        return instance;
    }
    
    public boolean isRepoDirectoryDefined()
    {
        if (prefs.get(DIR_KEY, DIR_DEFAULT).equalsIgnoreCase(DIR_DEFAULT))
        {
            return false;
        }
        
        if (getDotGitDir().exists() && getConfDir().exists() && getKeyDir().exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void setRepoDirectory(File directory)
    {
        this.prefs.put(DIR_KEY, directory.getAbsolutePath());
    }
    
    public File getRepoDirectory()
    {
        return new File (prefs.get(DIR_KEY, DIR_KEY));
    }
    
    public File getDotGitDir()
    {
        String baseDir = prefs.get(DIR_KEY, DIR_DEFAULT);
        return new File(baseDir, ".git");
    }
    
    public File getConfDir()
    {
        String baseDir = prefs.get(DIR_KEY, DIR_DEFAULT);
       return new File(baseDir, "conf");
    }
    
    public File getKeyDir()
    {
        String baseDir = prefs.get(DIR_KEY, DIR_DEFAULT);
        return new File(baseDir, "keyDir");
    }
    
}
