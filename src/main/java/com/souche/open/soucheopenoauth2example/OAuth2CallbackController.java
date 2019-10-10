package com.souche.open.soucheopenoauth2example;

import com.souche.open.SoucheOpenClient;
import com.souche.open.response.OapiUserInfoGetResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuth2CallbackController {
    @Value("${app.oauth2.host}")
    private String serverUrl;
    @Value("${app.oauth2.appKey}")
    private String appKey;
    @Value("${app.oauth2.appSecret}")
    private String appSecret;

    @GetMapping("/oauth2/callback_reception")
    public String callbackReception(
        @RequestParam(name = "code", required = true)  String code,
        @RequestParam(name = "state", required = false, defaultValue = "STATE") String state,
        Model model
    ) {
        // 置换用户信息
        SoucheOpenClient client = new SoucheOpenClient(
                serverUrl,
                appKey,
                appSecret
        );

        com.souche.open.request.OapiUserInfoGetRequest request = new com.souche.open.request.OapiUserInfoGetRequest();
        request.setCode(code);

        try {
            OapiUserInfoGetResponse resp = client.execute(request);
            // 设置返回结果
            model.addAttribute("rtnBody", resp.getBody());
        } catch (com.souche.open.ApiException e) {
            throw new RuntimeException();
        }

        // 设置视图 Model
        model.addAttribute("code", code);
        model.addAttribute("state", state);

        // 返回 View 模板 ID
        return "oauth2/callback_reception";
    }
}
