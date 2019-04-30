package com.example.docker.spring.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cromero
 */
@RestController
public class HelloController {

    public static String RESULT ="hello pepe billy" ;

    @RequestMapping("/greeting")
    public String greeting() {
        return RESULT;
    }

}
