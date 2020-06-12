/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.model;

import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.common.Config;
import com.vng.zing.testfeature.common.TemplateManager;
import static com.vng.zing.testfeature.model.BaseModel.CONT_FULL_PAGE;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author tindpt
 */
public class LoginModel {

    private static final Logger _Logger = ZLogger.getLogger(LoginModel.class);
    public static final LoginModel Instance = new LoginModel();
    public String HTML = "/view/cmds/login";
    public String SCRIPT = "/view/cmds/loginjs";
    public String url=Config.appUrl;
    public long appId=Config.appId;
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        try {
            renderPage(req, resp);
        } catch (IOException ex) {
            _Logger.error(ex.getMessage(), ex);
        } catch (TemplateException ex) {
            _Logger.error(ex.getMessage(), ex);
        }
    }

    public void renderPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, TemplateException {
        TemplateDictionary dictFullPage = new TemplateDictionary();
        dictFullPage.setVariable("URL", url);
        dictFullPage.setVariable("APP_ID", String.valueOf(appId));
        TemplateManager.print(TemplateManager.applyTemplate(HTML, dictFullPage), resp);
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) {
        doProcess(req, resp);
    }

}
