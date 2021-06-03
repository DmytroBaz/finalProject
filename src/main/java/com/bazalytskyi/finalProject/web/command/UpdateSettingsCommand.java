package com.bazalytskyi.finalProject.web.command;

import org.apache.log4j.Logger;
import com.bazalytskyi.finalProject.Path;
import com.bazalytskyi.finalProject.db.AccountDAO;
import com.bazalytskyi.finalProject.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Update settings items.
 * 
 * @author D.Kolesnikov
 * 
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		log.debug("Command starts");
		
		// UPDATE USER ////////////////////////////////////////////////////////
		
		Account account = (Account) request.getSession().getAttribute("user");
		boolean updateAccount = false;
		
		// update first name
		String firstName = request.getParameter("firstName");
		if (firstName != null && !firstName.isEmpty()) {
			account.setFirstName(firstName);
			updateAccount = true;
		}

		// update last name
		String lastName = request.getParameter("lastName");
		if (lastName != null && !lastName.isEmpty()) {
			account.setLastName(lastName);
			updateAccount = true;
		}

		String localeToSet = request.getParameter("localeToSet");
		if (localeToSet != null && !localeToSet.isEmpty()) {
			HttpSession session = request.getSession();
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);			
			session.setAttribute("defaultLocale", localeToSet);
			account.setLocaleName(localeToSet);
			updateAccount = true;
		}
		
		if (updateAccount)
			new AccountDAO().updateAccount(account);

		
		log.debug("Command finished");
		return Path.PAGE__SETTINGS;
	}

}