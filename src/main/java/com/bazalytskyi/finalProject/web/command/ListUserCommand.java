package com.bazalytskyi.finalProject.web.command;

import com.bazalytskyi.finalProject.Path;
import com.bazalytskyi.finalProject.db.AccountDAO;
import com.bazalytskyi.finalProject.db.TestDAO;
import com.bazalytskyi.finalProject.entity.Account;
import com.bazalytskyi.finalProject.entity.Test;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListUserCommand extends Command {
    private static final Logger log = Logger.getLogger(ListUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");
        List<Account> accounts = AccountDAO.getInstance().findAll();
        log.trace("Found in DB List accounts" + accounts);
        request.setAttribute("accounts", accounts);
        log.trace("Set the request attribute: accounts --> " + accounts);

        log.debug("Commands finished");
        return Path.PAGE__ADMIN_ALL_USERS;

    }
}
