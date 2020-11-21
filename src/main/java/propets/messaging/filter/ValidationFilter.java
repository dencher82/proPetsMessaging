package propets.messaging.filter;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import static propets.messaging.configuration.Constants.*;

@Component
@Order(10)
public class ValidationFilter implements Filter {
	
	@Value("${validation.url}")
	private String validationUrl;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		String xToken = request.getHeader(TOKEN_HEADER);
		if (!"/message/en/v1/userdata".equals(path)) {
			if (xToken != null) {
				HttpHeaders headers = new HttpHeaders();
				headers.add(TOKEN_HEADER, xToken);
				try {
					RequestEntity<String> requestEntity = new RequestEntity<String>(headers, HttpMethod.GET,
							new URI(validationUrl));
					ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
					request = new WrapperRequest(request, responseEntity.getHeaders().getFirst(LOGIN_HEADER));
					response.addHeader(TOKEN_HEADER, responseEntity.getHeaders().getFirst(TOKEN_HEADER));
				} catch (BadRequest e) {
					e.printStackTrace();
					response.sendError(403);
					return;
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(401);
					return;
				}
			} else {
				response.sendError(401);
				return;
			} 
		}
		
		chain.doFilter(request, response);
	}
	
	private class WrapperRequest extends HttpServletRequestWrapper {
		String user;

		public WrapperRequest(HttpServletRequest request, String user) {
			super(request);
			this.user = user;
		}

		@Override
		public Principal getUserPrincipal() {
			return new Principal() {

				@Override
				public String getName() {
					return user;
				}
			};
		}
	}

}
