package com.example.aplicatie_bd;

public class departamente_tabel {
    int IDDepartament, SefDepartament;
    String NumeDepartament;

    public departamente_tabel(int IDDepartament, int sefDepartament, String numeDepartament) {
        this.IDDepartament = IDDepartament;
        SefDepartament = sefDepartament;
        NumeDepartament = numeDepartament;
    }

    public departamente_tabel(String numeDepartament) {
        NumeDepartament = numeDepartament;
    }

    public int getIDDepartament() {
        return IDDepartament;
    }

    public int getSefDepartament() {
        return SefDepartament;
    }

    public String getNumeDepartament() {
        return NumeDepartament;
    }

    public void setIDDepartament(int IDDepartament) {
        this.IDDepartament = IDDepartament;
    }

    public void setSefDepartament(int sefDepartament) {
        SefDepartament = sefDepartament;
    }

    public void setNumeDepartament(String numeDepartament) {
        NumeDepartament = numeDepartament;
    }
}
