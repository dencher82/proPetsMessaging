package propets.messaging.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(20)
public class LoginValidationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		if (path.matches("/message/en/v1/[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}+/?")) {
			Principal principal = request.getUserPrincipal();
			if (!principal.getName().equals(path.split("/")[4])) {
				response.sendError(403);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

}
