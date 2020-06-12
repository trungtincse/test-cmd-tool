/*
* Copyright (c) 2012-2016 by Zalo Group.
* All Rights Reserved.
 */
package com.vng.zing.testfeature.model;

import com.vng.zing.configer.ZConfig;
import com.vng.zing.jettyserver.WebServers;
import com.vng.zing.logger.ZLogger;
import com.vng.zing.testfeature.common.TemplateManager;
import com.vng.zing.stats.Profiler;
import com.vng.zing.stats.ThreadProfiler;
import com.vng.zing.testfeature.auth.TokenManager;
import com.vng.zing.testfeature.auth.UserManager;
import com.vng.zing.testfeature.data.database.DataStorage;
import com.vng.zing.testfeature.data.model.Command;
import com.vng.zing.testfeature.data.model.Feature;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.Cookie;
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
 * @author thongml
 */
public abstract class BaseModel {

    private static final Logger LOGGER = ZLogger.getLogger(BaseModel.class);
    protected static final String LayoutMenu = "view/sidebar";
    protected static final String CONT_FULL_PAGE = "view/index";
    protected static final String MENU_TEMPLATE = "view/cmds/menu_component";
    protected static final String ITEM_MENU_TEMPLATE = "view/cmds/item_menu_component";
    protected static final String CREATE_BTN_TEMPLATE = "view/commonsnippets/create_button";
    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    protected static final String PREFIX_PATH = "/stc";
    protected static final String BE_HOST = "0.0.0.0:9009";
    public static final boolean IS_AUTHEN = ZConfig.Instance.getBoolean(WebServers.class, "main", "is_authen", false);

    public abstract void doProcess(HttpServletRequest req, HttpServletResponse resp);

    public abstract void renderPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, TemplateException;

    public abstract void renderMainContent(HttpServletRequest req, HttpServletResponse resp) throws TemplateException;

    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doProcess(req, resp);
    }

    protected void renderCommon(HttpServletRequest req, HttpServletResponse resp, TemplateDictionary dic, TemplateDictionary dicjs) {
        dic.setVariable("PREFIX_PATH", PREFIX_PATH);
        dicjs.setVariable("BASEURL", BE_HOST);
        ThreadProfiler profiler = Profiler.getThreadProfiler();
        try {
            profiler.push(BaseModel.class, "renderCommon");
            //session info
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            profiler.pop(BaseModel.class, "renderCommon");
        }
    }

    protected void renderCommonMenu(HttpServletRequest req, HttpServletResponse resp, TemplateDictionary dic, String uid) {
        ThreadProfiler profiler = Profiler.getThreadProfiler();
        try {
            profiler.push(BaseModel.class, "renderCommonMenu");
            TemplateDictionary dict = new TemplateDictionary();
            List<String> HtmlLst = new ArrayList<>();
            List<Feature> accessableTable = UserManager.userManager.getAccessableTable(uid);
            for (Feature feature : accessableTable) {
                List<String> cmdHtmlLst = new ArrayList<>();
                for (String cmdId : feature.commands) {
                    Command command = DataStorage.Instance.cmdRepo.find(eq("number",cmdId)).firstOrDefault();
                    if (command==null) {
                        continue;
                    }
                    String itemmenu = createItemMenu("", command.number, "#", String.format("requestExec(\'%s\',%s)", feature.slug_name, command.number));
                    cmdHtmlLst.add(itemmenu);
                }
                String menu = createMenu(null, feature.name, cmdHtmlLst, false);
                HtmlLst.add(menu);
            }

            for (String html : HtmlLst) {
                TemplateDataDictionary menu = dict.addSection("NEW_MENU");
                menu.setVariable("MENU", html);
            }
            if (UserManager.userManager.checkAdmin(uid)) {
                dict.showSection("ADMIN_OPERATION_SECTION");
                dict.showSection("ROLE_SECTION");
            } else if (UserManager.userManager.checkTester(uid)) {
                dict.showSection("TESTER_OPERATION_SECTION");
            }
            dic.setVariable("NAV_CONTENT", TemplateManager.applyTemplate(LayoutMenu, dict));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            profiler.pop(BaseModel.class, "renderCommonMenu");
        }
    }

    public String createMenu(String createButton, String menuName, List<String> menuHtmls, boolean isActive) throws TemplateException {
        TemplateDictionary dict = new TemplateDictionary();
        dict.setVariable("MENUCLASS", "");
        if (isActive) {
            dict.showSection("ACTIVE");
        }
        dict.setVariable("CREATE_MENU_BUTTON", createButton);
        for (String menuHtml : menuHtmls) {
            TemplateDataDictionary menu = dict.addSection("NEW_MENU");
            menu.setVariable("MENU_OR_ITEM", menuHtml);

        }
        dict.setVariable("MENU_NAME", menuName);
        return TemplateManager.applyTemplate(MENU_TEMPLATE, dict);
    }

    public String createItemMenu(String className, String itemName, String url, String function) throws TemplateException {
        TemplateDictionary dict = new TemplateDictionary();
        dict.setVariable("CLASSNAME", className);
        dict.setVariable("ITEM_NAME", itemName);
        dict.setVariable("FUNCTION", function);
        dict.setVariable("URL", url);
        return TemplateManager.applyTemplate(ITEM_MENU_TEMPLATE, dict);
    }

    public boolean checkToken(HttpServletRequest req, HttpServletResponse resp) {
//        return true;
        if (!IS_AUTHEN) {
            return true;
        }
        boolean result = false;
        try {
            if (req.getCookies() == null) {
                resp.sendRedirect("/login");
                return result;
            }
            String accessToken = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals("accessToken"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
            if (!TokenManager.tokenManager.verifyToken(accessToken)) {
                resp.sendRedirect("/login");
            } else {
                result = true;
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(BaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String getUid(HttpServletRequest req) {
        if (IS_AUTHEN) {
            String uid = null;
            String accessToken = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals("accessToken"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
            uid = TokenManager.tokenManager.getUid(accessToken);
            return uid;
        }
        return "185230354";
    }

    public static void sendJson(HttpServletResponse resp,JSONObject result) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(result.toString());
        out.flush();
    }
        public static void sendJson(HttpServletResponse resp,JSONArray result) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(result.toString());
        out.flush();
    }
    public String mapFeature() throws Exception {
//        List<String> listfea = new ArrayList<>();
        JSONObject obj = new JSONObject();
        DataStorage.Instance.feaRepo.find().forEach((fea) -> {
            try {
                obj.put(fea.slug_name, fea.commands.toArray());
            } catch (JSONException ex) {
            }
        });

        return obj.toString();
    }
    public String listFeature() throws Exception {
        List<String> listfea = new ArrayList<>();
        DataStorage.Instance.feaRepo.find().forEach((fea) -> {
            listfea.add(String.format("\"%s\"", fea.slug_name));
        });

        return listfea.toString();
    }
}
