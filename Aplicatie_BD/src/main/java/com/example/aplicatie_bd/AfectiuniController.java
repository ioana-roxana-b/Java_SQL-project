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

public class AfectiuniController{
    @FXML
    private TableView<afectiuni_tabel> afectiuni;
    @FXML
    private TableColumn<afectiuni_tabel, Integer> IDAfectiune;
    @FXML
    private TableColumn<afectiuni_tabel, String> Nume;
    @FXML
    private TableColumn<afectiuni_tabel, Integer> NrStudiiEfectuate;
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
    private Button searchButton;
    @FXML
    private TextField nume_af;
    @FXML
    private TextField nrStudiiaf;
    @FXML
    private TextArea nrs;

    ObservableList<afectiuni_tabel> list;

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
    public static ObservableList<afectiuni_tabel> getData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<afectiuni_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select * from Afectiuni");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new afectiuni_tabel(Integer.parseInt(rs.getString("IDAfectiune")), rs.getString("Nume"), Integer.parseInt(rs.getString("NrStudiiEfectuate"))));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void loadButtonOnAction(ActionEvent event){

        IDAfectiune.setCellValueFactory(new PropertyValueFactory<afectiuni_tabel,Integer>("IDAfectiune"));
        Nume.setCellValueFactory(new PropertyValueFactory<afectiuni_tabel,String>("Nume"));
        NrStudiiEfectuate.setCellValueFactory(new PropertyValueFactory<afectiuni_tabel,Integer>("NrStudiiEfectuate"));

        list=getData();
        afectiuni.setItems(list);
    }
    public void insertButtonOnAction(ActionEvent event){
        String n=nume_af.getText();
        String nsa=nrStudiiaf.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement s=con.createStatement();
            s.executeUpdate("insert into Afectiuni values('"+n+"','"+nsa+"')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteButtonOnAction(ActionEvent event){
        String n=nume_af.getText();
        String nsa=nrStudiiaf.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement s=con.createStatement();
            s.executeUpdate("delete from Afectiuni where Nume='"+n+"' AND NrStudiiEfectuate='"+nsa+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateButtonOnAction(ActionEvent event){
        String n=nume_af.getText();
        String nsa=nrStudiiaf.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement s=con.createStatement();
            s.executeUpdate("update Afectiuni set NrStudiiEfectuate='"+nsa+"' where Nume='"+n+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchButtonOnAction(ActionEvent event){
        String n=nume_af.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            PreparedStatement ps=con.prepareStatement("select COUNT(S.IDStudiu) as NR\n" +
                    "from Studii S inner join Afectiuni A on A.IDAfectiune=S.IDAfectiune\n" +
                    "where A.Nume='"+n+"' AND S.Status='Activ'");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                nrs.setText(rs.getString("NR"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
