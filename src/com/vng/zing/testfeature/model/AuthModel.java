/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.model;

import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.auth.AccessToken;
import com.vng.zing.testfeature.auth.DecodeUserID;
import com.vng.zing.testfeature.auth.TokenManager;
import com.vng.zing.testfeature.auth.UserManager;
import com.vng.zing.testfeature.common.Config;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class AuthModel {
    private static final Logger _Logger = ZLogger.getLogger(AuthModel.class);
    public static AuthModel Instance = new AuthModel();
    public long appId = Config.appId;
    private UserManager userManager = UserManager.userManager;
    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getMethod() == "GET") {
            try {
                if(req.getParameter("uid")==null){
                    resp.sendError(404);
                    return;
                }
                long userId = Long.valueOf(req.getParameter("uid"));
                int uid_ = DecodeUserID.decodeUserID(appId, userId);
                String code = req.getParameter("code");
                checkInfoUser(userId, uid_, code);
                AccessToken token = TokenManager.tokenManager.login(uid_);
                Cookie accessTokenCookie = new Cookie("accessToken", token.getAccessToken());
                resp.addCookie(accessTokenCookie);

                String name = userManager.getName(String.valueOf(uid_));
                String avaURL = userManager.getUrl(String.valueOf(uid_));
                if (name != null) {
                    Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
                    resp.addCookie(nameCookie);
                }
                if (avaURL != null) {
                    Cookie avaURLCookie = new Cookie("ava_url", avaURL);
                    resp.addCookie(avaURLCookie);

                }
                resp.sendRedirect("/");
            } catch (Exception ex) {
                _Logger.error(ex.getMessage(), ex);
            }
            
        } else if (req.getMethod() == "POST") {
        }
    }

    private void checkInfoUser(long userId, int uid_, String code) {
        if (userManager.checkHavingUser(String.valueOf(uid_))) {
            return;
        }

        URIBuilder builder = null;
        HttpGet request = null;
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            builder = new URIBuilder("https://oauth.zaloapp.com/v3/access_token");
            builder.setParameter("app_id", String.valueOf(appId))
                    .setParameter("app_secret", "0w1SUW8bhHhE4ezT4tSS")
                    .setParameter("code", code);
            request = new HttpGet(builder.build());
            response = client.execute(request);
            String json_str = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(json_str);
            //Requset name, avatar
            builder = new URIBuilder("https://graph.zalo.me/v2.0/me");
            builder.setParameter("access_token", json.getString("access_token"))
                    .setParameter("fields", "name,picture");
            request = new HttpGet(builder.build());
            response = client.execute(request);
            json_str = EntityUtils.toString(response.getEntity());
            json = new JSONObject(json_str);
            String ava_url = json.getJSONObject("picture").getJSONObject("data").getString("url");
            userManager.addUser(String.valueOf(uid_), json.getString("name"), ava_url);
            userManager.addRole(String.valueOf(uid_), "GUEST");
        } catch (IOException ex) {
            _Logger.error(ex.getMessage(), ex);
        } catch (URISyntaxException ex) {
            _Logger.error(ex.getMessage(), ex);
        } catch (JSONException ex) {
            _Logger.error(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) {
    }
}
