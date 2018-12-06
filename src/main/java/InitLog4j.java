import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class InitLog4j extends HttpServlet {

	   public void init() throws ServletException {
	       String prefix = getServletContext().getRealPath("/");
	       prefix = prefix.replace("//", "/");
	       String file = getInitParameter("log4j-init-file");
	       // if the log4j-init-file is not set, then no point in trying
	       if (file != null) {
	           PropertyConfigurator.configure(prefix + file);
	       }
	}
}

