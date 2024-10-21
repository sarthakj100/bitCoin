package com.google.googleTransalateApi.controller;

import com.google.googleTransalateApi.model.Text;
//import com.google.googleTransalateApi.response.Response;
import com.google.googleTransalateApi.service.TransalateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate-service")
public class GoogleTranslateController {

    @Autowired
    TransalateService transalateService;

    @PostMapping("/translate")
    ResponseEntity<String> fun(@RequestBody Text text){
        String message =text.getMessage();
        return transalateService.transalateMessage(message);
    }
    @GetMapping("/bitcoin")
    String fun2 (){
        return transalateService.bitcoinPrice();
    }
}
