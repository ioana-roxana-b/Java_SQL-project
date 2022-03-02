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

public class MediciCotroller {
    @FXML
    private TableView<medici_tabel> medici;
    @FXML
    private TableColumn<medici_tabel, Integer> IDMedic;
    @FXML
    private TableColumn<medici_tabel, Integer> IDDepartament;
    @FXML
    private TableColumn<medici_tabel, String> Nume;
    @FXML
    private TableColumn<medici_tabel, String> Prenume;
    @FXML
    private Button exitButton;
    @FXML
    private Button backButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button searchButton2;
    @FXML
    private Button searchButton3;
    @FXML
    private TextField nume;
    @FXML
    private TextField prenume;
    @FXML
    private TextField iddep;
    @FXML
    public TextField nrs;
    @FXML
    private TextArea ND;
    @FXML
    private TextArea NSP;
    ObservableList<medici_tabel> list;

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
    public static ObservableList<medici_tabel> getData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<medici_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select * from Medici");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new medici_tabel(Integer.parseInt(rs.getString("IDMedic")),Integer.parseInt(rs.getString("IDDepartament")), rs.getString("Nume"),rs.getString("Prenume")));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void loadButtonOnAction(ActionEvent event){

        IDMedic.setCellValueFactory(new PropertyValueFactory<medici_tabel,Integer>("IDMedic"));
        IDDepartament.setCellValueFactory(new PropertyValueFactory<medici_tabel,Integer>("IDDepartament"));
        Nume.setCellValueFactory(new PropertyValueFactory<medici_tabel,String>("Nume"));
        Prenume.setCellValueFactory(new PropertyValueFactory<medici_tabel,String>("Prenume"));

        list=getData();
        medici.setItems(list);
    }
    public void insertButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String p=prenume.getText();
        String idd=iddep.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("insert into Medici values('"+idd+"','"+n+"','"+p+"')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String p=prenume.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("delete from Medici where Nume='"+n+"' OR Prenume='"+n+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<medici_tabel> SearchData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<medici_tabel> list = FXCollections.observableArrayList();
        String ns=nrs.getText();
        try{
            PreparedStatement ps=con.prepareStatement("select M.Nume, M.Prenume\n" +
                    "from Medici M inner join DetaliiStudii DS on DS.IDMedic=M.IDMedic\n" +
                    "group by M.Nume, M.Prenume\n" +
                    "having count(DS.IDMedic)>='"+ns+"'");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new medici_tabel(rs.getString("Nume"),rs.getString("Prenume")));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void searchButtonOnAction(ActionEvent event){
        Nume.setCellValueFactory(new PropertyValueFactory<medici_tabel,String>("Nume"));
        Prenume.setCellValueFactory(new PropertyValueFactory<medici_tabel,String>("Prenume"));

        list=SearchData();
        medici.setItems(list);

    }
    public void searchButton2OnAction(ActionEvent event){
        String n=nume.getText();
        String p=prenume.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            PreparedStatement ps=con.prepareStatement("select D.NumeDepartament\n" +
                    "        from Departamente D inner join Medici M on M.IDDepartament=D.IDDepartament\n" +
                    "        where M.Nume='"+n+"' AND M.Prenume='"+p+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                ND.setText(rs.getString("NumeDepartament"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchButton3OnAction(ActionEvent event){
        String n=nume.getText();
        String p=prenume.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            PreparedStatement ps=con.prepareStatement("select count(DS.IDStudiu) as NrStudii\n" +
                    "from DetaliiStudii DS inner join Medici M on M.IDMedic=DS.IDMedic\n" +
                    "where M.Nume='"+n+"' AND M.Prenume='"+p+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                NSP.setText(rs.getString("NrStudii"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
