package Controller.Menu;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuItem extends ToggleButton {
    private String value;
    private String image_path;
    Image img ;
    ImageView view ;
    public MenuItem(String value, String image_path) {
        super(value);
        img = new Image(String.valueOf(MenuItem.class.getResource(image_path)));
        view = new ImageView(img);
        view.setFitHeight(26);
        view.setPreserveRatio(true);
        super.setGraphic(view);
        super.setId("menu_item");
    }
}
