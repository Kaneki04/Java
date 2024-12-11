module com.japanese.kanjizy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.japanese.kanjizy to javafx.fxml;
    exports com.japanese.kanjizy;
}