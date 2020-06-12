/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.vng.zing.testfeature.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vng.zing.common.HReqParam;
import com.vng.zing.exception.InvalidParamException;
import com.vng.zing.exception.NotExistException;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.common.TemplateManager;
import com.vng.zing.testfeature.data.database.DataStorage;
import com.vng.zing.testfeature.data.model.Command;
import com.vng.zing.testfeature.data.model.Feature;
import com.vng.zing.testfeature.data.model.Param;
import com.vng.zing.testfeature.data.model.SubCmd;
import static com.vng.zing.testfeature.model.BaseModel.CONT_FULL_PAGE;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.Cursor;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class CreateModel extends BaseModel {

    private static final Logger _Logger = ZLogger.getLogger(CreateModel.class);
    public static final CreateModel Instance = new CreateModel();
    public String CREATE_CMD_FORM_TEMPLATE = "/view/cmds/create_cmd";
    public String CREATE_FEA_FORM_TEMPLATE = "/view/cmds/create_feature";
    public String SCRIPT = "/view/cmds/createjs";

    @Override
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkToken(req, resp)) {
            return;
        }
        if ("POST".equals(req.getMethod())) {
            doPost(req, resp,true);
        } else {
            renderPage(req, resp);
        }
    }

    public void doProcess(HttpServletRequest req, HttpServletResponse resp, boolean isUpdate) {
        if ("POST".equals(req.getMethod())) {
            doPost(req, resp, isUpdate);
        }
    }

    @Override
    public void renderPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            TemplateDictionary dictFullPage = new TemplateDictionary();
            TemplateDictionary dict = new TemplateDictionary();
            TemplateDictionary dictjs = new TemplateDictionary();

            renderCommon(req, resp, dictFullPage, dictjs);
            String uid = getUid(req);
            renderCommonMenu(req, resp, dictFullPage, uid);
            dictjs.setVariable("FEAS", listFeature());
            dictFullPage.setVariable("CURRENT_PAGE_CONTENT", TemplateManager.applyTemplate(CREATE_CMD_FORM_TEMPLATE, dict));
            dictFullPage.setVariable("CURRENT_PAGE_SCRIPT", TemplateManager.applyTemplate(SCRIPT, dictjs));
            TemplateManager.print(TemplateManager.applyTemplate(CONT_FULL_PAGE, dictFullPage), resp);
        } catch (TemplateException ex) {
            _Logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void renderMainContent(HttpServletRequest req, HttpServletResponse resp) throws TemplateException {
        try {
            String type = HReqParam.getString(req, "type");
            TemplateDictionary dictMainContent = new TemplateDictionary();
            if (type.equals("feature")) {
                TemplateManager.print(TemplateManager.applyTemplate(CREATE_FEA_FORM_TEMPLATE, dictMainContent), resp);

            } else {
                String feaId = HReqParam.getString(req, "feaId", "");
                dictMainContent.setVariable("FEAID", feaId);
                TemplateManager.print(TemplateManager.applyTemplate(CREATE_CMD_FORM_TEMPLATE, dictMainContent), resp);
            }
        } catch (NotExistException | InvalidParamException ex) {
            _Logger.error(ex.getMessage(), ex);
        }

    }

    public String listFeature() {
        List<String> listfea = new ArrayList<>();
        Cursor<Feature> feaCursor = DataStorage.Instance.feaRepo.find();
        for (Feature feature : feaCursor) {
            listfea.add(String.format("\"%s\"", feature.slug_name));
        }
        return listfea.toString();
    }

    private void doPost(HttpServletRequest req, HttpServletResponse resp, boolean isUpdate) {
        try {
            JSONObject result = new JSONObject().put("success", true);
            String jsonStr = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String action = HReqParam.getString(req, "action", "feature");
            JSONObject json = new JSONObject(jsonStr);
            createCmd(json, result, resp, isUpdate);
        } catch (IOException | JSONException ex) {
            java.util.logging.Logger.getLogger(CreateModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createFea(JSONObject json, JSONObject result, HttpServletResponse resp) throws JSONException, IOException {
        Feature feaIns = new Feature(json.getString("featureName"), json.getString("description"));
        if (feaIns.slug_name != null) {
            try {
                DataStorage.Instance.feaRepo.insert(feaIns);
                sendJson(resp, result);
            } catch (UniqueConstraintException ex) {
                result.put("success", false).put("error", "Duplicated");
                sendJson(resp, result);
            }
        }
    }

    private void createCmd(JSONObject json, JSONObject result, HttpServletResponse resp, boolean isUpdate) throws JSONException, IOException {
        String cmd = json.getString("cmd");
        if (checkCMDExists(cmd, isUpdate)) {
            result.put("success", false).put("error", "Command duplicated");
            return;
        }
        String cmd_description = json.getString("description");
        Command command_ins = new Command(cmd, cmd_description);
        JSONArray JSONsubcmds = json.getJSONArray("subcmds");
        for (int i = 0; i < JSONsubcmds.length(); i++) {
            JSONObject JSONsubcmd = JSONsubcmds.getJSONObject(i);
            String subcmd = JSONsubcmd.getString("subcmd");
            String subcmd_description = JSONsubcmd.getString("description");
            SubCmd subcmd_ins = new SubCmd(subcmd, subcmd_description);
            JSONArray JSONparams = JSONsubcmd.getJSONArray("params");
            for (int j = 0; j < JSONparams.length(); j++) {
                JSONObject JSONparam = JSONparams.getJSONObject(j);
                String p_type = JSONparam.getString("type");
                Param param_ins = new Param("", p_type);
                try {
                    JSONArray JSONvalues = JSONparam.getJSONArray("value");
                    for (int t = 0; t < JSONvalues.length(); t++) {
                        String value = JSONvalues.getString(t);
                        param_ins.values.add(value);
                    }
                } catch (Exception ex) {
                    String value = JSONparam.getString("value");
                    param_ins.values.add(value);
                } finally {
                    subcmd_ins.paramIds.add(param_ins.id);
                    DataStorage.Instance.paramRepo.insert(param_ins);
                }
            }
            command_ins.subcmdIds.add(subcmd_ins.id);
            DataStorage.Instance.subCmdRepo.insert(subcmd_ins);
        }
        DataStorage.Instance.cmdRepo.insert(command_ins);

        sendJson(resp, result);
    }

    public static void main(String[] args) throws JSONException {
        DataStorage.Instance.cmdRepo.find().forEach((cmd) -> {
            System.out.println(cmd.number);
        });
    }

    private boolean checkCMDExists(String cmd, boolean isUpdate) {
        Command cmd_ins = DataStorage.Instance.cmdRepo.find(eq("number", cmd)).firstOrDefault();
        if (isUpdate) {
            DataStorage.Instance.deleteCmd(cmd);
            return false;
        } else {
            return cmd_ins != null;
        }

    }

}
