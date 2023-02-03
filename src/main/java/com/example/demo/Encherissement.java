package com.example.demo;

import util.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Encherissement {
    private int idenchere;
    private double prix;
    private int idutilisateur;
    private int idvendeur;

    public Encherissement() {
    }

    public Encherissement(int idenchere, double prix, int idutilisateur) {
        this.idenchere = idenchere;
        this.prix = prix;
        this.idutilisateur = idutilisateur;
    }

    public Encherissement(int idenchere, double prix, int idutilisateur, int idvendeur) {
        this.idenchere = idenchere;
        this.prix = prix;
        this.idutilisateur = idutilisateur;
        this.idvendeur = idvendeur;
    }

    public static void rencherir(Encherissement encherissement) throws Exception{
        Connection con=null;
        try {
            con=Conexion.getCo();
            con.setAutoCommit(false);
            int idvendeur=Enchere.getIdUser(encherissement.getIdenchere(),con);
            if(idvendeur!=encherissement.getIdutilisateur()){
                if((Compte.checkSoldeSuffisant(encherissement.getPrix(),encherissement.getIdutilisateur(),con))) {
                    double prixfarany=0;
                    Encherissement dernier=Encherissement.getLast(encherissement.getIdenchere(),con);
                    if(dernier==null)
                        prixfarany = Enchere.selectEnchere(String.valueOf(encherissement.getIdenchere()), con).getPrix();

                    else if(dernier!=null&&dernier.getIdutilisateur()==encherissement.getIdutilisateur())
                        throw new Exception("Vous etes le dernier à avoir miser sur cette enchere");
                    else if(dernier!=null&&dernier.getPrix()>= encherissement.getPrix())
                        throw new Exception("Vous devez miser plus");
                    else
                        prixfarany=dernier.getPrix();
                    if(encherissement.getPrix()<=prixfarany)
                        throw new Exception("Vous ne pouvez pas rencherir car votre mise est insuffidante");
                    else {
                        if(dernier!=null) delete(encherissement.getIdenchere(),con);
                        save(encherissement, con);
                        Compte.deleteBlocage(encherissement.getIdenchere(), con);
                        Compte.addBlocage(encherissement, con);
                        con.commit();
                    }
                }else  throw new Exception("Votre solde est insuffisant pour cette mise.Veuillez recharger votre compte");

            }
            else throw new Exception("Vous ne pouvez pas participer à votre propre enchere");
        }catch(Exception e){
            con.rollback();
            throw e;
        }
        finally {
            if(con!=null)
                con.close();
        }
    }
    public static void save(Encherissement encherissement,Connection con) throws Exception{
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into encherissement(idenchere,prix,idutilisateur) values ("+encherissement.getIdenchere()+","+encherissement.getPrix()+","+encherissement.getIdutilisateur()+")";
            sta.executeUpdate(req);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (sta != null)
                sta.close();
//            if(con !=null)
//                con.close();
        }
    }
    public static Encherissement getLast(int idenchere,Connection con) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from encherissement where idenchere="+idenchere;
        ResultSet res = sta.executeQuery(req);
        Encherissement liste = null;
        try {
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste=new Encherissement(res.getInt(1),res.getDouble(2),res.getInt(3));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();
//            if(con !=null)
//                con.close();
        }
        return liste;
    }
    public static Encherissement getLast2(int idenchere,int iduser,Connection con) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_encherissement where idenchere="+idenchere+" and idutilisateur!="+iduser+" and idvendeur!="+iduser+" limit 1" ;
        ResultSet res = sta.executeQuery(req);
        Encherissement liste = null;
        try {
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste=new Encherissement(res.getInt(1),res.getDouble(2),res.getInt(3));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();
//            if(con !=null)
//                con.close();
        }
        return liste;
    }
    public static void delete(int idenchere,Connection con) throws Exception{
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req="delete from encherissement where idenchere="+idenchere;
            sta.executeUpdate(req);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (sta != null)
                sta.close();
//            if(con !=null)
//                con.close();
        }
    }
    public int getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(int idenchere) {
        this.idenchere = idenchere;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public int getIdvendeur() {
        return idvendeur;
    }

    public void setIdvendeur(int idvendeur) {
        this.idvendeur = idvendeur;
    }
}
