package fr.proxibanque.proxibanquesi.presentation;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Ce filtre intercepte toutes les requêtes HTTP dirigées vers l'application
 * ProxiBanqueSI. Si l'utilisateur n'est pas identifié, aucune page ne peut être
 * affichée, et aucun service ne peut être exploité. Seul la présence d'un
 * utilisateur identifié en session (pour 60 minutes - cf. Servlet de login)
 * permet d'accéder à la page d'accueil et aux webservices.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

@WebFilter("/*")
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		// Ne pas filtrer TOUTES les requêtes...
		String[] authorizedPages = { request.getContextPath() + "/Login", request.getContextPath() + "/index.html",
				request.getContextPath() + "/css/style.css", request.getContextPath() + "/img/banner.jpg" };

		boolean loggedIn = session != null && session.getAttribute("userSession") != null;
		boolean authorizedRequest = Arrays.asList(authorizedPages).contains(request.getRequestURI());

		if (loggedIn || authorizedRequest) {
			chain.doFilter(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous n'êtes pas identifié.");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
