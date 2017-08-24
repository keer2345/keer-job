package com.keer.job.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * index controller
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}
