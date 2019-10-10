package com.souche.open.soucheopenoauth2example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {
    @GetMapping("/oauth2/callback_reception")
    public String callbackReception(
        @RequestParam(name = "code", required = true)  String code,
        @RequestParam(name = "state", required = false, defaultValue = "STATE") String state,
        Model model
    ) {
        // 从 URL 部分得到的原始信息
        model.addAttribute("code", code);
        model.addAttribute("state", state);

        //
        return "oauth2/callback_reception";
    }
}
