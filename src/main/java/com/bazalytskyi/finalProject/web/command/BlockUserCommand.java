package com.bazalytskyi.finalProject.web.command;

import com.bazalytskyi.finalProject.Path;
import com.bazalytskyi.finalProject.db.AccountDAO;
import com.bazalytskyi.finalProject.entity.Account;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BlockUserCommand extends Command {
    private static final Logger log = Logger.getLogger(BlockUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");
        AccountDAO accountDAO = AccountDAO.getInstance();
        int id = Integer.parseInt(request.getParameter("userId"));
        Account account = accountDAO.findAccount(id);
        account.setActive(false);
        accountDAO.updateAccount(account);


        log.debug("Commands finished");
        return Path.COMMAND__ADMIN_MENU;
    }
}
