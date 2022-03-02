package com.example.aplicatie_bd;

import java.util.Date;

public class studii_tabel {
    int IDStudiu, IDAfectiune, IDClinica, DepartamentCoordonator;
    String Nume, Tratament, Status;
    int NrPacientiNecesariInscrisi;
    Date DataInceperiiStudiului, DataFinalizariiStudiului;

    public studii_tabel(int IDStudiu, int IDAfectiune, int IDClinica, int departamentCoordonator, String nume, String tratament, String status, int nrPacientiNecesariInscrisi, Date dataInceperiiStudiului, Date dataFinalizariiStudiului) {
        this.IDStudiu = IDStudiu;
        this.IDAfectiune = IDAfectiune;
        this.IDClinica = IDClinica;
        DepartamentCoordonator = departamentCoordonator;
        Nume = nume;
        Tratament = tratament;
        Status = status;
        NrPacientiNecesariInscrisi = nrPacientiNecesariInscrisi;
        DataInceperiiStudiului = dataInceperiiStudiului;
        DataFinalizariiStudiului = dataFinalizariiStudiului;
    }

    public int getIDStudiu() {
        return IDStudiu;
    }

    public int getIDAfectiune() {
        return IDAfectiune;
    }

    public int getIDClinica() {
        return IDClinica;
    }

    public int getDepartamentCoordonator() {
        return DepartamentCoordonator;
    }

    public String getNume() {
        return Nume;
    }

    public String getTratament() {
        return Tratament;
    }

    public String getStatus() {
        return Status;
    }

    public int getNrPacientiNecesariInscrisi() {
        return NrPacientiNecesariInscrisi;
    }

    public Date getDataInceperiiStudiului() {
        return DataInceperiiStudiului;
    }

    public Date getDataFinalizariiStudiului() {
        return DataFinalizariiStudiului;
    }

    public void setIDStudiu(int IDStudiu) {
        this.IDStudiu = IDStudiu;
    }

    public void setIDAfectiune(int IDAfectiune) {
        this.IDAfectiune = IDAfectiune;
    }

    public void setIDClinica(int IDClinica) {
        this.IDClinica = IDClinica;
    }

    public void setDepartamentCoordonator(int departamentCoordonator) {
        DepartamentCoordonator = departamentCoordonator;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public void setTratament(String tratament) {
        Tratament = tratament;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setNrPacientiNecesariInscrisi(int nrPacientiNecesariInscrisi) {
        NrPacientiNecesariInscrisi = nrPacientiNecesariInscrisi;
    }

    public void setDataInceperiiStudiului(Date dataInceperiiStudiului) {
        DataInceperiiStudiului = dataInceperiiStudiului;
    }

    public void setDataFinalizariiStudiului(Date dataFinalizariiStudiului) {
        DataFinalizariiStudiului = dataFinalizariiStudiului;
    }
}
