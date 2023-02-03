package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TokenController {


    @PostMapping("/testToken")
    public String testToken(){
        try{
        return new Token().createMD5Hash("202cb962ac59075b964b07152d234b70");
        }catch (Exception e){
           e.printStackTrace();
        }
        return "tsy nety";
    }
}
