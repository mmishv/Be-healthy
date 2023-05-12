package by.fpmibsu.be_healthy.servlets.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;
import by.fpmibsu.be_healthy.services.ProfileService;
import by.fpmibsu.be_healthy.postgres.DataSource;
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
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
        DataSource.close();
    }
}