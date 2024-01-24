package fr.btsciel.convertisseur;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label label_Init;
    public Label label_Final;
    public TextField textField_Init;
    public TextField textField_Final;
    public Button buttonConvertion;
    public double taux_Euro_Dollar;
    public double taux_Euro_Livre;
    public double taux_Euro_Yen;
    public double valeur_Conversion;
    public DecimalFormat df;
    public ConversionDevise [] tabConversionDevise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void convertion(ActionEvent actionEvent) {

    }
    public void comboSelection(ActionEvent actionEvent) {

    }
    public void fabriqueDonnees() {

    }
    public void initConvertion(ConversionDevise conversionDevise) {

    }
    public void alerteFormat() {

    }
}