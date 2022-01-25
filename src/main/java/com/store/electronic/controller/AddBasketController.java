package com.store.electronic.controller;

import com.store.electronic.dao.CategoryDAO;
import com.store.electronic.entity.Basket;
import com.store.electronic.entity.Category;
import com.store.electronic.entity.User;
import com.store.electronic.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBasketController implements Controller {

    private final UserService userService = new UserService();
    //private final ProductService productService = new ProductService(new ProductDAO());
    private final CategoryService categoryService = new CategoryService(new CategoryDAO());
    private final BasketService basketService = new BasketService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String categoryId = req.getParameter("categoryId");
            System.out.println("categoryId: " + categoryId);
            Category category = categoryService.findById(Integer.valueOf(categoryId));
            System.out.println("categoryName: " + category.getName());

            Integer userId = (Integer) req.getSession().getAttribute("userId");
            System.out.println("userId: " + userId);

            req.getSession().setAttribute("userId", userId);
            User user = userService.findById(userId);
            System.out.println("user: " + user);

            Basket basket = basketService.findOrCreateForUser(user);
            basket.getCategories().add(category);
            basketService.createOrUpdate(basket);

            return new ControllerResultDto("basket", true);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error-500");
        }
    }
}
