package com.bazalytskyi.finalProject.web.command;

import com.bazalytskyi.finalProject.db.TestDAO;
import com.bazalytskyi.finalProject.entity.Test;
import org.apache.log4j.Logger;
import com.bazalytskyi.finalProject.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListTestCommand extends Command {
    private static final Logger log = Logger.getLogger(ListTestCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");
        List<Test> tests = new TestDAO().findAllTests();
        log.trace("Found in DB List tests" + tests);
        request.setAttribute("tests", tests);
        log.trace("Set the request attribute: tests --> " + tests);

        log.debug("Commands finished");
        return Path.PAGE__USER_TESTS;

    }
}
