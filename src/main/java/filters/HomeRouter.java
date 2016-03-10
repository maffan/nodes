/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Filter allows "/" to be redirected to "/home"
 */
@WebFilter(filterName = "Router", urlPatterns = {"/*"})
public class HomeRouter implements Filter {
// Leading "/" very important (from context root)
    public static final String TEMPLATE = "/WEB-INF/templates/template.jsp";
    private static final String RESOURCES
            = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|css))$)";
    private Pattern resources;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        resources = Pattern.compile(RESOURCES);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;

        String uri = httpRequest.getRequestURI();
        String path = httpRequest.getServletPath();
        if (resources.matcher(uri).matches()) {
            chain.doFilter(req, res);
        } 
        else if (path.equals("/")) {
            httpResponse.sendRedirect("home");
        } 
        else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}
