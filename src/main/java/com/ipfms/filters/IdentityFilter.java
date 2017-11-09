package com.ipfms.filters;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class IdentityFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
        //Load from environment
        String env = System.getenv().get("ENV");
        if (env == null){
            env = "dev";
        }

        if (env.equals("prod")){
            String uid = ((HttpServletRequest)request).getRemoteUser();
            request.setAttribute("currentUserId", uid);
            chain.doFilter(request, response);
        }else{
            String uid = request.getParameter("uid");
            if (uid == null){
                uid = "reichertb";
            }
            request.setAttribute("currentUserId", uid);
            chain.doFilter(request, response);
        }

    }
}