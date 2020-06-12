package com.vng.zing.testfeature.model;

import com.vng.zing.common.HReqParam;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.common.TemplateManager;
import com.vng.zing.testfeature.data.database.DataStorage;
import com.vng.zing.testfeature.data.model.Feature;
import com.vng.zing.testfeature.data.model.SubCmd;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class DeleteModel extends BaseModel {

    private static final Logger _Logger = ZLogger.getLogger(DeleteModel.class);
    public static final DeleteModel Instance = new DeleteModel();
    public String HTML_TEMPLATE = "view/cmds/delete_resource";
    public String SCRIPT = "view/cmds/delete_resourcejs";

    @Override
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkToken(req, resp)) {
            return;
        }
        if (req.getMethod() == "GET") {
            deleteCmd(req, resp);
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            resp.setHeader("Location", "/");
        }
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        doProcess(req, resp);
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
            dictjs.setVariable("FEAS", listFeature());
            dictjs.setVariable("FEA_MAP", mapFeature());
//            dictjs.setVariable("CMDS", listCmd());
            dictFullPage.setVariable("CURRENT_PAGE_CONTENT", TemplateManager.applyTemplate(HTML_TEMPLATE, dict));
            dictFullPage.setVariable("CURRENT_PAGE_SCRIPT", TemplateManager.applyTemplate(SCRIPT, dictjs));
            TemplateManager.print(TemplateManager.applyTemplate(CONT_FULL_PAGE, dictFullPage), resp);
        } catch (Exception ex) {
            _Logger.error(ex.getMessage(), ex);
        }
    }

    public void renderMainContent(HttpServletRequest req, HttpServletResponse resp) throws TemplateException {
        try {
            TemplateDictionary dictMainContent = new TemplateDictionary();
            dictMainContent.setVariable("FEAS", listFeature());
//            dictMainContent.setVariable("CMDS", listCmd());
            TemplateManager.print(TemplateManager.applyTemplate(HTML_TEMPLATE, dictMainContent), resp);
        } catch (Exception ex) {
            _Logger.error(ex.getMessage(), ex);
        }
    }

    public String listCmd() throws Exception {
        ArrayList<String> listcmd = new ArrayList<>();
        DataStorage.Instance.feaRepo.find().forEach((fea) -> {
            List<String> cmds = fea.commands;
            for (String cmd : cmds) {
                listcmd.add(String.format("\"%s\"", cmd));
            }
        });
        return listcmd.toString();
    }

    private void deleteFea(HttpServletRequest req, HttpServletResponse resp) {
        String slugname = HReqParam.getString(req, "name", "");
        if (!slugname.isEmpty()) {
            DataStorage.Instance.deleteFea(slugname);
        }
    }

    private void deleteCmd(HttpServletRequest req, HttpServletResponse resp) {
        String cmd = HReqParam.getString(req, "cmd", "");
        DataStorage.Instance.deleteCmd(cmd);
    }
}
