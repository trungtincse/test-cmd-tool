/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.model;

import com.vng.zing.common.HReqParam;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.auth.UserManager;
import com.vng.zing.testfeature.common.TemplateManager;
import com.vng.zing.testfeature.data.database.DataStorage;
import com.vng.zing.testfeature.data.model.WhiteList;
import static com.vng.zing.testfeature.model.BaseModel.CONT_FULL_PAGE;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class UIDModel extends BaseModel {

    private static final Logger _Logger = ZLogger.getLogger(UIDModel.class);
    public static final UIDModel Instance = new UIDModel();
    public String TEMPLATE = "/view/cmds/whitelist";
    private String SCRIPT = "/view/cmds/whitelistjs";
    private UserManager userManager = UserManager.userManager;

    @Override
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        String uidTarget = HReqParam.getString(req, "uid", "0");
        JSONObject result = null;
        try {
            result = new JSONObject().put("success", true);
        } catch (JSONException ex) {
        }
        if (uidTarget.isEmpty()) {
            uidTarget = "0";
        }
        try {
            if (!checkToken(req, resp)) {
                return;
            }
            String uid_ = getUid(req);
            if (req.getMethod() == "GET") {
                renderPage(req, resp);
            } else if (req.getMethod() == "POST" && req.getParameter("action").equals("add")) {
                WhiteList whiteList = new WhiteList(uidTarget);
                try {
                    DataStorage.Instance.wlRepo.insert(whiteList);
                } catch (Exception ex) {
                }
                sendJson(resp, result);
            } else if (req.getMethod() == "POST" && req.getParameter("action").equals("delete")) {
                WhiteList whiteList = new WhiteList(uidTarget);
                try {
                    DataStorage.Instance.wlRepo.remove(whiteList);
                } catch (Exception ex) {
                }
                sendJson(resp, result);
            }
        } catch (IOException ex) {
            _Logger.error(ex.getMessage(), ex);
        } catch (TemplateException ex) {
            _Logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void renderPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, TemplateException {
        TemplateDictionary dictFullPage = new TemplateDictionary();
        TemplateDictionary dict = new TemplateDictionary();
        TemplateDictionary dictjs = new TemplateDictionary();
        renderCommon(req, resp, dictFullPage, dictjs);
        String uid = getUid(req);
        renderCommonMenu(req, resp, dictFullPage, uid);
        try {
            List<String> uids = DataStorage.Instance.wlRepo.find().toList().stream().map((obj) -> {
                return obj.uid;
            }).collect(Collectors.toList());
            dictjs.setVariable("UIDS", uids.toString());
            dictFullPage.setVariable("CURRENT_PAGE_CONTENT", TemplateManager.applyTemplate(TEMPLATE, dict));
            dictFullPage.setVariable("CURRENT_PAGE_SCRIPT", TemplateManager.applyTemplate(SCRIPT, dictjs));
            TemplateManager.print(TemplateManager.applyTemplate(CONT_FULL_PAGE, dictFullPage), resp);
        } catch (Exception ex) {
        }
    }

    @Override
    public void renderMainContent(HttpServletRequest req, HttpServletResponse resp) throws TemplateException {
    }

}
