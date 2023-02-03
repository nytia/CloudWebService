package com.example.demo;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Detail {
    private int idavion;
    private float totalkm;
    private String matricule;
    private int nombrePlace;

    public Detail() {
    }

    public Detail(int idavion, float totalkm, String matricule, int nombrePlace) {
        this.idavion = idavion;
        this.totalkm = totalkm;
        this.matricule = matricule;
        this.nombrePlace = nombrePlace;
    }
    public static Vector<Detail> selectAllDetail() throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_detail ";
        ResultSet res = sta.executeQuery(req);
        Vector<Detail> liste = null;
        try {

            liste = new Vector<Detail>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Detail(res.getInt(1),res.getFloat(2),res.getString(3),res.getInt(4)));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();

        }
        return liste;
    }
    public static Detail selectDetail(String idv) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_detail where idavion="+idv;
        ResultSet res = sta.executeQuery(req);
        Detail liste = null;
        try {

            liste = new Detail();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste=new Detail(res.getInt(1),res.getFloat(2),res.getString(3),res.getInt(4));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();

        }
        return liste;
    }

    public int getIdavion() {
        return idavion;
    }

    public void setIdavion(int idavion) {
        this.idavion = idavion;
    }

    public float getTotalkm() {
        return totalkm;
    }

    public void setTotalkm(float totalkm) {
        this.totalkm = totalkm;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }
}
