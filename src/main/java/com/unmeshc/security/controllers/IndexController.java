package com.unmeshc.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by uc on 10/9/2019
 */
@Controller
public class IndexController {

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("welcome", "Welcome to Monad Computer");
        return "index";
    }
}
