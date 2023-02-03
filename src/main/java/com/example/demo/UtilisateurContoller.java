package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Vector;

@RestController
@CrossOrigin
public class UtilisateurContoller {
    @GetMapping("/utilisateurs")
    public Object listeUtilisateur() {
//    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        Vector<Utilisateur> uu=new Vector<>();
        try {
            uu=Utilisateur.getAllUtilisateur();
        } catch (Exception e) {
            return new Error(e);
        }
        return  new Data(uu);
    }

    @PostMapping("/utilisateurs/login")
    public Object login(@RequestBody Utilisateur u){
        Vector<Utilisateur> list = null;
        try{
           list = new Vector<>();
           list = u.getAllUtilisateur();
           int x=0;
            for (int i = 0; i < list.size(); i++) {
               if(u.getIdentifiant().equals(list.get(i).getIdentifiant()) && u.getMdp().equals(list.get(i).getMdp()) ) {
                   String hash=new Token().createMD5Hash(u.getIdentifiant()+u.getMdp()+LocalDateTime.now().toString());
                   Token tkn=new Token(hash,LocalDateTime.now().plusMinutes(15),list.get(i).getId());
                   Token.insertToken(tkn);
                   x++;
                   System.out.println("ok");
                   return  new Data(tkn);
               }
            }
            return new Error(new Exception("Informations incorrectes"));
//            if(x==0) System.out.println("diso");
        }catch(Exception e){
            return new Error(e);
        }
    }
     @PostMapping("/utilisateur/login")
    public Token loginFront(@RequestBody Utilisateur u) throws Exception{
        Vector<Utilisateur> list = null;
        try{
           list = new Vector<>();
           list = u.getAllUtilisateur();
           int x=0;
            for (int i = 0; i < list.size(); i++) {
               if(u.getIdentifiant().equals(list.get(i).getIdentifiant()) && u.getMdp().equals(list.get(i).getMdp()) ) {
                   String hash=new Token().createMD5Hash(u.getIdentifiant()+u.getMdp()+LocalDateTime.now().toString());
                   Token tkn=new Token(hash,LocalDateTime.now().plusMinutes(15),list.get(i).getId());
                   Token.insertToken(tkn);
                   x++;
                   System.out.println("ok");
                   return  tkn;
               }
            }
            throw new Exception("Informations incorrectes");
//            if(x==0) System.out.println("diso");
        }catch(Exception e){
            throw e;
        }
    }

    @PostMapping("/check")
    public Object checkExpiration(@RequestBody Token tok){
        try {
            return new Data(Token.checkTokenValidity(tok.getValeur()));
        }catch (Exception e){
            return  new Error(e);
        }
    }

    @PostMapping("/recharger")
    public Object rechargement(@RequestParam int id , @RequestParam double montant){
        try {
            Utilisateur.insertRecharger(id,montant);
            return new Data("inserer");
        }catch (Exception e){
            return  new Error(e);
        }
    }

    @PostMapping("/deconnexion")
    public Object deconnect(@RequestBody Token tok){
        try {
            Token.deleteToken(tok.getValeur());
            return new Data("deconnecte");
        }catch (Exception e){
            return  new Error(e);
        }

    }
    @PostMapping("/ajouter")
    public Object ajouterUtilisateur(@RequestBody Utilisateur utilisateur){
        try{
            Utilisateur.insertUtilisateur(utilisateur);
            System.out.println("insert ok");
        }catch(Exception e){
            return new Error(e);
        }
        return new Data(utilisateur);
    }
}
