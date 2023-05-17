package by.fpmibsu.be_healthy.servlets.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;
import by.fpmibsu.be_healthy.services.ProfileService;
import by.fpmibsu.be_healthy.postgres.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private AtomicReference<ProfileService> profile;
    private static final Logger logger = LogManager.getLogger(ServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        profile = new AtomicReference<>(new ProfileService());
        final ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("profile", profile);
        logger.debug("Context listener initialized");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        profile = null;
        logger.debug("Context listener destroyed");
        DataSource.close();
    }
}