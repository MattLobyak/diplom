package ru.matthew.MyShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "bye";
    }
}
