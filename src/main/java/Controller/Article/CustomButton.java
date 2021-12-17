package Controller.Article;

import Controller.Menu.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomButton extends ToggleButton {
    private String value;
    private String image_path;
    Image img ;
    ImageView view ;
    public CustomButton(String value, String image_path,String id) {
        super(value);
        img = new Image(String.valueOf(MenuItem.class.getResource(image_path)));
        view = new ImageView(img);
        view.setFitHeight(26);
        view.setPreserveRatio(true);
        this.getStyleClass().add("btns");
        super.setGraphic(view);
    }
}
