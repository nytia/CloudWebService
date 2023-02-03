package com.example.demo;
import org.springframework.web.bind.annotation.*;
import util.Conexion;

import java.sql.Connection;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Vector;

@RestController
@CrossOrigin
public class RechargeController {
    @PostMapping("/rechargement")
    public Object ajouterRecharge(@RequestBody Recharge recharge){
        try{
            Recharge.insertRecharge(recharge);
            System.out.println("insert recharge ok");
        }catch(Exception e){
            return new Error(e);
        }
        return new Data(recharge);
    }
}