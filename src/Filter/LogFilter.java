package Filter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class Filter
 */
public class LogFilter implements javax.servlet.Filter {

	final String defaultEncoding = "UTF-8";
	PrintWriter printWrite;

	/**
	 * @see LogFilter#destroy()
	 */
	public void destroy() {
		printLog("[필터 종료 시간]: "+new Date(System.currentTimeMillis()).toString());
		try {
			printWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see LogFilter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(defaultEncoding);
		response.setCharacterEncoding(defaultEncoding);
		printLog("[필터 사전작업 시간]: "+new Date(System.currentTimeMillis()).toString());
		chain.doFilter(request, response);
		printLog("[필터 사후작업 시간]: "+new Date(System.currentTimeMillis()).toString());
	}

	/**
	 * @see LogFilter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			printWrite=new PrintWriter(new FileWriter(fConfig.getInitParameter("FILENAME"),true),true);
			printLog("[필터 초기화 시간]: "+new Date(System.currentTimeMillis()).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void printLog(String log) {
		printWrite.println(log);
		System.out.println(log);
	}
}
