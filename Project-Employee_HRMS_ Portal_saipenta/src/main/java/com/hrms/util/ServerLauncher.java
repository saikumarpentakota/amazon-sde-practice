package com.hrms.util;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;

public class ServerLauncher {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        
        WebAppContext context = new WebAppContext();
        context.setContextPath("/hrms-portal");
        context.setResourceBase("src/main/webapp");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setParentLoaderPriority(true);
        
        // Configure JSP support
        context.setConfigurations(new Configuration[] {
            new AnnotationConfiguration(),
            new WebInfConfiguration(),
            new WebXmlConfiguration(),
            new MetaInfConfiguration(),
            new FragmentConfiguration(),
            new EnvConfiguration(),
            new PlusConfiguration(),
            new JettyWebXmlConfiguration()
        });
        
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", 
            ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$|.*/apache-jsp-[^/]*\\.jar$");
        
        // JSP configuration
        context.setAttribute("org.eclipse.jetty.jsp.precompiled", Boolean.FALSE);
        context.setInitParameter("org.eclipse.jetty.jsp.precompiled", "false");
        context.setInitParameter("compilerSourceVM", "11");
        context.setInitParameter("compilerTargetVM", "11");
        
        // Add work directory for JSP compilation
        context.setAttribute("javax.servlet.context.tempdir", new java.io.File("target/jsp-work"));
        context.setTempDirectory(new java.io.File("target/jsp-work"));
        
        server.setHandler(context);
        server.start();
        
        System.out.println("Server started at: http://localhost:8081/hrms-portal/");
        server.join();
    }
}