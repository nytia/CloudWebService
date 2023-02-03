package com.example.demo;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Historique{
    private Enchere enchere; 


    public Enchere getEnchere() {
        return this.enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public Historique(){

    }
    public static Vector<Enchere> selectHistoriqueByIdUser(String iduser) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_enchere where statut=1 and idutilisateur="+iduser+" order by date";
        ResultSet res = sta.executeQuery(req);
        Vector<Enchere> liste = null;
        try {
            liste = new Vector<Enchere>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Enchere(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getInt(6), res.getInt(7), res.getString(8), res.getInt(9), res.getString(10), res.getInt(11),res.getString(12) ));
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
    
}