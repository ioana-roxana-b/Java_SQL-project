package com.example.aplicatie_bd;

public class afectiuni_tabel {
    int IDAfectiune;
    String Nume;
    int NrStudiiEfectuate;

    public afectiuni_tabel(int IDAfectiune, String nume, int nrStudiiEfectuate) {
        this.IDAfectiune = IDAfectiune;
        Nume = nume;
        NrStudiiEfectuate = nrStudiiEfectuate;
    }

    public int getIDAfectiune() {
        return IDAfectiune;
    }

    public String getNume() {
        return Nume;
    }

    public int getNrStudiiEfectuate() {
        return NrStudiiEfectuate;
    }

    public void setIDAfectiune(int IDAfectiune) {
        this.IDAfectiune = IDAfectiune;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public void setNrStudiiEfectuate(int nrStudiiEfectuate) {
        NrStudiiEfectuate = nrStudiiEfectuate;
    }
}
