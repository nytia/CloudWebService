package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Vector;


@RestController
@CrossOrigin
public class HistoriqueController {

    @GetMapping("/historiques/{idutilisateur}")
    public Vector<Enchere> listeHistorique(@PathVariable String idutilisateur) throws Exception {
        Vector<Enchere> en = null;
        try {
            en = Historique.selectHistoriqueByIdUser(idutilisateur);
        } catch (Exception e) {
            throw e;
        }
        return en;
    }
}