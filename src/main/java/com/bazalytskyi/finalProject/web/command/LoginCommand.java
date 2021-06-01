package com.bazalytskyi.finalProject.web.command;

import com.bazalytskyi.finalProject.Path;
import com.bazalytskyi.finalProject.db.AccountDAO;
import com.bazalytskyi.finalProject.entity.Account;
import com.bazalytskyi.finalProject.entity.Role;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;


public class LoginCommand extends Command {
	private static final Logger log = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		log.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from the request
		String login = request.getParameter("login");
		log.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");

		// error handler
		String errorMessage = null;
		String forward = Path.PAGE__ERROR_PAGE;

		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			errorMessage = "Login/password cannot be empty";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		}

		Account user = new AccountDAO().findAccountByLogin(login);
		log.trace("Found in DB: user --> " + user);

		if (user == null || !password.equals(user.getPassword())) {
			errorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		} else {
			Role userRole = Role.getRole(user);
			log.trace("userRole --> " + userRole);

			if (userRole == Role.ADMIN)
				forward = Path.PAGE__ADMIN_ALL_USERS;

			if (userRole == Role.CLIENT)
				forward = Path.PAGE__USER_TESTS;

			session.setAttribute("user", user);
			log.trace("Set the session attribute: user --> " + user);

			session.setAttribute("userRole", userRole);
			log.trace("Set the session attribute: userRole --> " + userRole);

			log.info("User " + user + " logged as " + userRole.toString().toLowerCase());

			// work with i18n
			String userLocaleName = user.getLocaleName();
			log.trace("userLocalName --> " + userLocaleName);

			if (userLocaleName != null && !userLocaleName.isEmpty()) {
				Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);

				session.setAttribute("defaultLocale", userLocaleName);
				log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);

				log.info("Locale for user: defaultLocale --> " + userLocaleName);
			}
		}

		log.debug("Command finished");
		return forward;
	}

}