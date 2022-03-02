package com.example.aplicatie_bd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;


import java.io.IOException;
import java.sql.*;

public class CliniciController{
    @FXML
    private TableView<clinici_tabel> clinici;
    @FXML
    private TableColumn<clinici_tabel, Integer> IDClinica;
    @FXML
    private TableColumn<clinici_tabel, String> Nume;
    @FXML
    private TableColumn<clinici_tabel, String> Tara;
    @FXML
    private TableColumn<clinici_tabel, String> Localitate;
    @FXML
    private TableColumn<clinici_tabel, String> Strada;
    @FXML
    private TableColumn<clinici_tabel, Integer> Numar;
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
    private TextField nume;
    @FXML
    private TextField tara;
    @FXML
    private TextField loc;
    @FXML
    private TextField st;
    @FXML
    private TextField nr;

    ObservableList<clinici_tabel> list;

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
    public static ObservableList<clinici_tabel> getData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<clinici_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select * from Clinici");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new clinici_tabel(Integer.parseInt(rs.getString("IDClinica")), rs.getString("Nume"),rs.getString("Tara"),rs.getString("Localitate"), rs.getString("Strada"),Integer.parseInt(rs.getString("Numar"))));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void loadButtonOnAction(ActionEvent event){

        IDClinica.setCellValueFactory(new PropertyValueFactory<clinici_tabel,Integer>("IDClinica"));
        Nume.setCellValueFactory(new PropertyValueFactory<clinici_tabel,String>("Nume"));
        Tara.setCellValueFactory(new PropertyValueFactory<clinici_tabel,String>("Tara"));
        Localitate.setCellValueFactory(new PropertyValueFactory<clinici_tabel,String>("Localitate"));
        Strada.setCellValueFactory(new PropertyValueFactory<clinici_tabel,String>("Strada"));
        Numar.setCellValueFactory(new PropertyValueFactory<clinici_tabel,Integer>("Numar"));

        list=getData();
        clinici.setItems(list);
    }
    public void insertButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String t=tara.getText();
        String l=loc.getText();
        String s=st.getText();
        String num=nr.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("insert into Clinici values('"+n+"','"+t+"','"+l+"','"+s+"','"+num+"')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String t=tara.getText();
        String l=loc.getText();
        String s=st.getText();
        String num=nr.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("delete from Clinici where Nume='"+n+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String t=tara.getText();
        String l=loc.getText();
        String s=st.getText();
        String num=nr.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("update Clinici set Tara='"+t+"', Localitate='"+l+"', Strada='"+s+"', Numar='"+num+"' where Nume='"+n+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<clinici_tabel> SearchData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<clinici_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select C.Nume \n" +
                    "from Clinici C inner join Studii S on C.IDClinica=S.IDStudiu\n" +
                    "group by C.Nume \n" +
                    "having COUNT(C.IDClinica)>=(select TOP 1 COUNT(B.IDClinica) from Studii B group by B.IDClinica order by COUNT(B.IDClinica) DESC)");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new clinici_tabel(rs.getString("Nume")));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void searchButtonOnAction(ActionEvent event){

        Nume.setCellValueFactory(new PropertyValueFactory<clinici_tabel,String>("Nume"));

        list=SearchData();
        clinici.setItems(list);
    }

}
