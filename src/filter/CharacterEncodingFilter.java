package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements javax.servlet.Filter{
	
	private String encoding=null;
	private boolean forceEncoding=false;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding=filterConfig.getInitParameter("encoding");
		this.forceEncoding=Boolean.valueOf(filterConfig.getInitParameter("force"));
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		//请求中未设置编码，并且已配置编码，则使用配置的编码
		//请求中已设置编码，但不是想用的编码，强制使用配置的编码
		//此代码只针对post请求
		if((req.getCharacterEncoding()==null || forceEncoding) && hasLangth(encoding)){
			req.setCharacterEncoding(encoding);
			resp.setCharacterEncoding(encoding);
		}
	
		chain.doFilter(req, resp);
	}
	
	private boolean hasLangth(String str){
		return str!=null && !"".equals(str.trim());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
