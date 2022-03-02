package com.example.aplicatie_bd;

public class medici_tabel {
    int IDMedic, IDDepartament;
    String Nume, Prenume;

    public medici_tabel(int IDMedic, int IDDepartament, String nume, String prenume) {
        this.IDMedic = IDMedic;
        this.IDDepartament = IDDepartament;
        Nume = nume;
        Prenume = prenume;
    }

    public medici_tabel(String nume, String prenume) {
        Nume = nume;
        Prenume = prenume;
    }

    public int getIDMedic() {
        return IDMedic;
    }

    public int getIDDepartament() {
        return IDDepartament;
    }

    public String getNume() {
        return Nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setIDMedic(int IDMedic) {
        this.IDMedic = IDMedic;
    }

    public void setIDDepartament(int IDDepartament) {
        this.IDDepartament = IDDepartament;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }
}
