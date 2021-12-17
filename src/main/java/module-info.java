module ispg {
    requires javafx.controls;
    requires javafx.fxml;

    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens Application to javafx.fxml;
    exports Application;
    opens Controller.Login to javafx.fxml,javafx.graphics;
    exports Controller.Login;
    opens Controller.Menu to javafx.fxml;
    exports Controller.Menu;
    opens Controller.Article to javafx.fxml;
    exports Controller.Article;
    opens Controller.Client to javafx.fxml;
    exports Controller.Client;

    exports Interfaces;
    opens Interfaces to javafx.fxml;

    opens Assets to javafx.fxml,javafx.graphics;

    exports Model.Article;
    opens Model.Article;

}