package com.example.aplicatie_bd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.net.URL;

public class DetaliiStudiiController{
    @FXML
    private TableView<detst_tabel> detalii_studii;
    @FXML
    private TableColumn<detst_tabel, Integer> IDStudiu;
    @FXML
    private TableColumn<detst_tabel, Integer> IDMedic;
    @FXML
    private TableColumn<detst_tabel, Integer> IDPacient;
    @FXML
    private TableColumn<detst_tabel, String> Rezultat;
    @FXML
    private Button exitButton;
    @FXML
    private Button backButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField rezultat;
    @FXML
    private TextField ids;

    ObservableList<detst_tabel> list;

    public void exitButtonOnAction(ActionEvent event) {
        Stage stage1 = (Stage) exitButton.getScene().getWindow();
        stage1.close();
    }
    public void backButtonOnAction(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) backButton.getScene().getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Pagina2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Evidenta bolnavilor ce participa la testarea unor medicamente/vaccinuri");
        stage.setScene(scene);
        stage.show();
    }
    public static ObservableList<detst_tabel> getData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<detst_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select * from DetaliiStudii");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new detst_tabel(Integer.parseInt(rs.getString("IDStudiu")),Integer.parseInt(rs.getString("IDMedic")),Integer.parseInt(rs.getString("IDPacient")), rs.getString("Rezultat")));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void loadButtonOnAction(ActionEvent event){

        IDStudiu.setCellValueFactory(new PropertyValueFactory<detst_tabel,Integer>("IDStudiu"));
        IDMedic.setCellValueFactory(new PropertyValueFactory<detst_tabel,Integer>("IDMedic"));
        IDPacient.setCellValueFactory(new PropertyValueFactory<detst_tabel,Integer>("IDPacient"));
        Rezultat.setCellValueFactory(new PropertyValueFactory<detst_tabel,String>("Rezultat"));

        list=getData();
        detalii_studii.setItems(list);
    }
    public void deleteButtonOnAction(ActionEvent event){
        String r=rezultat.getText();
        String i=ids.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement s=con.createStatement();
            s.executeUpdate("delete from DetaliiStudii where IDStudiu='"+i+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateButtonOnAction(ActionEvent event){
        String r=rezultat.getText();
        String i=ids.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement s=con.createStatement();
            s.executeUpdate("update DetaliiStudii set Rezultat='"+r+"' where IDStudiu='"+i+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
