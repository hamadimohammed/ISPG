package Interfaces;

import javafx.scene.input.KeyEvent;

public interface GestionController {
    void initialize();
    void insert();
    void update();
    void delete();

    void load_permission();
    void load_data();
    void editable_cols();
    void properties();
    void events(KeyEvent event);
}
