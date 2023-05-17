package by.fpmibsu.be_healthy.servlets.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {
    }
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        logger.debug("User authorization check");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = (String) req.getSession().getAttribute("login");
        final HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(login)) {
            logger.debug("User is authorized");
            filterChain.doFilter(request, response);
        } else {
            logger.debug("User is not authorized, redirection to register/log in page");
            res.sendRedirect("/authentication");
        }
    }
    @Override
    public void destroy() {
    }

}