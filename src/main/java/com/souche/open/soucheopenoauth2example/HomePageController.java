package com.souche.open.soucheopenoauth2example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @Value("${app.oauth2.host}")
    private String serverUrl;
    @Value("${app.oauth2.appKey}")
    private String appKey;
    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("host", serverUrl);
        model.addAttribute("appKey", appKey);
        model.addAttribute("port", port);

        return "index";
    }
}
