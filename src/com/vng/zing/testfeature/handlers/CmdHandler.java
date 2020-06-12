/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.vng.zing.testfeature.handlers;

import com.vng.zing.logger.ZLogger;
import com.vng.zing.stats.Profiler;
import com.vng.zing.stats.ThreadProfiler;
import com.vng.zing.testfeature.model.ExecuteCmdModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author tindpt
 */
public class CmdHandler extends HttpServlet{
    private static final Logger _Logger = ZLogger.getLogger(CmdHandler.class);
    private void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        ThreadProfiler profiler = Profiler.createThreadProfilerInHttpProc("CmdHandler", req);
        try {
            ExecuteCmdModel.Instance.process(req, resp);
        } catch (Exception ex) {
            _Logger.error(null, ex);
        } finally {
            Profiler.closeThreadProfiler();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }
    
}
