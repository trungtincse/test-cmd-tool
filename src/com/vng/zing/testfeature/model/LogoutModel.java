/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.model;

import com.vng.zing.logger.ZLogger;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author tindpt
 */
public class LogoutModel {

    private static final Logger _Logger = ZLogger.getLogger(LogoutModel.class);
    public static final LogoutModel Instance = new LogoutModel();

    public void process(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] deleteCookies = req.getCookies();
        if (deleteCookies == null) {
            return;
        }
        for (Cookie deleteCookie : deleteCookies) {
            deleteCookie.setMaxAge(0);
            resp.addCookie(deleteCookie);
        }
    }

}
