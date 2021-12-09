package com.store.electronic.controller;

import com.store.electronic.authorisation.AuthUser;
import com.store.electronic.dto.LoginPageDto;
import com.store.electronic.entity.RoleEnum;
import com.store.electronic.service.LoginService;
import com.store.electronic.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginControllerDto implements Controller{
    boolean dataFound;

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.isEmpty() || password.isEmpty()) {
            return new ControllerResultDto("profile");
        }

        LoginService loginService = new LoginService();
        LoginPageDto loginPageDto = new LoginPageDto();
        loginPageDto.setLogin(login).setPassword(password);

        try {
            //dataFound = loginService.assemble(login);
            loginPageDto = loginService.assemble(login);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }

        if (loginPageDto.getLogin().equals(login) &&
                loginPageDto.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("loginPageDto", loginPageDto);
            session.setAttribute("authUser", new AuthUser().setLogin(login).setRole(RoleEnum.USER));
            return new ControllerResultDto("profileLogin");
        } else {
            return new ControllerResultDto("error-403");
        }
    }
}


