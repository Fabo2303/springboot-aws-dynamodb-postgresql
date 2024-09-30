package com.grupo5.tareas.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tareas")
    public String tareas(Model model) {
        return "tareas";
    }

    @GetMapping("/upload")
    public String upload(Model model) {
        return "upload";
    }
}
