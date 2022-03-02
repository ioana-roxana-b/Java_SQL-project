package com.example.aplicatie_bd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Button LoginButton;
    @FXML
    private Label LoginMessageLabel;
    @FXML
    private ImageView LeftPicture;
    @FXML
    private ImageView LockPicture;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile=new File("IMG/am-jd-OQYv1-YZkZQ-unsplash.jpg");
        Image brandingImage=new Image(brandingFile.toURI().toString());
        LeftPicture.setImage(brandingImage);

        File LockFile=new File("IMG/unlocked-padlock.png");
        Image LockImage=new Image(LockFile.toURI().toString());
        LockPicture.setImage(LockImage);
    }
    public void LoginButtonOnAction(ActionEvent event){
        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
            validateLogin();
        }
        else{
            LoginMessageLabel.setText("Please enter username and password!");
        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage1=(Stage) cancelButton.getScene().getWindow();
        stage1.close();
    }
    public void validateLogin(){
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB= connectNow.getConnection();
        String verifyLogin="select count(1) from Username where username='"+usernameTextField.getText()+"' AND pass='"+enterPasswordField.getText()+"'";
        try{
            Statement statement=connectDB.createStatement();
            ResultSet queryResult=statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    LoginMessageLabel.setText("You did it!");
                    Stage stage1=(Stage) LoginButton.getScene().getWindow();
                    stage1.close();
                    FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("Pagina2.fxml"));
                    Scene scene2 = new Scene(fxmlLoader2.load(), 600, 400);
                    Stage stage2=new Stage();
                    stage2.setTitle("Evidenta bolnavilor ce participa la testarea unor medicamente/vaccinuri");
                    stage2.setScene(scene2);
                    stage2.show();

                }else{
                    LoginMessageLabel.setText("Please, try again!");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}