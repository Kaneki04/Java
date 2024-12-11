package com.japanese.kanjizy;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private TextField myTextField;

    @FXML
    private Label Kanji;

    @FXML
    private Label progressLabel;


    @FXML
    private Label ResponsiveLabel;

    @FXML
    private Label Result;
    @FXML
    private Label showLap;

    @FXML
    private ChoiceBox<String> modus;
    @FXML

    private ChoiceBox<String> lang;


    @FXML
    private ProgressBar myProgress;


    private int lap = 0;


    private List<List<String>> data;

    private int dataLen;


    private int languageChoice = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getCsvData();
        setContent();
        dataLen = data.get(0).size();
        modus.getItems().addAll(FXCollections.observableArrayList("Kanji", "Meaning"));
        modus.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setContent();
            }
        });
        modus.setValue("Kanji");

        lang.getItems().addAll(FXCollections.observableArrayList("De", "En", "Es"));

        lang.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lap = 0;
                if (Objects.equals(lang.getValue(), "De")) {
                    languageChoice = 2;
                } else if (Objects.equals(lang.getValue(), "En")) {
                    languageChoice = 3;
                } else {
                    languageChoice = 4;
                }
                setContent();

            }
        });
        lang.setValue("De");
        myProgress.setStyle("-fx-accent: #00FF00;");
        showLap.setText((lap + 1) + " / " + dataLen);
        ResponsiveLabel.setPickOnBounds(true);
        ResponsiveLabel.setOnMouseClicked(e -> ResponsiveLabel.requestFocus());

    }


    public void setBarValue() {
        double barValue = (((double) (lap + 1)) / data.get(0).size());
        progressLabel.setText((lap + 1) + "/" + data.get(0).size());
        myProgress.setProgress(barValue);

    }


    public void listenKeys() {
        if (Objects.equals(modus.getValue(), "Kanji")) {
            myTextField.textProperty().addListener((obs, oldText, newText) -> {
                ResponsiveLabel.setText(newText);
                giveMessage(newText);
            });

        } else {
            myTextField.textProperty().addListener((obs, oldText, newText) -> {
                String text = new TextToHiragana().convert(newText);
                ResponsiveLabel.setText(text);
                giveMessage(text);

            });

        }

    }

    public void giveMessage(String text) {
        if (Objects.equals(text.toLowerCase(), data.get(languageChoice).get(lap)) && Objects.equals(modus.getValue(), "Kanji") || Objects.equals(text, data.get(1).get(lap)) && Objects.equals(modus.getValue(), "Meaning")) {
            Result.setText("Correct!");
        } else {
            Result.setText("Continue...");
        }
    }

    public void getCsvData() {
        data = ReadCsv.fetchAllData("/csv/verben/present/present/all_present.csv");
    }

    public void setContent() {
        if (Objects.equals(modus.getValue(), "Kanji")) {
            Kanji.setText(data.get(0).get(lap));
            myTextField.clear();
        } else {
            String str = data.get(languageChoice).get(lap);
            Kanji.setText(str.substring(0, 1).toUpperCase() + str.substring(1));
            myTextField.clear();
        }
        ResponsiveLabel.setText("");
        setBarValue();
    }

    public void next() {
        if (lap < (data.get(0).size() - 1)) {
            lap++;
            setContent();

        } else if (lap == (data.get(0).size() - 1)) {
            lap = 0;
            setContent();
        }
        showLap.setText((lap + 1) + " / " + dataLen);

    }

    public void back() {
        if (lap > 0) {
            lap--;
            setContent();
        } else if (lap == 0) {
            lap = (data.get(0).size() - 1);
            setContent();
        }
        showLap.setText((lap + 1) + " / " + dataLen);

    }

    public void showAnswer() {
        if (Objects.equals(modus.getValue(), "Kanji")) {
            String str = data.get(languageChoice).get(lap);

            ResponsiveLabel.setText(str.substring(0, 1).toUpperCase() + str.substring(1)

            );
        } else {
            String str = data.get(1).get(lap);
            ResponsiveLabel.setText(str.substring(0, 1).toUpperCase() + str.substring(1));
        }
        myTextField.clear();
    }
}
