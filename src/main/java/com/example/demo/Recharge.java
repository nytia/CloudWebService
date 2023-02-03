package com.example.demo;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.Conexion;

public class Recharge {
    private int idUtilisateur;
    private float montant;

    public Recharge() {
    }

    public Recharge(int idUtilisateur, float montant) {
        this.idUtilisateur = idUtilisateur;
        this.montant = montant;
    }

    public int getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Recharge idUtilisateur(int idUtilisateur) {
        setIdUtilisateur(idUtilisateur);
        return this;
    }

    public Recharge montant(float montant) {
        setMontant(montant);
        return this;
    }


    public static void insertRecharge(Recharge recharge) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into demanderecharge(idUtilisateur, montant) values ("
                    + recharge.getIdUtilisateur() + "," + recharge.getMontant() + ")";
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
}