package com.example.demo;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Utilisateur {
    private int id;
    private String identifiant;
    private String mdp;


    public Utilisateur(int id, String identifiant, String mdp) {
        this.id = id;
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

    public Utilisateur() {
    }

    public Utilisateur(int id, String identifiant) {
        this.id = id;
        this.identifiant = identifiant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static Vector<Utilisateur> getAllUtilisateur() throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from utilisateur";
        ResultSet res = sta.executeQuery(req);
        Vector<Utilisateur> liste = null;
        try {

            liste = new Vector<Utilisateur>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Utilisateur(res.getInt(1),res.getString(2),res.getString(3)));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();
            if(con !=null)
                con.close();
        }
        return liste;
    }
    public static void insertRecharger(int idUtilisateur , double montant) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into demanderecharge (idutilisateur,montant) values ("+ idUtilisateur +" , "+montant+")";
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
    public static void insertUtilisateur(Utilisateur utilisateur) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into utilisateur(identifiant, mdp) values ('" + utilisateur.getIdentifiant() + "','"
            + utilisateur.getMdp() +"')";
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }

}
