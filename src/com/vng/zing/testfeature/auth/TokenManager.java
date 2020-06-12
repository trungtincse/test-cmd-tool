/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vng.zing.configer.ZConfig;
import org.apache.log4j.Logger;
import com.vng.zing.logger.ZLogger;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 *
 * @author tindpt
 */
public class TokenManager {
    
    private static Logger _Logger = ZLogger.getLogger(TokenManager.class);
    public static TokenManager tokenManager = new TokenManager();
    private static int TIME_7_DAYS_IN_SECONDS = 7 * 24 * 60 * 60;
    private static long TIME_7_DAYS_IN_MILISECONDS = TIME_7_DAYS_IN_SECONDS * 1000;
    
    public static int TOKEN_EXPIRATION_TIME_IN_SECONDS = TIME_7_DAYS_IN_SECONDS;
    public static long TOKEN_EXPIRATION_TIME_IN_MILISECONDS = TIME_7_DAYS_IN_MILISECONDS;
    private Algorithm algorithm;
    private String issuer = "commandtesttool";
    private JWTVerifier verifier;
    private String _secret;
    
    private TokenManager() {
        try {
            _secret = ZConfig.Instance.getString(TokenManager.class, "main", "secret", "");
            algorithm = Algorithm.HMAC256(_secret);
            verifier = JWT.require(algorithm).withIssuer(issuer).build();
            
        } catch (IllegalArgumentException ex) {
            _Logger.error(ex.getMessage(), ex);
        } catch (UnsupportedEncodingException ex) {
            _Logger.error(ex.getMessage(), ex);
            
        }
    }
    
    private AccessToken createAndSignToken(int uid) {
        AccessToken res = null;
        Date expiresAt = new Date();
        expiresAt.setTime(expiresAt.getTime() + TOKEN_EXPIRATION_TIME_IN_MILISECONDS);
        String token = JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(expiresAt)
                .withClaim("uid", uid)
                .sign(algorithm);
        res = new AccessToken(token, expiresAt.getTime());
        return res;
        
    }
    
    public boolean verifyToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        try {
            DecodedJWT jwt = verifier.verify(token);
            int uid = jwt.getClaim("uid").asInt();
            if (UserManager.userManager.checkHavingUser(String.valueOf(uid))) {
                return true;
            }
            return false;
            
        } catch (JWTVerificationException ex) {
            _Logger.error(ex.getMessage(), ex);
            return false;
        }
    }
    
    public String getUid(String token) {
        String uid = null;
        if (token == null || token.isEmpty()) {
            return null;
        }
        try {
            DecodedJWT jwt = verifier.verify(token);
            uid = String.valueOf(jwt.getClaim("uid").asInt());
            
        } catch (JWTVerificationException ex) {
            _Logger.error(ex.getMessage(), ex);
        } finally {
            return uid;
        }
    }
    
    public AccessToken login(int uid) throws Exception {
        AccessToken accessToken = createAndSignToken(uid);
        return accessToken;
    }
}
