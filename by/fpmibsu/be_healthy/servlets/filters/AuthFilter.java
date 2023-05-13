package by.fpmibsu.be_healthy.servlets.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = (String) req.getSession().getAttribute("login");
        final HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(login)) {
            filterChain.doFilter(request, response);
        } else {
            res.sendRedirect("/authentication");
        }
    }
    @Override
    public void destroy() {
    }

}