//package com.store.electronic.controller;
//
//
//import com.store.electronic.entity.Basket;
//import com.store.electronic.entity.Product;
//import com.store.electronic.entity.User;
//import com.store.electronic.service.BasketService;
//import com.store.electronic.service.ProductService;
//import com.store.electronic.service.ServiceException;
//import com.store.electronic.service.UserService;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class AddBasketController implements Controller {
//
//    private final UserService userService = new UserService();
//    //private final ProductService productService = new ProductService(new CarDaoImpl());
//    private final BasketService basketService = new BasketService();
//
//    @Override
//    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            String productId = req.getParameter("productId");
//            try {
//                Product product = productService.findById(Integer.parseInt(productId));
//            } catch (ServiceException e) {
//                e.printStackTrace();
//            }
//
//            Integer userId = (Integer) req.getSession().getAttribute("userId");
//            req.getSession().setAttribute("userId", null);
//            User user = userService.findById(userId);
//            Basket basket = basketService.findOrCreateForUser(user);
//            basket.getProducts().add(new Product());
//            basketService.createOrUpdate(basket);
//
//            return new ControllerResultDto("products", true);
//        } catch (ServiceException e) {
//            return new ControllerResultDto("error-500");
//        }
//    }
//}
