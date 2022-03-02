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

public class PacientiController{
    @FXML
    private TableView<pacienti_tabel> pacienti;
    @FXML
    private TableColumn<pacienti_tabel, Integer> IDPacient;
    @FXML
    private TableColumn<pacienti_tabel, String> Nume;
    @FXML
    private TableColumn<pacienti_tabel, String> Prenume;
    @FXML
    private TableColumn<pacienti_tabel, String> CNP;
    @FXML
    private TableColumn<pacienti_tabel, Date> DataNasterii;
    @FXML
    private TableColumn<pacienti_tabel, String> Sex;
    @FXML
    private TableColumn<pacienti_tabel, String> Tara;
    @FXML
    private TableColumn<pacienti_tabel, String> Oras;
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
    private TextField prenume;
    @FXML
    private TextField cnp;
    @FXML
    private TextField dn;
    @FXML
    private TextField sex;
    @FXML
    private TextField tara;
    @FXML
    private TextField oras;
    @FXML
    private TextField t_an;

    ObservableList<pacienti_tabel> list;

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
    public static ObservableList<pacienti_tabel> getData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<pacienti_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select * from Pacienti");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new pacienti_tabel(Integer.parseInt(rs.getString("IDPacient")), rs.getString("Nume"),rs.getString("Prenume"),rs.getString("CNP"), rs.getDate("DataNasterii"), rs.getString("Sex"), rs.getString("Tara"), rs.getString("Oras")));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void loadButtonOnAction(ActionEvent event){

        IDPacient.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,Integer>("IDPacient"));
        Nume.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,String>("Nume"));
        Prenume.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,String>("Prenume"));
        CNP.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,String >("CNP"));
        DataNasterii.setCellValueFactory(new PropertyValueFactory<pacienti_tabel, Date>("DataNasterii"));
        Sex.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,String>("Sex"));
        Tara.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,String>("Tara"));
        Oras.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,String>("Oras"));

        list=getData();
        pacienti.setItems(list);
    }
    public void insertButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String p=prenume.getText();
        String c=cnp.getText();
        String d=dn.getText();
        String s=sex.getText();
        String t=tara.getText();
        String o=oras.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("insert into Pacienti values('"+n+"','"+p+"','"+c+"','"+d+"','"+s+"','"+t+"','"+o+"')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String p=prenume.getText();
        String c=cnp.getText();
        String d=dn.getText();
        String s=sex.getText();
        String t=tara.getText();
        String o=oras.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("delete from Pacienti where Nume='"+n+"' OR Prenume='"+p+"' OR CNP='"+c+"' OR DataNasterii='"+d+"' OR Sex='"+s+"' OR Tara='"+t+"' Or Oras='"+o+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String p=prenume.getText();
        String c=cnp.getText();
        String d=dn.getText();
        String s=sex.getText();
        String t=tara.getText();
        String o=oras.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("update Pacienti set Tara='"+t+"', Oras='"+o+"' where Nume='"+n+"' AND Prenume='"+p+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchButtonOnAction(ActionEvent event){
        String an=t_an.getText();
        Connection con=DatabaseConnection.getConnection();
        ObservableList<pacienti_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select P.Nume, P.Prenume\n" +
                    "from Pacienti P inner join DetaliiStudii DS on DS.IDPacient=P.IDPacient\n" +
                    "where DS.IDStudiu in (select top 1 S.IDStudiu from Studii S where YEAR(S.DataInceperiiStudiului)='"+an+"')\n" +
                    "group by P.Nume, P.Prenume");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new pacienti_tabel(rs.getString("Nume"),rs.getString("Prenume")));
            }

        }catch (Exception e){

        }
        Nume.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,String>("Nume"));
        Prenume.setCellValueFactory(new PropertyValueFactory<pacienti_tabel,String>("Prenume"));
        pacienti.setItems(list);
    }
}
