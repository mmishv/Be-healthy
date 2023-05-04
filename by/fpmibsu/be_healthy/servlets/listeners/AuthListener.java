package by.fpmibsu.be_healthy.servlets.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;
import by.fpmibsu.be_healthy.services.ProfileService;

@WebListener
public class AuthListener implements ServletContextListener {
    private AtomicReference<ProfileService> profile;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        profile = new AtomicReference<>(new ProfileService());
        final ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("profile", profile);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        profile = null;
    }
}