package com.store.electronic.controller;

import com.store.electronic.entity.Account;
import com.store.electronic.entity.User;
import com.store.electronic.service.RegistrationUserService;
import com.store.electronic.service.ServiceException;
import com.store.electronic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationController implements Controller{

    private UserService userService = new UserService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean isCreated = false;
        String login = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if (login == null || password == null || email == null) {
            return new ControllerResultDto("error");
        }

        User user = new User();

        //user = userService.findByUserName(userName);
        RegistrationUserService registrationUserService = new RegistrationUserService();
        user.setAccount(new Account());
        user.getAccount().setLogin(login).setPassword(password).setEmail(email);
        System.out.println("user:  " + user);

        try {
            isCreated = registrationUserService.create(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }

        if (isCreated) {
            req.setAttribute("account", user.getAccount());
            req.getSession(true).setAttribute("userId", user.getUserName());
            return new ControllerResultDto("registrationProfile");
        } else {
            return new ControllerResultDto("error-403");
        }
    }
}
