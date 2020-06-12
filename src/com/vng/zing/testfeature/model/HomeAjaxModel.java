package com.vng.zing.testfeature.model;

import com.vng.zing.common.HReqParam;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.common.TemplateManager;
import com.vng.zing.testfeature.data.database.DataStorage;
import com.vng.zing.testfeature.data.model.Command;
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
import org.dizitart.no2.objects.Cursor;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tindpt
 */
public class HomeAjaxModel extends BaseModel {

    private static final Logger _Logger = ZLogger.getLogger(HomeAjaxModel.class);
    public static final HomeAjaxModel Instance = new HomeAjaxModel();

    @Override
    public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        if (!checkToken(req, resp)) {
            return;
        }
        try {
            getAllCmd(req, resp);
        } catch (IOException | JSONException ex) {
        }
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        doProcess(req, resp);
    }

    private void getAllCmd(HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException {
        JSONObject result = new JSONObject();
        JSONArray dataLst = new JSONArray();
        Cursor<Command> cmdCursor = DataStorage.Instance.cmdRepo.find();
        for (Command cmd : cmdCursor) {
            List<String> lst = new ArrayList<>();
            lst.add(cmd.number);
            lst.add(cmd.description);
            List<String> subcmdLst = new ArrayList<>();
            for (String subcmdId : cmd.subcmdIds) {
                SubCmd subcmdIns = DataStorage.Instance.subCmdRepo.find(eq("id",subcmdId)).firstOrDefault();
                if(subcmdIns==null) continue;
                subcmdLst.add(subcmdIns.subcmd);
            }
            lst.add(subcmdLst.toString().replace("[", "").replace("]", ""));
            dataLst.put(lst);
            result.put("data", dataLst);
        }
        sendJson(resp, result);
    }

    @Override
    public void renderPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, TemplateException {
    }

    @Override
    public void renderMainContent(HttpServletRequest req, HttpServletResponse resp) throws TemplateException {
    }

}
