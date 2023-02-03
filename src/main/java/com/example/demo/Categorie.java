package com.example.demo;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Categorie {
    private int id;
    private String nomcategorie;


    public Categorie() {
    }

    public Categorie(int id, String nomcategorie) {
        this.id = id;
        this.nomcategorie = nomcategorie;
    }

    public static Vector<Categorie> findAll() throws Exception{
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from categorie order by id";
        ResultSet res = sta.executeQuery(req);
        Vector<Categorie> liste = null;
        try {
            liste = new Vector<Categorie>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Categorie(res.getInt(1),res.getString(2)));
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
}
