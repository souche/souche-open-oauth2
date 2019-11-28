package com.souche.open.soucheopenoauth2example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.souche.open.common.SoucheApiException;
import com.souche.open.oauth2.AuthClient;
import com.souche.open.oauth2.vo.AccessTokenVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        // 设置视图 Model
        model.addAttribute("code", code);
        model.addAttribute("state", state);

        // 返回 View 模板 ID
        return "oauth2/callback_reception";
    }


    @GetMapping("/oauth2/getAccessToken")
    @ResponseBody
    public ResultV0 getAccessToken(
            @RequestParam(name = "code", required = true)  String code
    ) {

        AuthClient authClient = new AuthClient(serverUrl, appKey, appSecret);

        try {
            AccessTokenVO accessTokenVO = authClient.getAccessToken(code);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accessToken", accessTokenVO.getAccessToken());
            jsonObject.put("refreshToken", accessTokenVO.getRefreshToken());

            return new ResultV0(true,200,"success", jsonObject);

        }catch (SoucheApiException e){
            return new ResultV0(false,e.getErrCode(),e.getErrMessage(), new JSONObject());
        }
    }

    @GetMapping("/oauth2/getUserInfo")
    @ResponseBody
    public ResultV0 getUserInfo(
            @RequestParam(name = "accessToken", required = true)  String accessToken
    ) {

        AuthClient authClient = new AuthClient(serverUrl, appKey, appSecret);

        try {
            JSONObject jsonObject = authClient.getUserInfo(accessToken);

            return new ResultV0(true,200,"success", jsonObject);

        }catch (SoucheApiException e){
            return new ResultV0(false,e.getErrCode(),e.getErrMessage(), new JSONObject());
        }
    }


    @PostMapping("/oauth2/refreshToken")
    @ResponseBody
    public ResultV0 refreshToken(
            @RequestParam(name = "accessToken", required = true)  String accessToken,
            @RequestParam(name = "refreshToken", required = true)  String refreshToken

    ) {

        AuthClient authClient = new AuthClient(serverUrl, appKey, appSecret);

        try {
            AccessTokenVO accessTokenVO = authClient.refreshToken(accessToken, refreshToken);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accessToken", accessTokenVO.getAccessToken());
            jsonObject.put("refreshToken", accessTokenVO.getRefreshToken());

            return new ResultV0(true,200,"success", jsonObject);

        }catch (SoucheApiException e){
            return new ResultV0(false,e.getErrCode(),e.getErrMessage(), new JSONObject());
        }
    }


}
