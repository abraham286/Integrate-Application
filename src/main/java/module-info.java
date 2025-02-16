module org.example.integratedapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.integratedapplication to javafx.fxml;
    exports org.example.integratedapplication;
}