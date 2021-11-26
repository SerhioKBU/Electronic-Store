package com.store.electronic.controller;

import com.store.electronic.dao.DaoException;
import com.store.electronic.entity.Product;
import com.store.electronic.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllProductsController implements Controller {
    ProductService productService;

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            List<Product> allProducts = productService.findAll();
            req.setAttribute("products", allProducts);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
