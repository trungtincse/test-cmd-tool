package com.vng.zing.testfeature.model;

import com.vng.zing.common.HReqParam;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.common.TemplateManager;
import com.vng.zing.testfeature.data.database.DataStorage;
import com.vng.zing.testfeature.data.model.Command;
import com.vng.zing.testfeature.data.model.Feature;
import com.vng.zing.testfeature.data.model.Param;
import com.vng.zing.testfeature.data.model.SubCmd;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class DetailModel extends BaseModel {

    private static final Logger _Logger = ZLogger.getLogger(DetailModel.class);
    public static final DetailModel Instance = new DetailModel();
    public String HTML_TEMPLATE = "view/cmds/update";
    public String SCRIPT = "view/cmds/createjs";

    @Override
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkToken(req, resp)) {
            return;
        }
        try {
            renderPage(req, resp);
        } catch (IOException | TemplateException ex) {
            java.util.logging.Logger.getLogger(DetailModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        doProcess(req, resp);
    }

    @Override
    public void renderPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, TemplateException {
        String cmd = HReqParam.getString(req, "cmd", "");
        Command cmdIns = DataStorage.Instance.cmdRepo.find(eq("number", cmd)).firstOrDefault();
        if (cmd.isEmpty() || cmdIns == null) {
            try {
                sendJson(resp, new JSONObject().put("message", "cmd not found"));
            } catch (JSONException ex) {
            }
        }
        TemplateDictionary dictFullPage = new TemplateDictionary();
        TemplateDictionary dict = new TemplateDictionary();
        TemplateDictionary dictjs = new TemplateDictionary();
        dict.setVariable("CMD", cmd);
        dict.setVariable("DESCRIPTION", cmdIns.description);
        List<String> subcmdLst = cmdIns.subcmdIds;
        for (String subcmd : subcmdLst) {
            SubCmd subcmdIns = DataStorage.Instance.subCmdRepo.find(eq("id", subcmd)).firstOrDefault();
            if (subcmdIns == null) {
                continue;
            }
            TemplateDataDictionary subcmdDict = dict.addSection("SUBCMD_ZONE");
            subcmdDict.setVariable("SUBCMD", subcmdIns.subcmd);
            subcmdDict.setVariable("DESCRIPTION", subcmdIns.description);
            subcmdDict.setVariable("TOTAL", String.valueOf(subcmdIns.paramIds.size()));
            List<String> paramLst = subcmdIns.paramIds;
            for (int i = 0; i < paramLst.size(); i++) {

                Param paramIns = DataStorage.Instance.paramRepo.find(eq("id", paramLst.get(i))).firstOrDefault();
                if (paramIns == null) {
                    return;
                }
                TemplateDataDictionary paramDict = subcmdDict.addSection("PARAM_ZONE");
                paramDict.setVariable("INDEX", i + "");
                paramDict.setVariable("TYPE", type(paramIns.type));
                paramDict.setVariable("INPUT", createInputHtml(paramIns.type, paramIns.values));
            }

        }
        renderCommon(req, resp, dictFullPage, dictjs);
        String uid = getUid(req);
        renderCommonMenu(req, resp, dictFullPage, uid);
        try {
            dictFullPage.setVariable("CURRENT_PAGE_CONTENT", TemplateManager.applyTemplate(HTML_TEMPLATE, dict));
            dictFullPage.setVariable("CURRENT_PAGE_SCRIPT", TemplateManager.applyTemplate(SCRIPT, dictjs));
            TemplateManager.print(TemplateManager.applyTemplate(CONT_FULL_PAGE, dictFullPage), resp);
        } catch (Exception ex) {
        }
    }

    public void renderMainContent(HttpServletRequest req, HttpServletResponse resp) throws TemplateException {
        try {
            TemplateDictionary dictMainContent = new TemplateDictionary();
            TemplateManager.print(TemplateManager.applyTemplate(HTML_TEMPLATE, dictMainContent), resp);

        } catch (Exception ex) {
        }
    }

    private String createInputHtml(String type, List<String> values) {
        String template = "<input type=\"text\" name=\"value\" id=\"value\" class=\"form-control value\" %s value=\"%s\">";
        if (values.isEmpty()) {
            return "";
        }
        if (values.size() > 1) {
            return String.format(template, "data-role=\"tagsinput\"", values.toString().replace("[", "").replace("]", ""));
        } else {
            return String.format(template, "", values.toString().replace("[", "").replace("]", ""));
        }
    }

    private String type(String type) {
        String typeTemplate = "                                                  <option value=\"byte\">byte</option>\n"
                + "                                                  <option value=\"short\">short</option>\n"
                + "                                                  <option value=\"int\">int</option>\n"
                + "                                                  <option value=\"long\">long</option>\n"
                + "                                                  <option value=\"double\">double</option>\n"
                + "                                                  <option value=\"string8\">string8</option>\n"
                + "                                                  <option value=\"string16\">string16</option>\n"
                + "                                                  <option value=\"string32\">string32</option>\n"
                + "                                                  <option value=\"bytes8\">bytes8</option>\n"
                + "                                                  <option value=\"bytes16\">bytes16</option>\n"
                + "                                                  <option value=\"bytes32\">bytes32</option>\n"
                + "                                                  <option value=\"shorts8\">shorts8</option>\n"
                + "                                                  <option value=\"shorts16\">shorts16</option>\n"
                + "                                                  <option value=\"shorts32\">shorts32</option>\n"
                + "                                                  <option value=\"ints8\">ints8</option>\n"
                + "                                                  <option value=\"ints16\">ints16</option>\n"
                + "                                                  <option value=\"ints32\">ints32</option>\n"
                + "                                                  <option value=\"longs8\">longs8</option>\n"
                + "                                                  <option value=\"longs16\">longs16</option>\n"
                + "                                                  <option value=\"longs32\">longs32</option>\n"
                + "                                                  <option value=\"string8s8\">string8s8</option>\n"
                + "                                                  <option value=\"string8s16\">string8s16</option>\n"
                + "                                                  <option value=\"string8s32\">string8s32</option>\n"
                + "                                                  <option value=\"string16s8\">string16s8</option>\n"
                + "                                                  <option value=\"string16s16\">string16s16</option>\n"
                + "                                                  <option value=\"string16s32\">string16s32</option>\n"
                + "                                                  <option value=\"string32s8\">string32s8</option>\n"
                + "                                                  <option value=\"string32s16\">string32s16</option>\n"
                + "                                                  <option value=\"string32s32\">string32s32</option>";
        String type1 = typeTemplate.replace(">" + type + "<", "selected>" + type + "<");
        return type1;
    }
}
