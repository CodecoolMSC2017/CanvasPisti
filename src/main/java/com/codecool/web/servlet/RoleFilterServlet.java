package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebFilter("/*")
public class RoleFilterServlet implements Filter {

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
        User tmpUser = (User) session.getAttribute("logged");
        String[] ignored = new String[] {"text.jsp", "assignment.jsp", "solutionGrade", "attendance"};
        if(session.getAttribute("logged") != null && tmpUser.getRole().equals("Student")) {
            if (Arrays.asList(ignored).contains(url) || url.contains("scoring")) {
                req.getRequestDispatcher("main.jsp").forward(req, res);
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
