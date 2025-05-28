module org.remindly.remindly {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires jdk.jdi;
    requires java.sql;

    opens org.remindly to javafx.fxml;
    exports org.remindly;
    exports org.remindly.controller;
    opens org.remindly.controller to javafx.fxml;
}