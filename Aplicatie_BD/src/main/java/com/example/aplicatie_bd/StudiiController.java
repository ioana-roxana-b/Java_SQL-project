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

public class StudiiController{
    @FXML
    private TableView<studii_tabel> studii;
    @FXML
    private TableColumn<studii_tabel, Integer> IDStudiu;
    @FXML
    private TableColumn<studii_tabel, Integer> IDAfectiune;
    @FXML
    private TableColumn<studii_tabel, Integer> IDClinica;
    @FXML
    private TableColumn<studii_tabel, Integer> DepartamentCoordonator;
    @FXML
    private TableColumn<studii_tabel, String> Nume;
    @FXML
    private TableColumn<studii_tabel, String> Tratament;
    @FXML
    private TableColumn<studii_tabel, String> Status;
    @FXML
    private TableColumn<studii_tabel, Integer> NrPacientiNecesariInscrisi;
    @FXML
    private TableColumn<studii_tabel, Date> DataInceperiiStudiului;
    @FXML
    private TableColumn<studii_tabel, Date> DataFinalizariiStudiului;
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
    private TextField tratament;
    @FXML
    private TextField status;
    @FXML
    private TextField nrpacienti;
    @FXML
    private TextField dis;
    @FXML
    private TextField dfs;
    @FXML
    private TextField idafectiune;
    @FXML
    private TextField idclinica;
    @FXML
    private TextField depc;
    @FXML
    private TextArea t_an;


    ObservableList<studii_tabel> list;

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
    public static ObservableList<studii_tabel> getData(){
        Connection con=DatabaseConnection.getConnection();
        ObservableList<studii_tabel> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps=con.prepareStatement("select * from Studii");
            ResultSet rs=ps.executeQuery();

            while(rs.next()) {
                list.add(new studii_tabel(Integer.parseInt(rs.getString("IDStudiu")),Integer.parseInt(rs.getString("IDAfectiune")),Integer.parseInt(rs.getString("IDClinica")),Integer.parseInt(rs.getString("DepartamentCoordonator")), rs.getString("Nume"),rs.getString("Tratament"),rs.getString("Status"),Integer.parseInt(rs.getString("NrPacientiNecesariInscrisi")), rs.getDate("DataInceperiiStudiului"),rs.getDate("DataFinalizariiStudiului")));
            }

        }catch (Exception e){

        }
        return list;
    }
    public void loadButtonOnAction(ActionEvent event){

        IDStudiu.setCellValueFactory(new PropertyValueFactory<studii_tabel,Integer>("IDStudiu"));
        IDAfectiune.setCellValueFactory(new PropertyValueFactory<studii_tabel,Integer>("IDAfectiune"));
        IDClinica.setCellValueFactory(new PropertyValueFactory<studii_tabel,Integer>("IDClinica"));
        DepartamentCoordonator.setCellValueFactory(new PropertyValueFactory<studii_tabel,Integer>("DepartamentCoordonator"));
        Nume.setCellValueFactory(new PropertyValueFactory<studii_tabel,String>("Nume"));
        Tratament.setCellValueFactory(new PropertyValueFactory<studii_tabel,String>("Tratament"));
        Status.setCellValueFactory(new PropertyValueFactory<studii_tabel,String>("Status"));
        NrPacientiNecesariInscrisi.setCellValueFactory(new PropertyValueFactory<studii_tabel,Integer>("NrPacientiNecesariInscrisi"));
        DataInceperiiStudiului.setCellValueFactory(new PropertyValueFactory<studii_tabel, Date>("DataInceperiiStudiului"));
        DataFinalizariiStudiului.setCellValueFactory(new PropertyValueFactory<studii_tabel, Date>("DataFinalizariiStudiului"));

        list=getData();
        studii.setItems(list);
    }
    public void insertButtonOnAction(ActionEvent event){
        String ida=idafectiune.getText();
        String idc=idclinica.getText();
        String dc=depc.getText();
        String n=nume.getText();
        String t=tratament.getText();
        String s=status.getText();
        String nr=nrpacienti.getText();
        String di=dis.getText();
        String df=dfs.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("insert into Studii values('"+ida+"','"+idc+"','"+dc+"','"+n+"','"+t+"','"+s+"','"+nr+"','"+di+"','"+df+"')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String t=tratament.getText();
        String s=status.getText();
        String nr=nrpacienti.getText();
        String di=dis.getText();
        String df=dfs.getText();

        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("delete from Studii where Nume='"+n+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateButtonOnAction(ActionEvent event){
        String n=nume.getText();
        String t=tratament.getText();
        String s=status.getText();
        String nr=nrpacienti.getText();
        String di=dis.getText();
        String df=dfs.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            Statement st=con.createStatement();
            st.executeUpdate("update Studii set Status='"+s+"' where Nume='"+n+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchButtonOnAction(ActionEvent event){

        String n=nume.getText();
        Connection con=DatabaseConnection.getConnection();
        try {
            PreparedStatement ps=con.prepareStatement("select YEAR(S.DataInceperiiStudiului) as AN\n" +
                    "from Studii S\n" +
                    "group by YEAR(S.DataInceperiiStudiului)\n" +
                    "having COUNT(S.IDStudiu)>=(select TOP 1 COUNT(X.IDStudiu) from Studii X group by YEAR(X.DataInceperiiStudiului) order by COUNT(X.DataInceperiiStudiului) DESC)");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                t_an.setText(rs.getString("AN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
