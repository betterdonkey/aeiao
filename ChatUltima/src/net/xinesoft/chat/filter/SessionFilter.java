package net.xinesoft.chat.filter;
 
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {
 
    private ServletContext context;
    
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        
        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        if(uri.indexOf("/m/") > 0 || uri.indexOf("/resources/") > 0 ){        	
        	chain.doFilter(request, response);
        	
        }else if(session == null || (session.getAttribute("user") == null)){
            req.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
            chain.doFilter(request, response);
        }             
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	} 
     
 
 
}