package com.example.demo;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Compte {
    private int id;
    private Utilisateur utilisateur;
    private double solde;


    public Compte() {
    }

    public Compte(int id, Utilisateur utilisateur, double solde) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.solde = solde;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    public static boolean checkSoldeSuffisant(double mise,int idutilisateur,Connection con)throws Exception {
        try{
            Compte compte=findByIdUser(idutilisateur,con);
            double montantbloque=getMontantBlocage(idutilisateur,con);
            if(mise<=(compte.getSolde()-montantbloque)) return true;
            return false;
        }catch (Exception e){
            throw e;
        }
    }

    public static Compte findByIdUser(int idutilisateur,Connection con) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_compte where idutilisateur="+idutilisateur;
        ResultSet res = sta.executeQuery(req);
        Compte liste = null;
        try {
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste=new Compte(res.getInt(1),new Utilisateur(res.getInt(2),res.getString(3)),res.getDouble(4));
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
    public static double getMontantBlocage(int idutilisateur,Connection con) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_blocage where idutilisateur="+idutilisateur;
        ResultSet res = sta.executeQuery(req);
        double montant=0;
        try {
            res.first();
            res.beforeFirst();
            while (res.next()) {
                montant= res.getDouble(3);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();
        }
        return montant;
    }
    public static void deleteBlocage(int idenchere,Connection con) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "delete from blocage where idenchere="+idenchere;
            sta.executeUpdate(req);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
    public static void addBlocage(Encherissement ench,Connection con) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into blocage values("+ench.getIdutilisateur()+","+ench.getIdenchere()+","+ench.getPrix()+")";
            sta.executeUpdate(req);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
}
