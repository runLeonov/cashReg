package ua.training.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/*"})
public class MainFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = ((HttpServletRequest) request).getSession();
        String path = ((HttpServletRequest) request).getServletPath();
        if ((Objects.isNull(session) || Objects.isNull(session.getAttribute("user")))
                && !path.equals("/")
                && !path.equals("/login")
                && !path.equals("/logout")
                && !path.equals("/registration")) {
            ((HttpServletResponse) response).sendRedirect("/login");
            return;
        }
        chain.doFilter(request, response);
    }
}
