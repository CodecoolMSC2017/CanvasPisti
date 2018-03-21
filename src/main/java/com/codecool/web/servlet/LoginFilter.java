package com.codecool.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Filter;
@WebFilter("/*")
public class LoginFilter implements javax.servlet.Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String path = ((HttpServletRequest) req).getRequestURI();
        String url = path.substring(path.lastIndexOf("/") + 1, path.length());
        if (url.endsWith(".css")) {
            filterChain.doFilter(req, res);
            return;
        }
        if (url.equals("login.jsp") || url.equals("registry.jsp") || url.equals("index.html") || url.equals("") || url.equals("greeting.jsp")||url.equals("unlogin.jsp")||url.equals("loginServlet")||url.equals("register")||url.equals("greeting")||url.equals("logerror.jsp")) {
            filterChain.doFilter(request, response);
        } else {
            if (session.getAttribute("logged") != null) {
                filterChain.doFilter(req, res);
            }else{
                req.getRequestDispatcher("registry.html").forward(req,res);
            }

        }
    }

    @Override
    public void destroy() {

    }
}