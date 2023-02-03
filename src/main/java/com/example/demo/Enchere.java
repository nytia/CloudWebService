package com.example.demo;

import util.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Enchere{
    private int id;
    private String date;
    private String description;
    private int duree;
    private String produit;
    private double prix;
    private int idUtilisateur;
    private String identifiant;
    private int idCategorie;
    private String nomCategorie;
    private int statut;
    private String photo;

    public Enchere(int id, String date, String description, int duree, String produit, double prix, int idUtilisateur, int idCategorie, int statut, String photo) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.duree = duree;
        this.produit = produit;
        this.prix = prix;
        this.idUtilisateur = idUtilisateur;
        this.idCategorie = idCategorie;
        this.statut = statut;
        this.photo = photo;
    }
    public Enchere(String categorie, String produit){
        this.nomCategorie = categorie;
        this.produit = produit;
    }

    public Enchere(int id, String date, String description, int duree, String produit, int prix, int idUtilisateur, String identifiant, int idCategorie, String nomCategorie, int statut, String photo){
        this.id = id;
        this.date = date;
        this.description = description;
        this.duree = duree;
        this.produit = produit;
        this.prix = prix;
        this.idUtilisateur = idUtilisateur;
        this.identifiant = identifiant;
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        this.statut = statut;
        this.photo = photo;
    }
    public Enchere(){}

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdentifiant() {
        return identifiant;
    }
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return this.duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getProduit() {
        return this.produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdCategorie() {
        return this.idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getStatut() {
        return this.statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public static void insertEnchere(Enchere enchere) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into enchere(description, duree, produit, prix, idutilisateur, idcategorie, photo) values ('"
                    + enchere.getDescription() + "'," + enchere.getDuree() + ",'"+ enchere.getProduit() + "'," + enchere.getPrix() + "," + 
                    enchere.getIdUtilisateur() + "," + enchere.getIdCategorie() +",'" + enchere.getPhoto()+ "')";
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }

    public static Vector<Enchere> selectAllEnchere() throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_enchere order by id";
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
    public static Vector<Enchere> selectAllEnchereActuel() throws Exception {
            Connection con= Conexion.getCo();
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String req = "select * from v_enchere where statut=0 order by id";
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

    public static Enchere selectFicheEnchere(String idEnchere) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from enchere where id="+idEnchere;
        ResultSet res = sta.executeQuery(req);
        Enchere liste = null;
        try {

            liste = new Enchere();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste = new Enchere(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5),res.getInt(6),res.getInt(7), res.getInt(8),res.getInt(9),res.getString(10));
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

    public static Enchere selectEnchere(String idEnchere , Connection con) throws Exception {
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_enchere where id="+idEnchere;
        ResultSet res = sta.executeQuery(req);
        Enchere liste = null;
        try {

            liste = new Enchere();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste= new Enchere(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getInt(6), res.getInt(7), res.getString(8), res.getInt(9), res.getString(10), res.getInt(11),res.getString(12) );
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



    public static Vector<Enchere> rechercheEnchere(Critere c) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        Vector<Enchere> listeRecherche = null;
        String sql = "select * from v_enchere where 1=1 and statut=0 ";
        if(!c.getCategorie().equals("")){
            sql += " and idCategorie = '"+c.getCategorie()+"'";
        }
        if(!c.getMotCle().equals("")){
            sql += " and description like '%"+ c.getMotCle() +"%'";
        }
        if(!c.getDate().equals("")){
            sql += " and date <= '"+c.getDate()+"'";
        }
        if(!c.getPrixMax().equals("")){
            sql += " and prix <= "+c.getPrixMax()+"";
        }
        sql+=" order by id";
        ResultSet res = sta.executeQuery(sql);

        try {
            listeRecherche = new Vector<Enchere>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                listeRecherche.add(new Enchere(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getInt(6), res.getInt(7), res.getString(8), res.getInt(9), res.getString(10), res.getInt(11),res.getString(12) ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();

        }
        return listeRecherche;
    }
    public static Vector<Enchere> GetHistoriqueByUtilisateur(String idUtilisateur) throws Exception {
        Connection con= Conexion.getCo();
        String req = "select * from v_historique where idUtilisateur = " + idUtilisateur;
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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

        }
        return liste;
    }
    public static Vector<Enchere> GetAllHistorique() throws Exception {
        Connection con= Conexion.getCo();
        String req = "select * from v_historique";
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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

        }
        return liste;
    }

    public static void updateEncher(Enchere enchere, String idEnchere) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "update enchere set duree='"+enchere.getDuree()+"',prix='"+ enchere.getPrix() +"' where id="+idEnchere;
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
            if(con !=null)
                con.close();
        }
    }

    public static int getIdUser(int idenchere,Connection con) throws Exception{
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from enchere where id="+idenchere;
        ResultSet res = sta.executeQuery(req);
        int idutilisateur=0;
        try {
            res.first();
            res.beforeFirst();
            while (res.next()) {
                idutilisateur= res.getInt(7);
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
        return idutilisateur;
    }

    public static Vector<Enchere> getExpires(Connection con) throws Exception{
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_enchereexpire ";
        ResultSet res = sta.executeQuery(req);
        Vector<Enchere> liste = null;
        try {
            liste = new Vector<Enchere>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Enchere(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5),res.getDouble(6),res.getInt(7), res.getInt(8), res.getInt(9),res.getString(10)));
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
    public static void updateStatutAndMakeTransaction(Connection con,Vector<Enchere> expires) throws  Exception{
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            for (int i = 0; i < expires.size(); i++) {
                Encherissement encherissement=Encherissement.getLast(expires.get(i).getId(),con);
                if(encherissement!=null)
                    transactionFinale(encherissement,con);
                String req ="update enchere set statut=1 where id="+expires.get(i).getId();
                sta.executeUpdate(req);
            }
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
    public static void cloturerEnchere() throws  Exception{
        Connection con=null;
        try {
            con=Conexion.getCo();
            con.setAutoCommit(false);
            Vector<Enchere> expires=getExpires(con);
            updateStatutAndMakeTransaction(con,expires);
        }catch (Exception e){
            throw e;
        }finally {
            if (con != null)
                con.close();
        }
    }
    public static void retirerMiseGagnant(Encherissement encherissement,Connection con) throws Exception{
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            Compte cp=Compte.findByIdUser(encherissement.getIdutilisateur(),con);
            String req ="insert into transactioncompte(idcompte,montant) values("+cp.getId()+",-"+encherissement.getPrix()+")";
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
    public static void addPartVendeur(Encherissement encherissement,Connection con) throws Exception{
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            Enchere enchere= selectEnchere(Integer.toString(encherissement.getIdenchere()),con);
            Compte cp=Compte.findByIdUser(enchere.getIdUtilisateur(),con);
            Commission com=Commission.getLast(con);
            System.out.println("valeur commission: "+com.getValeur());
            double part=encherissement.getPrix()-(encherissement.getPrix()* com.getValeur());
            String req ="insert into transactioncompte(idcompte,montant) values("+cp.getId()+","+part+")";
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


    public static void transactionFinale(Encherissement encherissement,Connection con) throws  Exception{
        try {
            retirerMiseGagnant(encherissement,con);
            addPartVendeur(encherissement,con);
            con.commit();
        }catch (Exception e){
            con.rollback();
            throw e;
        }
    }

    public static Vector<Enchere> selectAllEnchereByUtilisateur(String idUtilisateur) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/enchere", "postgres", "root");
        String req = "select * from v_enchere where idUtilisateur = " + idUtilisateur;
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet res = sta.executeQuery(req);
        Vector<Enchere> liste = null;
        try {
            liste = new Vector<Enchere>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Enchere(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getInt(6), res.getInt(7), res.getString(8), res.getInt(9), res.getString(10), res.getInt(11), res.getString(12)));
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

    public static Vector<Enchere> selectListeEnchere() throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_listEnchere";
        ResultSet res = sta.executeQuery(req);
        Vector<Enchere> liste = null;
        try {
            liste = new Vector<Enchere>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Enchere(res.getString(1), res.getString(2)));
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
    
