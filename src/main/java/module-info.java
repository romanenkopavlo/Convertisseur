module fr.btsciel.convertisseur {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.btsciel.convertisseur to javafx.fxml;
    exports fr.btsciel.convertisseur;
}