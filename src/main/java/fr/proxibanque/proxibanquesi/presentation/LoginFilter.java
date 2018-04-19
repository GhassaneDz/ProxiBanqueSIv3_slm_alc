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
 * Ce filtre intercepte toutes les requ�tes HTTP dirig�es vers l'application
 * ProxiBanqueSI. Si l'utilisateur n'est pas identifi�, aucune page ne peut �tre
 * affich�e, et aucun service ne peut �tre exploit�. Seul la pr�sence d'un
 * utilisateur identifi� en session (pour 60 minutes - cf. Servlet de login)
 * permet d'acc�der � la page d'accueil et aux webservices.
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
		// Ne pas filtrer TOUTES les requ�tes...
		String[] authorizedPages = { request.getContextPath() + "/Login", request.getContextPath() + "/index.html",
				request.getContextPath() + "/css/style.css", request.getContextPath() + "/img/banner.jpg" };

		boolean loggedIn = session != null && session.getAttribute("userSession") != null;
		boolean authorizedRequest = Arrays.asList(authorizedPages).contains(request.getRequestURI());

		if (loggedIn || authorizedRequest) {
			chain.doFilter(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous n'�tes pas identifi�.");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
