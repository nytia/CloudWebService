package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@CrossOrigin
public class DetailController {


    @GetMapping("/details")
    public Object listeDetail() {
        Vector<Detail> vv=new Vector<>();
        try {
            vv=Detail.selectAllDetail();
        } catch (Exception e) {
            new Error(e);
        }
        return new Data(vv) ;
    }

    @GetMapping("/details/{idv}")
    public Object listeDetail(@PathVariable String idv) {
        Detail v=new Detail();
        try {
            v=Detail.selectDetail(idv);
        } catch (Exception e) {
            new Error(e);
        }
        return new Data(v) ;
    }

    //public Vector<User> getUser() {
//    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));

}
