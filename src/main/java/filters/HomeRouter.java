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

/**
 *
 * @author Marcus
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
        HttpServletRequest r = (HttpServletRequest) req;

        String uri = r.getRequestURI();
        String path = r.getServletPath();
        if (resources.matcher(uri).matches()) {
            chain.doFilter(req, res);
        } 
        else if (path.equals("/") || path.equals("/home")) {
            chain.doFilter(req, res);
        } 
        else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}
