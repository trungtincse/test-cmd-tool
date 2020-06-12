/*
* Copyright (c) 2012-2016 by Zalo Group.
* All Rights Reserved.
 */
package com.vng.zing.testfeature.servers;

import com.vng.zing.testfeature.handlers.CreateHandler;
import com.vng.zing.jettyserver.WebServers;
import com.vng.zing.testfeature.handlers.LogoutHandler;
import com.vng.zing.testfeature.handlers.AuthHandler;
import com.vng.zing.testfeature.handlers.CmdHandler;
import com.vng.zing.testfeature.handlers.DeleteHandler;
import com.vng.zing.testfeature.handlers.DetailHandler;
import com.vng.zing.testfeature.handlers.HomeAjaxHandler;
import com.vng.zing.testfeature.handlers.HomeHandler;
import com.vng.zing.testfeature.handlers.LoginHandler;
import com.vng.zing.testfeature.handlers.RequestHandler;
import com.vng.zing.testfeature.handlers.UIDHandler;
import org.eclipse.jetty.rewrite.handler.RewriteHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 *
 * @author namnq
 */
public class HServers {

    public boolean setupAndStart() {

        WebServers servers = new WebServers("main");
        ServletContextHandler svlHandler = new ServletContextHandler();
        svlHandler.setSessionHandler(new SessionHandler());

        svlHandler.addServlet(HomeHandler.class, "/");
        svlHandler.addServlet(CmdHandler.class, "/execute");
        svlHandler.addServlet(HomeAjaxHandler.class, "/homeajax");
        svlHandler.addServlet(DeleteHandler.class, "/delete");
        svlHandler.addServlet(DetailHandler.class, "/detail");
        svlHandler.addServlet(RequestHandler.class, "/testft");
        svlHandler.addServlet(CreateHandler.class, "/create");
        svlHandler.addServlet(LoginHandler.class, "/login");
        svlHandler.addServlet(AuthHandler.class, "/auth");
        svlHandler.addServlet(LogoutHandler.class, "/logout");
        svlHandler.addServlet(UIDHandler.class, "/whitelist");
        svlHandler.addServlet(HomeHandler.class, "/home");

//        //resource
        ContextHandler ctxHandler = new ContextHandler("/stc");
        ctxHandler.setResourceBase("./stc");
        ctxHandler.setHandler(new ResourceHandler());

//        //set redirect
        RewriteHandler rewrite = new RewriteHandler();
        rewrite.setRewriteRequestURI(true);
        rewrite.setRewritePathInfo(false);
        rewrite.setHandler(svlHandler);
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{ctxHandler, rewrite});
        servers.setup(handlers);
        return servers.start();
    }
}
