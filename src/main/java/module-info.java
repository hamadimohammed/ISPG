module ispg {
    requires javafx.controls;
    requires javafx.fxml;

    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens Application to javafx.fxml;
    exports Application;

}