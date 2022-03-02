package com.example.aplicatie_bd;

import java.util.Date;

public class pacienti_tabel {
    int IDPacient;
    String Nume, Prenume, CNP;
    Date DataNasterii;
    String Sex, Tara, Oras;

    public pacienti_tabel(int IDPacient, String nume, String prenume, String CNP, Date dataNasterii, String sex, String tara, String oras) {
        this.IDPacient = IDPacient;
        Nume = nume;
        Prenume = prenume;
        this.CNP = CNP;
        DataNasterii = dataNasterii;
        Sex = sex;
        Tara = tara;
        Oras = oras;
    }

    public pacienti_tabel(String nume, String prenume) {
        Nume = nume;
        Prenume = prenume;
    }

    public int getIDPacient() {
        return IDPacient;
    }

    public String getNume() {
        return Nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public String getCNP() {
        return CNP;
    }

    public Date getDataNasterii() {
        return DataNasterii;
    }

    public String getSex() {
        return Sex;
    }

    public String getTara() {
        return Tara;
    }

    public String getOras() {
        return Oras;
    }

    public void setIDPacient(int IDPacient) {
        this.IDPacient = IDPacient;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setDataNasterii(Date dataNasterii) {
        DataNasterii = dataNasterii;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public void setTara(String tara) {
        Tara = tara;
    }

    public void setOras(String oras) {
        Oras = oras;
    }
}
