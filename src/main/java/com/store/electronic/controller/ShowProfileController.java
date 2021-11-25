package com.store.electronic.controller;

import com.store.electronic.entity.User;
import com.store.electronic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfileController implements Controller {
    private UserService userService = new UserService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        User user = userService.findById(userId);

        req.setAttribute("user", user);
        return new ControllerResultDto("profile");
    }
}
