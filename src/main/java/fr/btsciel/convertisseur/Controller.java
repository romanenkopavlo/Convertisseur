package fr.btsciel.convertisseur;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public AnchorPane anchorPane;
    public Label label_Init;
    public Label label_Final;
    public TextField textField_Init;
    public TextField textField_Final;
    public Button buttonConvertion;
    public ComboBox<String> comboSelection = new ComboBox<>();
    public double taux_Euro_Dollar = 1.11;
    public double taux_Euro_Livre = 0.85;
    public double taux_Euro_Yen = 160.99;
    public double valeur_Conversion;
    public RotateTransition rotation;
    public DecimalFormat df = new DecimalFormat("0.00");
    public ArrayList<ConversionDevise> conversionDevises = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            rotation = new RotateTransition(Duration.seconds(0.5), buttonConvertion);
            ActionEvent actionEvent = new ActionEvent();
            fabriqueDonnees();
            initConvertion(conversionDevises.get(0));
            convertion(actionEvent);
            comboSelection(actionEvent);
    }
    public void convertion(ActionEvent actionEvent) {
        buttonConvertion.setOnAction(event -> {
            try {
                if (Objects.equals(comboSelection.getValue(), conversionDevises.get(0).getPrompt())) {
                    valeur_Conversion = Double.parseDouble(textField_Init.getText()) * conversionDevises.get(0).getTaux();
                    textField_Final.setText(df.format(valeur_Conversion).replaceAll(",", "."));

                } else if (Objects.equals(comboSelection.getValue(), conversionDevises.get(1).getPrompt())) {
                    valeur_Conversion = Double.parseDouble(textField_Final.getText()) / conversionDevises.get(1).getTaux();
                    textField_Init.setText(df.format(valeur_Conversion).replaceAll(",", "."));

                } else if (Objects.equals(comboSelection.getValue(), conversionDevises.get(2).getPrompt())) {
                    valeur_Conversion = Double.parseDouble(textField_Init.getText()) * conversionDevises.get(2).getTaux();
                    textField_Final.setText(df.format(valeur_Conversion).replaceAll(",", "."));

                } else if (Objects.equals(comboSelection.getValue(), conversionDevises.get(3).getPrompt())) {
                    valeur_Conversion = Double.parseDouble(textField_Final.getText()) / conversionDevises.get(3).getTaux();
                    textField_Init.setText(df.format(valeur_Conversion).replaceAll(",", "."));

                } else if (Objects.equals(comboSelection.getValue(), conversionDevises.get(4).getPrompt())) {
                    valeur_Conversion = Double.parseDouble(textField_Init.getText()) * conversionDevises.get(4).getTaux();
                    textField_Final.setText(df.format(valeur_Conversion).replaceAll(",", "."));

                } else if (Objects.equals(comboSelection.getValue(), conversionDevises.get(5).getPrompt())) {
                    valeur_Conversion = Double.parseDouble(textField_Final.getText()) / conversionDevises.get(5).getTaux();
                    textField_Init.setText(df.format(valeur_Conversion).replaceAll(",", "."));
                }
            } catch (NumberFormatException e) {
                alerteFormat();
            }
        });
    }
    public void comboSelection(ActionEvent actionEvent) {
        for (ConversionDevise conversionDevise: conversionDevises) {
            comboSelection.getItems().add(conversionDevise.getPrompt());
        }

        comboSelection.setValue(conversionDevises.get(0).getPrompt());

        final boolean[] isEuroFirstChosen = {true};
        final boolean[] isEuroSecondChosen = {false};

        comboSelection.setOnAction(event -> {

            if (Objects.equals(comboSelection.getValue(), "Euro-Dollar US")) {
                label_Final.setText("Dollar US");
                textField_Init.setDisable(false);
                textField_Final.setDisable(true);
                if (!isEuroFirstChosen[0]) {
                    rotation.setByAngle(180);
                    rotation.play();
                }
                isEuroFirstChosen[0] = true;
                isEuroSecondChosen[0] = false;

            } else if (Objects.equals(comboSelection.getValue(), "Dollar US-Euro")) {
                label_Final.setText("Dollar US");
                textField_Init.setDisable(true);
                textField_Final.setDisable(false);
                if (!isEuroSecondChosen[0]) {
                    rotation.setByAngle(180);
                    rotation.play();
                }
                isEuroSecondChosen[0] = true;
                isEuroFirstChosen[0] = false;

            } else if (Objects.equals(comboSelection.getValue(), "Euro-Livre")) {
                label_Final.setText("Livre");
                textField_Init.setDisable(false);
                textField_Final.setDisable(true);
                if (!isEuroFirstChosen[0]) {
                    rotation.setByAngle(180);
                    rotation.play();
                }
                isEuroFirstChosen[0] = true;
                isEuroSecondChosen[0] = false;

            } else if (Objects.equals(comboSelection.getValue(), "Livre-Euro")) {
                label_Final.setText("Livre");
                textField_Init.setDisable(true);
                textField_Final.setDisable(false);
                if (!isEuroSecondChosen[0]) {
                    rotation.setByAngle(180);
                    rotation.play();
                }
                isEuroSecondChosen[0] = true;
                isEuroFirstChosen[0] = false;

            } else if (Objects.equals(comboSelection.getValue(), "Euro-Yen")) {
                label_Final.setText("Yen");
                textField_Init.setDisable(false);
                textField_Final.setDisable(true);
                if (!isEuroFirstChosen[0]) {
                    rotation.setByAngle(180);
                    rotation.play();
                }
                isEuroFirstChosen[0] = true;
                isEuroSecondChosen[0] = false;

            } else if (Objects.equals(comboSelection.getValue(), "Yen-Euro")) {
                label_Final.setText("Yen");
                textField_Init.setDisable(true);
                textField_Final.setDisable(false);
                if (!isEuroSecondChosen[0]) {
                    rotation.setByAngle(180);
                    rotation.play();
                }
                isEuroSecondChosen[0] = true;
                isEuroFirstChosen[0] = false;
            }

            textField_Init.clear();
            textField_Final.clear();
        });
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
        ConversionDevise conversionDevise1 = new ConversionDevise("Euro-Dollar US", "Euro", "Dollar US", 1.11);
        ConversionDevise conversionDevise2 = new ConversionDevise("Dollar US-Euro", "Dollar US", "Euro", 1.11);
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