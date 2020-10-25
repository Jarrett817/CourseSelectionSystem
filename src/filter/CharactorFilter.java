package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharactorFilter", urlPatterns = "/filter/CharactorFilter")
public class CharactorFilter implements Filter {
    String encoding = null;

    public void destroy() {
        encoding = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if(encoding!=null){
            req.setCharacterEncoding(encoding);
            resp.setContentType("text/html;charset="+encoding);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding=config.getInitParameter("encoding");
    }

}
