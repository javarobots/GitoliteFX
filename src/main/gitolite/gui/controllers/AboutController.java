package main.gitolite.gui.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.gitolite.domain.parsers.AboutBuildParser;

public class AboutController {
    
    @FXML private Label buildNumberLabel;
    @FXML private Label buildDateLabel;
    
    public void initialize()
    {
        AboutBuildParser buildParser = new AboutBuildParser(readInfoFile());
        buildNumberLabel.setText(buildParser.getBuildNumber());
        buildDateLabel.setText(buildParser.getBuildDate());
    }

    private String readInfoFile()
    {
        String dataLine = null;
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/resource/buildInfo.txt")));
            dataLine = reader.readLine();
            reader.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataLine;
    }

}
