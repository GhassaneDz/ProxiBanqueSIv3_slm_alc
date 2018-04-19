package fr.proxibanque.proxibanquesi.presentation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Cette Servlet permet de valider l'authentification d'un conseiller
 * ProxiBanqueSI. Une authentification valide permet de créer une session qui
 * permettra une exploitation des services ProxiBanqueSI durant 60 minutes avant
 * reconnexion.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

@WebServlet("/Login")
public class Login extends HttpServlet {

	// TODO Identifiants en "dur"
	private static final String LOGIN = "mdupont";
	private static final String PASSWORD = "1234";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginInput = request.getParameter("login");
		String passwordInput = request.getParameter("password");
		PrintWriter out = response.getWriter();

		if (loginInput.equals(LOGIN) && passwordInput.equals(PASSWORD)) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60 * 60); // 60 minutes
			// TODO Ceci sera un objet Conseiller dans une prochaine version
			session.setAttribute("userSession", "Michel Dupont");
			response.sendRedirect(request.getContextPath() + "/accueil.jsp");
		} else {
			// TODO Page d'erreur à retourner
			out.println("Authentication failed.");
		}
	}

}
