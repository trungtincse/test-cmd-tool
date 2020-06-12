/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vng.zing.testfeature.common;

import com.vng.zing.logger.ZLogger;
import hapax.Template;
import hapax.TemplateCache;
import hapax.TemplateDataDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author SmartSO Team
 */
public class TemplateManager {

    private static final Logger _Logger = ZLogger.getLogger(TemplateManager.class);

    public static String applyTemplate(String tplName, TemplateDataDictionary dic) throws TemplateException {
        Template template;
        if (!System.getProperty("zappprof").equals("production")) {
            TemplateCache templateCache = new TemplateCache("stc");
            template = templateCache.getTemplate(tplName);
        } else {
            TemplateLoader tplLoader = TemplateResourceLoader.create("");
            template = tplLoader.getTemplate(tplName);
        }
        if (template != null) {
            return template.renderToString(dic);
        }
        return "";
    }

    public static void print(Object obj, HttpServletResponse resp) {
        PrintWriter out = null;
        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Connection", "Close");
            resp.setStatus(HttpServletResponse.SC_OK);
            out = resp.getWriter();
            out.print(obj);
        } catch (IOException ex) {
            _Logger.error(ex.getMessage(), ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
