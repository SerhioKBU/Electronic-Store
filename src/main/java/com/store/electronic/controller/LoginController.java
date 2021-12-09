package com.store.electronic.controller;

import com.store.electronic.entity.Account;
import com.store.electronic.entity.User;
import com.store.electronic.service.AccountService;
import com.store.electronic.service.ServiceException;
import com.store.electronic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    private UserService userService = new UserService();
    boolean isFind = false;

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.isEmpty() || password.isEmpty()) {
            return new ControllerResultDto("profile");
        }

        AccountService accountService = new AccountService();
        Account account = new Account();

        try {
            isFind = accountService.findByValue(login);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }

        if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            return new ControllerResultDto("profile");
        } else {
            return new ControllerResultDto("error-403");
        }
    }
}