package com.example.demo;

public class Critere {
    private  String motCle;
    private String date;
    private String categorie;
    private String  prixMax;

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(String prix) {
        this.prixMax = prix;
    }

    public Critere(String motCle, String date, String categorie, String prixMax) {
        this.motCle = motCle;
        this.date = date;
        this.categorie = categorie;
        this.prixMax = prixMax;
    }
    
    
}
