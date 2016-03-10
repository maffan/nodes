/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Checks that a valid session is in place. If not, the user gets redirected to
 * a login page.
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/home"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String intendedDestination = httpRequest.getRequestURI();
        if(session.getAttribute("user") == null && !intendedDestination.endsWith("login")){ //User is not logged in
            session.setAttribute("destination", intendedDestination);
            httpResponse.sendRedirect("login");
        }
        else{ //User is logged in
            if(intendedDestination.endsWith("login"))
                httpResponse.sendRedirect("home");
            else
                chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
