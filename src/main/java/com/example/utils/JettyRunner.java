package com.example.utils;

import com.example.servlets.TodoServlet;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyRunner {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        // FreeMarker configuration
        Configuration config = new Configuration(Configuration.VERSION_2_3_31);
        config.setClassForTemplateLoading(JettyRunner.class, "/templates");
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setLogTemplateExceptions(true);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        context.setResourceBase("src/main/resources");

        ServletHolder defaultServlet = new ServletHolder("default", new DefaultServlet());
        context.addServlet(defaultServlet, "/assets/*");

        context.addServlet(new ServletHolder(new TodoServlet(config)), "/todos/*");

        server.setHandler(context);
        server.start();
        server.join();
    }
}
