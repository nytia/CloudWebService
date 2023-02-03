package com.example.demo;

import org.springframework.web.bind.annotation.*;
import util.Conexion;

import java.util.Vector;

@RestController
@CrossOrigin
public class EncherissementController {
    @GetMapping("/encherissement")
    public Encherissement listeEnchere(@RequestBody Encherissement encherissement) throws Exception{
        Encherissement vv=null;
        try {
            vv= Encherissement.getLast2(encherissement.getIdenchere(),encherissement.getIdutilisateur(), Conexion.getCo());
        } catch (Exception e) {
            throw e;
        }
        return vv;
    }
}
