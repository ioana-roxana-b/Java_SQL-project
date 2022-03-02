package com.example.aplicatie_bd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;


import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

public class App implements Initializable {

        @FXML
        private Button exitButton;
        @FXML
        private Button backButton;
        @FXML
        private Button Afectiuni;
        @FXML
        private ImageView Picture;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle){
                File brandingFile=new File("IMG/online_doctors_notes_hero_image@2x.jpg");
                Image brandingImage=new Image(brandingFile.toURI().toString());
                Picture.setImage(brandingImage);

                File LockFile=new File("IMG/online_doctors_notes_hero_image@2x.jpg");
                Image LockImage=new Image(LockFile.toURI().toString());
                Picture.setImage(LockImage);
        }

        public void exitButtonOnAction(ActionEvent event) {
                Stage stage1 = (Stage) exitButton.getScene().getWindow();
                stage1.close();
        }
        public void backButtonOnAction(ActionEvent event) throws IOException {
                Stage stage1 = (Stage) backButton.getScene().getWindow();
                stage1.close();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BD_primaPagina.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Evidenta bolnavilor ce participa la testarea unor medicamente/vaccinuri");
                stage.setScene(scene);
                stage.show();
        }
        public void AfecttiuniButtonOnAction(ActionEvent event)
        {

        }

}
