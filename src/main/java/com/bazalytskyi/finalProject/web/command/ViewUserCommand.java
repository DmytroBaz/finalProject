package com.bazalytskyi.finalProject.web.command;

import com.bazalytskyi.finalProject.Path;
import com.bazalytskyi.finalProject.db.AccountDAO;
import com.bazalytskyi.finalProject.entity.Account;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ViewUserCommand extends Command {

//	private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger log = Logger.getLogger(ViewUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        int userId = Integer.parseInt(request.getParameter("userId"));
        AccountDAO accountDAO = AccountDAO.getInstance();
        Account account = accountDAO.findAccount(userId);
        request.setAttribute("userToUpdate", account);
        log.debug("Command finished");
        return Path.PAGE__ADMIN_UPDATE_USER;
    }

}