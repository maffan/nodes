/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import entities.User;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;

/**
 *
 * Authorization filter for RESTful requests
 * 
 * All requests must contain a valid username and user related API-key
 */
@WebFilter(filterName = "RestFilter", urlPatterns = {"/webresources/*"})
public class RestFilter implements Filter {
    @Inject
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String apiKey = httpRequest.getHeader("APIKey");
        String owner = httpRequest.getHeader("Owner");
        
        if(apiKey != null && owner != null){
            User user = userService.find(owner);
            if(user.getApi().equals(apiKey)){
                chain.doFilter(request, response);
            }
            else{
                httpResponse.sendError(403);
            }
        }
        else
            httpResponse.sendError(403);
    }

    @Override
    public void destroy() {
        
    }
   
    
}
