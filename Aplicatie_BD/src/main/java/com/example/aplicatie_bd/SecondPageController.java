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

public class SecondPageController implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Button backButton;
    @FXML
    private Button Afectiuni;
    @FXML
    private Button Pacienti;
    @FXML
    private Button Medici;
    @FXML
    private Button Clinici;
    @FXML
    private Button Departamente;
    @FXML
    private Button Studii;
    @FXML
    private Button DS;
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
    public void AfectiuniButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Afectiuni.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Tabel Afectiuni");
        stage.setScene(scene);
        stage.show();
    }
    public void PacientiButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Pacienti.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Tabel Pacienti");
        stage.setScene(scene);
        stage.show();
    }
    public void MediciButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Medici.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Tabel Medici");
        stage.setScene(scene);
        stage.show();
    }
    public void CliniciButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Clinici.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Tabel Clinici");
        stage.setScene(scene);
        stage.show();
    }
    public void DepartamenteButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Departamente.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Tabel Departamente");
        stage.setScene(scene);
        stage.show();
    }
    public void StudiiButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Studii.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Tabel Studii");
        stage.setScene(scene);
        stage.show();
    }
    public void DSButtonOnAction(ActionEvent event)
    {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Detalii_studii.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Tabel Detalii Studii");
        stage.setScene(scene);
        stage.show();
    }

}
