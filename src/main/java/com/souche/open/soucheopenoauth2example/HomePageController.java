package com.souche.open.soucheopenoauth2example;

import com.alibaba.fastjson.JSONObject;
import com.souche.open.oauth2.AuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {
    @Value("${app.oauth2.host}")
    private String serverUrl;
    @Value("${app.oauth2.appKey}")
    private String appKey;
    @Value("${app.oauth2.appSecret}")
    private String appSecret;
    @Value("${server.port}")
    private String port;
    @Value("${app.oauth2.scope}")
    private String scope;
    @Value("${app.oauth2.redirectUri}")
    private String redirectUri;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("host", serverUrl);
        model.addAttribute("appKey", appKey);
        model.addAttribute("port", port);

        AuthClient authClient = new AuthClient(serverUrl, appKey, appSecret);

        String url = authClient.getAuthorizeUrl(appKey, redirectUri, "STATE", "code", scope);



        model.addAttribute("url", url);

        return "index";
    }


    @GetMapping("/authUrl")
    public String getAuthUrl(){

        return  "oauth2/";
    }

}
