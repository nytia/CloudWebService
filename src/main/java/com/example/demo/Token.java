package com.example.demo;

import util.Conexion;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;

public class Token {
    private int id;
    private String valeur;
    private LocalDateTime expiration;

    private int idUtilisateur;

    public Token() {
    }

    public Token(String valeur, LocalDateTime expiration, int idUtilisateur) {
        this.valeur = valeur;
        this.expiration = expiration;
        this.idUtilisateur = idUtilisateur;
    }

    public Token(int id, String valeur, Timestamp expiration, int idUtilisateur) {
        this.id = id;
        this.valeur = valeur;
        this.expiration = expiration.toLocalDateTime();
        this.idUtilisateur = idUtilisateur;
    }


    public String createMD5Hash(final String input)
            throws NoSuchAlgorithmException {
        String hashtext = null;
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Compute message digest of the input
        byte[] messageDigest = md.digest(input.getBytes());

        hashtext = convertToHex(messageDigest);

        return hashtext;
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }

    public static void deleteToken( String tk) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "delete from token where valeur='"+tk+"'";
            sta.executeUpdate(req);
            System.out.println("token supprime");
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
            if(con !=null)
                con.close();
        }
    }
    public static void insertToken( Token tk) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into token(valeur,expiration,idutilisateur) values ('" + tk.getValeur()+ "','"
                    + tk.getExpiration() +"',"+ tk.getIdUtilisateur()+")";
            sta.executeUpdate(req);
            System.out.println("token cree");
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
            if(con !=null)
                con.close();
        }
    }
    public static Token findByValue(String valeur) throws Exception {
        Connection con= Conexion.getCo();
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from token where valeur='"+valeur+"'";
        ResultSet res = sta.executeQuery(req);
        Token tk = null;
        try {

            tk = new Token();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                tk=new Token(res.getInt(1),res.getString(2),res.getTimestamp(3),res.getInt(4));
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
        return tk;
    }
    public static Object checkTokenValidity(String valeur) throws Exception{
        try {
            Token tk=Token.findByValue(valeur);
            if(tk.expiration.isBefore(LocalDateTime.now())){
                return new Error(new Exception("token expire"));
            }else return new Data(tk);
        }catch (Exception e){
            throw e;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}