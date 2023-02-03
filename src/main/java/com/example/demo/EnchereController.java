package com.example.demo;

import org.springframework.web.bind.annotation.*;
import util.Conexion;

import java.sql.Connection;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin
public class EnchereController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/encheres")
    public Vector<Enchere> listeEnchere() throws Exception{
        Vector<Enchere> vv=new Vector<>();
        try {
            vv= Enchere.selectAllEnchere();
        } catch (Exception e) {
            throw e;
        }
            return vv;
    }

    @GetMapping("/encheres/actuel")
    public Vector<Enchere> listeEnchereActuel() throws Exception{
        Vector<Enchere> vv=new Vector<>();
        try {
            vv= Enchere.selectAllEnchereActuel();
        } catch (Exception e) {
            throw e;
        }
            return vv;
    }

    @GetMapping("/encheres/{idEnchere}")
    public Enchere listeEnchere(@PathVariable String idEnchere)  throws Exception{
        Enchere enchere=new Enchere();
        Connection con=null;
        try {
            con=Conexion.getCo();
            enchere= Enchere.selectEnchere(idEnchere, con);
        } catch (Exception e) {
            throw e;
        }finally {
            if(con!=null)
                con.close();
        }
            return enchere;
    }
    
    @PostMapping("/recherche")
    public Vector<Enchere> listeRechercheEnchere(@RequestBody Critere c) throws Exception{
        Vector<Enchere> listeRecherche=new Vector<>();
        try {
            listeRecherche= Enchere.rechercheEnchere(c);
            if(listeRecherche.isEmpty()){
                System.out.println("vide");
            }
        } catch (Exception e) {
            throw e;
        }
            return listeRecherche;
    }

    @GetMapping("/encheres/user/{idUtilisateur}")
    public Object listeEnchereByUtilisateur(@PathVariable String idUtilisateur) throws Exception {
        Vector<Enchere> vv=new Vector<>();
        try {
            vv= Enchere.selectAllEnchereByUtilisateur(idUtilisateur);
        } catch (Exception e) {
            throw e;
        }
            return vv;
    }

    @PostMapping("/encheres")
    public Object ajouterEnchere(@RequestBody Enchere enchere){
        try{
            Enchere.insertEnchere(enchere);
            System.out.println("insert ok");
        }catch(Exception e){
            return new Error(e);
        }
        return new Data(enchere);
    }
    @PutMapping("/enchere/{idenchere}")
    public Object modifierEnchere(@RequestBody Enchere enchere, @PathVariable String idenchere){
        try{
            Enchere.updateEncher(enchere,idenchere);
            System.out.println("update ok");
        }catch(Exception e){
            return new Error(e);
        }
        return  new Data(enchere);
    }

    @PostMapping("/rencherir")
    public Encherissement miser(@RequestBody Encherissement encherissement) throws Exception{
        try{
            Encherissement.rencherir(encherissement);
        }catch(Exception e){
           throw e;
        }
        return encherissement;
    }
    @PostMapping("/cloturer")
    public Object miser(){
        try{
            Enchere.cloturerEnchere();
        }catch(Exception e){
            return new Error(e);
        }
        return new Data("encheres mise à jour et transaction reussi");
    }

    @GetMapping("/encheresVita/{idUtilisateur}")
    public Object simpleListeEnchere() {
        Vector<Enchere> vv=new Vector<>();
        try {
            vv= Enchere.selectListeEnchere();
        } catch (Exception e) {
            return new Error(e);
        }
            return new Data(vv);
    }

    @GetMapping("/ficheEnchere/{idEnchere}")
    public Object getFicheEnchere(@PathVariable String idEnchere)  throws Exception{
        Enchere enchere=new Enchere();
        Connection con=null;
        try {
            con=Conexion.getCo();
            enchere= Enchere.selectFicheEnchere(idEnchere);
        } catch (Exception e) {
            return new Error(e);
        }finally {
            if(con!=null)
                con.close();
        }
            return new Data(enchere);
    }
    
}    