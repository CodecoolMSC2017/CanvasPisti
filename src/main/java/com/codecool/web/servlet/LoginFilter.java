package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Filter;
@WebFilter("/*")
public class LoginFilter implements javax.servlet.Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        Singletondb db = Singletondb.getInstance();
        Map<String,User> userMap = db.getUserMap();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String path = ((HttpServletRequest) req).getRequestURI();
        String url = path.substring(path.lastIndexOf("/") + 1, path.length());
        if (url.endsWith(".css")||url.endsWith(".jpeg")) {
            filterChain.doFilter(req, res);
            return;
        }
        if (url.equals("login.jsp") || url.equals("registry.jsp") || url.equals("index.jsp") || url.equals("") || url.equals("loginServlet")||url.equals("register")){            if(userMap.get(session.getId())!=null){
            req.getRequestDispatcher("main.jsp").forward(req,res);
        }else{
            filterChain.doFilter(request, response);

        }
        } else {
            if (session.getAttribute("logged") != null) {
                filterChain.doFilter(req,res);
            }else{
                req.getRequestDispatcher("registry.jsp").forward(req,res);
            }

        }
    }

    @Override
    public void destroy() {

    }
}

