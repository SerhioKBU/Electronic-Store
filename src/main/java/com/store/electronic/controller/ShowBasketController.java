package com.store.electronic.controller;

import com.store.electronic.entity.Basket;
import com.store.electronic.entity.User;
import com.store.electronic.service.BasketService;
import com.store.electronic.service.ServiceException;
import com.store.electronic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBasketController implements Controller {

    private final UserService userService = new UserService();
    private final BasketService basketService = new BasketService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            User user = userService.findById(userId);
            Basket basket = basketService.findOrCreateForUser(user);
            req.setAttribute("basket", basket);
            return new ControllerResultDto("basket");
        } catch (ServiceException e) {
            return new ControllerResultDto("error-500");
        }
    }
}
