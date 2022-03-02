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

public class DepartamenteController{
    @FXML
    private TableView<departamente_tabel> departamente;
    @FXML
    private TableColumn<departamente_tabel, Integer> IDDepartament;
    @FXML
    private TableColumn<departamente_tabel, Integer> SefDepartament;
    @FXML
    private TableColumn<departamente_tabel, String> NumeDepartament;
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
    private Button search2;
    @FXML
    private Button butonMagic;
    @FXML
    private TextField nume;
    @FXML
    private TextField sd;
    @FXML
    private TextArea nrmed;
    @FXML
    private TextArea sef;

    ObservableList<departamente_tabel> list;

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
    public static ObservableList<departamente_tabel> getData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<departamente_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select * from Departamente");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new departamente_tabel(Integer.parseInt(rs.getString("IDDepartament")),Integer.parseInt(rs.getString("SefDepartament")), rs.getString("NumeDepartament")));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void loadButtonOnAction(ActionEvent event){
        IDDepartament.setCellValueFactory(new PropertyValueFactory<departamente_tabel,Integer>("IDDepartament"));
        SefDepartament.setCellValueFactory(new PropertyValueFactory<departamente_tabel,Integer>("SefDepartament"));
        NumeDepartament.setCellValueFactory(new PropertyValueFactory<departamente_tabel,String>("NumeDepartament"));

        list=getData();
        departamente.setItems(list);
    }
    public void insertButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String ids=sd.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement s=con.createStatement();
            s.executeUpdate("insert into Departamente values('"+ids+"','"+n+"')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String ids=sd.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement s=con.createStatement();
            s.executeUpdate("delete from Departamente where NumeDepartament='"+n+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String ids=sd.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement s=con.createStatement();
            s.executeUpdate("update Departamente set NumeDepartament='"+n+"' where SefDepartament='"+ids+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchButtonOnAction(ActionEvent event){
        String n=nume.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            PreparedStatement ps=con.prepareStatement("select count(M.IdMedic) as NrMedici\n" +
                    "from Medici M inner join Departamente D on D.IDDepartament=M.IDDepartament\n" +
                    "where D.NumeDepartament='"+n+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                nrmed.setText(rs.getString("NrMedici"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<departamente_tabel> SearchData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<departamente_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select D.IDDepartament, D.NumeDepartament, D.SefDepartament\n" +
                    "from Departamente D\n" +
                    "where D.IDDepartament NOT IN(select S.DepartamentCoordonator from Studii S where S.DepartamentCoordonator=D.IDDepartament)");
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                list.add(new departamente_tabel(Integer.parseInt(rs.getString("IDDepartament")),Integer.parseInt(rs.getString("SefDepartament")), rs.getString("NumeDepartament")));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void butonMagicButtonOnAction(ActionEvent event){
        IDDepartament.setCellValueFactory(new PropertyValueFactory<departamente_tabel,Integer>("IDDepartament"));
        SefDepartament.setCellValueFactory(new PropertyValueFactory<departamente_tabel,Integer>("SefDepartament"));
        NumeDepartament.setCellValueFactory(new PropertyValueFactory<departamente_tabel,String>("NumeDepartament"));

        list=SearchData();
        departamente.setItems(list);

    }
    public void search2ButtonOnAction(ActionEvent event){
        String n=nume.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            PreparedStatement ps=con.prepareStatement("select concat(M.Nume, ' ', M.Prenume) as Name\n" +
                    "from Medici M inner join Departamente D on D.SefDepartament=M.IDMedic\n" +
                    "where D.NumeDepartament='"+n+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                sef.setText(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
