package com.store.electronic.controller;

import com.store.electronic.entity.Account;
import com.store.electronic.entity.User;
import com.store.electronic.service.RegistrationUserService;
import com.store.electronic.service.ServiceException;
import com.store.electronic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Registration of new account for user
 * @author serhii_chebanov
 */
public class RegistrationController implements Controller{

    /**
     * @param req - Http Servlet Request
     * @param resp - Http Servlet Response
     * @return User Account Profile
     */
    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        RegistrationUserService registrationUserService = new RegistrationUserService();

        boolean isCreated = false;
        String login = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");

        if (login.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            return new ControllerResultDto("registration");
        }

        User user = new User();
        user.setAccount(new Account());
        user.getAccount().setLogin(login).setPassword(password);
        user.setUserName(name).setEmail(email);
        System.out.println("user:  " + user);

        try {
            isCreated = registrationUserService.create(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }

        if (isCreated) {
            req.setAttribute("account", user.getAccount());
            req.getSession(true).setAttribute("userId", user.getId());
            return new ControllerResultDto("registrationProfile");
        } else {
            return new ControllerResultDto("error-403");
        }
    }
}