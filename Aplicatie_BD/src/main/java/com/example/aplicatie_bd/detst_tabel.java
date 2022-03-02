package com.example.aplicatie_bd;

public class detst_tabel {
    int IDStudiu, IDMedic, IDPacient;
    String Rezultat;

    public detst_tabel(int IDStudiu, int IDMedic, int IDPacient, String rezultat) {
        this.IDStudiu = IDStudiu;
        this.IDMedic = IDMedic;
        this.IDPacient = IDPacient;
        Rezultat = rezultat;
    }

    public int getIDStudiu() {
        return IDStudiu;
    }

    public int getIDMedic() {
        return IDMedic;
    }

    public int getIDPacient() {
        return IDPacient;
    }

    public String getRezultat() {
        return Rezultat;
    }

    public void setIDStudiu(int IDStudiu) {
        this.IDStudiu = IDStudiu;
    }

    public void setIDMedic(int IDMedic) {
        this.IDMedic = IDMedic;
    }

    public void setIDPacient(int IDPacient) {
        this.IDPacient = IDPacient;
    }

    public void setRezultat(String rezultat) {
        Rezultat = rezultat;
    }
}
