package fr.btsciel.convertisseur;

import javafx.animation.RotateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public AnchorPane anchorPane;
    public Label label_Init;
    public Label label_Final;
    public TextField textField_Init;
    public TextField textField_Final;
    public Button buttonConvertion;
    public final boolean[] isEvenChosen = {true};
    public final boolean[] isOddChosen = {false};
    public ComboBox<String> comboSelection = new ComboBox<>();
    public double valeur_Conversion;
    public RotateTransition rotation;
    public DecimalFormat df = new DecimalFormat("0.00");
    public ArrayList<ConversionDevise> conversionDevises = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            rotation = new RotateTransition(Duration.seconds(0.5), buttonConvertion);
            fabriqueDonnees();

            for (ConversionDevise conversionDevise : conversionDevises) {
                comboSelection.getItems().add(conversionDevise.getPrompt());
            }
            comboSelection.setValue(conversionDevises.get(0).getPrompt());

            initConvertion(conversionDevises.get(0));
            buttonConvertion.setOnAction(event -> convertion());
            comboSelection.setOnAction(event -> comboSelection());
    }
    public void convertion() {
        try {
            for (ConversionDevise devise : conversionDevises) {
                if (comboSelection.getValue().equals(devise.getPrompt())) {
                    if (textField_Init.isDisabled()) {
                        valeur_Conversion = Double.parseDouble(textField_Final.getText()) / devise.getTaux();
                        textField_Init.setText(df.format(valeur_Conversion).replaceAll(",", "."));
                    } else {
                        valeur_Conversion = Double.parseDouble(textField_Init.getText()) * devise.getTaux();
                        textField_Final.setText(df.format(valeur_Conversion).replaceAll(",", "."));
                    }
                }
            }
        } catch (NumberFormatException e) {
            alerteFormat();
        }
    }
    public void comboSelection() {
        int index = comboSelection.getSelectionModel().getSelectedIndex();
        boolean isOddElement = index % 2 == 1;

        if (isOddElement) {
            if (!isOddChosen[0]) {
                rotation.setByAngle(180);
                rotation.play();
                isOddChosen[0] = true;
                isEvenChosen[0] = false;
            }
            label_Final.setText(conversionDevises.get(index - 1).getCible());
        } else {
            if (!isEvenChosen[0]) {
                rotation.setByAngle(180);
                rotation.play();
                isEvenChosen[0] = true;
                isOddChosen[0] = false;
            }
            label_Final.setText(conversionDevises.get(index).getCible());
        }

        textField_Init.setDisable(isOddElement);
        textField_Final.setDisable(!isOddElement);

        textField_Init.clear();
        textField_Final.clear();
    }

    public void alerteFormat() {
        Alert dialogWindow = new Alert(Alert.AlertType.WARNING);
        dialogWindow.setTitle("Error");
        dialogWindow.setHeaderText(null);
        if (!textField_Init.isDisable()) {
            dialogWindow.setContentText("Une mauvaise saisie " + "'" +  textField_Init.getText() + "'" + " n'est pas un nombre");
        } else {
            dialogWindow.setContentText("Une mauvaise saisie " + "'" +  textField_Final.getText() + "'" + " n'est pas un nombre");
        }
        dialogWindow.showAndWait();
    }
    public void fabriqueDonnees() {
        ConversionDevise conversionDevise1 = new ConversionDevise("Euro-Dollars US", "Euro", "Dollars US", 1.11);
        ConversionDevise conversionDevise2 = new ConversionDevise("Dollars US-Euro", "Dollars US", "Euro", 1.11);
        ConversionDevise conversionDevise3 = new ConversionDevise("Euro-Livre", "Euro", "Livre", 0.85);
        ConversionDevise conversionDevise4 = new ConversionDevise("Livre-Euro", "Livre", "Euro", 0.85);
        ConversionDevise conversionDevise5 = new ConversionDevise("Euro-Yen", "Euro", "Yen", 160.99);
        ConversionDevise conversionDevise6 = new ConversionDevise("Yen-Euro", "Yen", "Euro", 160.99);
        conversionDevises.add(conversionDevise1);
        conversionDevises.add(conversionDevise2);
        conversionDevises.add(conversionDevise3);
        conversionDevises.add(conversionDevise4);
        conversionDevises.add(conversionDevise5);
        conversionDevises.add(conversionDevise6);
    }
    public void initConvertion(ConversionDevise conversionDevise) {
        label_Init.setText(conversionDevise.getSource());
        label_Final.setText(conversionDevise.getCible());
    }
}