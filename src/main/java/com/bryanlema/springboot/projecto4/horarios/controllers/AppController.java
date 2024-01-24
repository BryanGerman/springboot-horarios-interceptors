package com.bryanlema.springboot.projecto4.horarios.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("title", "Bienvenidos al sistema de atención");
        map.put("date", new Date());
        map.put("message", request.getAttribute("message"));
        return ResponseEntity.ok(map);

    }

}
