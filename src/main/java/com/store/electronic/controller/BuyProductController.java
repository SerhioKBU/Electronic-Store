package com.store.electronic.controller;

import com.store.electronic.dao.ProductDAO;
import com.store.electronic.entity.Product;
import com.store.electronic.entity.User;
import com.store.electronic.service.ProductService;
import com.store.electronic.service.ServiceException;
import com.store.electronic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyProductController implements Controller{
    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService(new ProductDAO());

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        //try {
//            int productId = Integer.parseInt(req.getParameter("productId"));
//            Integer userId = (Integer) req.getSession().getAttribute("userId");
//            Product product = productService.findById(productId);
//            User user = userService.findById(userId);
//            userService.buy(user, product);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
        return new ControllerResultDto("buyProduct");

    }
}
