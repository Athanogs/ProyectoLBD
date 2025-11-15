package com.grupo3.ProyectoLBD.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping("/layout")
    public String verLayout(Model model) {
        return "layout";
    }
}
