package com.bazalytskyi.finalProject.web.command;

import com.bazalytskyi.finalProject.Path;
import com.bazalytskyi.finalProject.db.AccountDAO;
import com.bazalytskyi.finalProject.entity.Account;
import org.apache.log4j.Logger;

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
public class UpdateUserCommand extends Command {

	private static final Logger log = Logger.getLogger(UpdateUserCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		boolean updateAccount = false;
		log.debug("Command starts");
		
		// UPDATE USER ////////////////////////////////////////////////////////

		int userId = Integer.parseInt(request.getParameter("userId"));
		AccountDAO accountDAO = AccountDAO.getInstance();
		Account account = accountDAO.findAccount(userId);
		
		// update first name
		String firstName = request.getParameter("firstName");
		if (firstName != null && !firstName.isEmpty() && !firstName.equals(account.getFirstName())) {
			account.setFirstName(firstName);
			updateAccount = true;
		}

		// update last name
		String lastName = request.getParameter("lastName");
		if (lastName != null && !lastName.isEmpty() && !lastName.equals(account.getLastName())) {
			account.setLastName(lastName);
			updateAccount = true;
		}
		
		if (updateAccount) {
			AccountDAO.getInstance().updateAccount(account);
		}
		
		log.debug("Command finished");
		return Path.COMMAND__ADMIN_MENU;
	}

}