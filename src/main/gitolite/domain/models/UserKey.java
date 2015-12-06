package main.gitolite.domain.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserKey {
    
    private StringProperty userName;
    private StringProperty keyValue;
    
    public UserKey(String userName, String keyValue)
    {
        this.userName = new SimpleStringProperty(userName);
        this.keyValue = new SimpleStringProperty(keyValue);
    }
    
    public StringProperty userNameProperty()
    {
        return this.userName;
    }
    
    public StringProperty keyValueProperty()
    {
        return this.keyValue;
    }

}
