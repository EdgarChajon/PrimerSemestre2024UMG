module com.mycompany.proyectoumgbiblioteca2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.proyectoumgbiblioteca2024 to javafx.fxml;
    exports com.mycompany.proyectoumgbiblioteca2024;
}
