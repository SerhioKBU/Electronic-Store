package com.store.electronic.controller;

import com.store.electronic.dto.LoginPageDto;
import com.store.electronic.entity.Account;
import com.store.electronic.entity.User;
import com.store.electronic.service.LoginService;
import com.store.electronic.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginControllerDto implements Controller{

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean isCreated = false;
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.isEmpty() || password.isEmpty()) {
            return new ControllerResultDto("profile");
        }

        LoginService loginService = new LoginService();
        LoginPageDto loginPageDto = null;


        try {
            loginPageDto = loginService.assemble(login);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }

        if (loginPageDto.getLogin().equals(login) && loginPageDto.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("loginPageDto", loginPageDto);
            return new ControllerResultDto("profileLogin");
        } else {
            return new ControllerResultDto("error-403");
        }
    }
}
