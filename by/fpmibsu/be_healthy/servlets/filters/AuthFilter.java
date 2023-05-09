package by.fpmibsu.be_healthy.servlets.filters;

import by.fpmibsu.be_healthy.services.ProfileService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final int id;
        if (req.getParameter("id")!=null){
            id = Integer.parseInt(req.getParameter("id"));
        }

        final AtomicReference<ProfileService> profile =
                (AtomicReference<ProfileService>) req.getSession().getServletContext().getAttribute("profile");
        final HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            filterChain.doFilter(request, response);
        } else {
            res.sendRedirect("/authentication");
        }
    }
    @Override
    public void destroy() {
    }

}