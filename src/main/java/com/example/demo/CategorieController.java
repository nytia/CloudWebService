package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@CrossOrigin
public class CategorieController {
    @GetMapping("/categories")
    public Vector<Categorie> listeCategories() throws Exception {
        Vector<Categorie> en = null;
        try {
            en = Categorie.findAll();
        } catch (Exception e) {
            throw e;
        }
        return en;
    }
}
