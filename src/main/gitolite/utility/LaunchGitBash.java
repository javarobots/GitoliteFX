package main.gitolite.utility;

import java.io.File;
import java.io.IOException;

public class LaunchGitBash {
    
    private String execPath = "AppData/Local/Programs/Git/git-bash.exe";
    
    public String getUserName()
    {
        return System.getProperty("user.name");
    }
    
    public boolean doesExecutableExist()
    {
        StringBuilder filePathBuilder = new StringBuilder("C:/Users/");
        filePathBuilder.append(getUserName()).append("/");
        filePathBuilder.append(execPath);
        File execFile = new File(execPath());
        return execFile.exists();
    }
    
    private String execPath()
    {
        StringBuilder filePathBuilder = new StringBuilder("C:/Users/");
        filePathBuilder.append(getUserName()).append("/");
        filePathBuilder.append(execPath);
        return filePathBuilder.toString();
    }
    
    public void launchGitBash()
    {
        try
        {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", execPath(), "--cd-to-home");
            pb.start();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
