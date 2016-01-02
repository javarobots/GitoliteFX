package main.gitolite.utility;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class ButtonUtility {
    
    public static void setIconOnButton(Button button, FontAwesomeIcon icon)
    {
        button.setText("");
        button.setGraphic(GlyphsDude.createIcon(icon));
    }
    
    public static void setIconOnButtonAndTooltip(Button button, FontAwesomeIcon icon, String tooltip)
    {
        setIconOnButton(button, icon);
        button.setTooltip(new Tooltip(tooltip));
    }
    
    public static void setIconOnButtonAndTooltipWithInitialState(Button button, FontAwesomeIcon icon, String tooltip, boolean disabled)
    {
        setIconOnButtonAndTooltip(button, icon, tooltip);
        button.setDisable(disabled);
    }

}
