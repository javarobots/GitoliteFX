package main.gitolite.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import main.gitolite.domain.models.ApplicationModel;

public class LaunchGitBash {
    
    private File openBatchFile;
    
    public boolean doesExecutableExist()
    {
        openBatchFile = new File(getClass().getResource("/resource/openBash.bat").getFile());
        return openBatchFile.exists();
    }
    
    public void launchGitBash()
    {
        try
        {
            //Copy file from .jar
            File localLaunchFile = new File("openBatchFile.bat");
            localLaunchFile.deleteOnExit();
            Files.copy(getClass().getResourceAsStream("/resource/openBash.bat"), localLaunchFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            ProcessBuilder pb = new ProcessBuilder(localLaunchFile.getAbsolutePath(), SystemUtility.getSyetmUserName(), ApplicationModel.getInstance().getRepoDirectory().getAbsolutePath());
            pb.start();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
