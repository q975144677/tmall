package yellow.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import yellow.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	// TODO Auto-generated method stub
	String[] white = {"/homePage","/foresearch","/forecategory","/foreloginAjax","/forecheckLogin","/foreproduct"
			,"/login_check","/loginPage","/registPage","/isExist"};
	String uri = request.getRequestURI();
	HttpSession session = request.getSession();
	String contextPath = session.getServletContext().getContextPath();
	String string  = uri.replace(contextPath, "");
	System.out.println(contextPath);
	System.out.println(string);
	System.out.println(uri);
	if(Arrays.asList(white).contains(string)) {
			return true;
		
	}
	
	else {
		User user =(User) session.getAttribute("user");
		if(user==null) {
			response.sendRedirect("loginPage");
			return false;
			
		}
	}
		return true;

}

@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}

