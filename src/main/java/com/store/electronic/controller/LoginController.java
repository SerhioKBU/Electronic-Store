package com.store.electronic.controller;

import com.store.electronic.entity.User;
import com.store.electronic.service.ServiceException;
import com.store.electronic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    private UserService userService = new UserService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        if (userName == null || password == null) {
            return new ControllerResultDto("error");
        }

        User user = null;
        user = userService.findByUserName(userName);
        System.out.println("user:  " + user);

        if (user.getPassword().equals(password)) {
            req.setAttribute("user", user);

            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getId());
            return new ControllerResultDto("profile");
        } else {
            return new ControllerResultDto("error-403");
        }
    }
}
