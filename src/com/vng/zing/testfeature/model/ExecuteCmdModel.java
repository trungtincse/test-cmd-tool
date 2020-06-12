/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.vng.zing.testfeature.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vng.zing.common.HReqParam;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.common.TemplateManager;
import com.vng.zing.testfeature.data.database.DataStorage;
import com.vng.zing.testfeature.data.model.Command;
import static com.vng.zing.testfeature.model.BaseModel.CONT_FULL_PAGE;
import com.zalo.zte.testworker.webserver.ExecSubcmd;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class ExecuteCmdModel extends BaseModel {

    private static final Logger _Logger = ZLogger.getLogger(ExecuteCmdModel.class);
    public static final ExecuteCmdModel Instance = new ExecuteCmdModel();
    public String CMD_TEMPLATE = "/view/cmds/execute";
    public String FORM_TEMPLATE = "/view/cmds/form";
    public String INPUT_SNIPPET_TEMPLATE = "/view/commonsnippets/forminput";
    private String SCRIPT = "/view/cmds/executejs";

    @Override
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkToken(req, resp)) {
            return;
        }
        String uid = getUid(req);
        if (req.getMethod() == "GET") {
            try {
                renderPage(req, resp);
            } catch (IOException | TemplateException ex) {
                java.util.logging.Logger.getLogger(ExecuteCmdModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            excuteCmd(req, resp);
        }
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        doProcess(req, resp);
    }

    @Override
    public void renderPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, TemplateException, JsonProcessingException {
        TemplateDictionary dictFullPage = new TemplateDictionary();
        TemplateDictionary dict = new TemplateDictionary();
        TemplateDictionary dictjs = new TemplateDictionary();
        renderCommon(req, resp, dictFullPage, dictjs);
        String uid = getUid(req);
        renderCommonMenu(req, resp, dictFullPage, uid);
        String cmd = HReqParam.getString(req, "cmd", "");
        Command cmdIns = DataStorage.Instance.cmdRepo.find(eq("number", cmd)).firstOrDefault();
        if (cmdIns == null) {
            try {
                sendJson(resp, new JSONObject().put("message", "cmd not found"));
            } catch (JSONException ex) {
            }
        }
        dictjs.setVariable("CMD", cmd);
        try {
            dictjs.setVariable("DATA", DataStorage.Instance.getCMDJson(cmd));
        } catch (JSONException ex) {
        }

        List<String> uids = DataStorage.Instance.wlRepo.find().toList().stream().map((obj) -> {
            return obj.uid;
        }).collect(Collectors.toList());
        dict.setVariable("UIDS", optionUids(uids));
        dictFullPage.setVariable("CURRENT_PAGE_CONTENT", TemplateManager.applyTemplate(CMD_TEMPLATE, dict));
        dictFullPage.setVariable("CURRENT_PAGE_SCRIPT", TemplateManager.applyTemplate(SCRIPT, dictjs));
        TemplateManager.print(TemplateManager.applyTemplate(CONT_FULL_PAGE, dictFullPage), resp);
    }

    @Override
    public void renderMainContent(HttpServletRequest req, HttpServletResponse resp) throws TemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String optionUids(List<String> uids) {
        String strTemplate = " <option value=\"%s\" selected>%s</option>";
        List<String> result = new ArrayList<>();
        for (int i = 0; i < uids.size(); i++) {
            String uid = uids.get(i);
            if (i != 0) {
                strTemplate = strTemplate.replace("selected", "");
            }
            result.add(String.format(strTemplate, uid, uid));
        }
        return result.toString().replace("[", "").replace("]", "");
    }

    private void excuteCmd(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            JSONObject body = new JSONObject(requestData);
            int uid = body.getInt("uid");
            Short cmd = HReqParam.getShort(req, "cmd", (short) 0);
            Byte subcmd = HReqParam.getByte(req, "subcmd", (byte) 0);
            JSONArray params = body.getJSONArray("params");
            JSONObject result = ExecSubcmd.execute1(cmd, subcmd, body);

            sendJson(resp, result);
        } catch (IOException | JSONException ex) {
            java.util.logging.Logger.getLogger(ExecuteCmdModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
