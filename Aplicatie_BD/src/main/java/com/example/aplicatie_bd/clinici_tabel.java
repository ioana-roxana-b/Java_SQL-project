package com.example.aplicatie_bd;

public class clinici_tabel {
    int IDClinica, Numar;
    String Nume, Tara, Localitate, Strada;

    public clinici_tabel(int IDClinica, String nume, String tara, String localitate, String strada, int numar) {
        this.IDClinica = IDClinica;
        Numar = numar;
        Nume = nume;
        Tara = tara;
        Localitate = localitate;
        Strada = strada;
    }

    public clinici_tabel(String nume) {
        Nume = nume;
    }

    public int getIDClinica() {
        return IDClinica;
    }

    public int getNumar() {
        return Numar;
    }

    public String getNume() {
        return Nume;
    }

    public String getTara() {
        return Tara;
    }

    public String getLocalitate() {
        return Localitate;
    }

    public String getStrada() {
        return Strada;
    }

    public void setIDClinica(int IDClinica) {
        this.IDClinica = IDClinica;
    }

    public void setNumar(int numar) {
        Numar = numar;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public void setTara(String tara) {
        Tara = tara;
    }

    public void setLocalitate(String localitate) {
        Localitate = localitate;
    }

    public void setStrada(String strada) {
        Strada = strada;
    }
}
