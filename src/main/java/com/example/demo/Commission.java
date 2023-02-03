package com.example.demo;


import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Commission {
    private int id;
    private double valeur;

    public Commission(int id, double valeur) {
        this.id = id;
        this.valeur = valeur;
    }

    public Commission() {
    }

    public Commission(double valeur) {
        this.valeur = valeur;
    }

    public static void save( Commission ve) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into commission(valeur) values("+ve.getValeur()+")";
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
            if (con != null)
                con.close();
        }
    }

    public static Commission getLast(Connection con) throws  Exception{
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from commission order by id desc limit 1 ";
        ResultSet res = sta.executeQuery(req);
        Commission com=null;
        try {
            res.first();
            res.beforeFirst();
            while (res.next()) {
                com=new Commission(res.getInt(1),res.getDouble(2));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();
//            if (con != null)
//                con.close();
        }
        return com;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
}
