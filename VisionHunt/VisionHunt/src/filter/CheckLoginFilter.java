package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginFilter implements Filter{
	

	private List<String> unCheckedURL=null;
	private String loginPage="/JSP/Login.jsp";
	private String userInSession="USER_IN_SESSION";

	public void init(FilterConfig filterConfig) throws ServletException {
		String url=filterConfig.getInitParameter("unCheckedURL");
		if(url!=null && !"".equals(url.trim())){
			String [] arr = url.split(",");
			this.unCheckedURL=Arrays.asList(arr);
		}
		
		String initPage=filterConfig.getInitParameter("loginPage");
		if(initPage!=null && "".equals(initPage)){
			this.loginPage=initPage;
		}
		
		String sessionAttrName=filterConfig.getInitParameter("userInSession");
		if(sessionAttrName!=null && !"".equals(sessionAttrName)){
			this.userInSession=sessionAttrName;
		}
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		//==============================================================
		String requestURI = req.getRequestURI();//当前请求的资源名
		
		if(!unCheckedURL.contains(requestURI)){
			Object user = req.getSession().getAttribute(userInSession);
			if(user==null){
				resp.sendRedirect(req.getContextPath()+loginPage);
				return;
			}	
			
		}
		
		chain.doFilter(req, resp);
		
	}

	public void destroy() {
		
	}

}
