package com.store.electronic.service;


import com.store.electronic.dao.DaoException;
import com.store.electronic.dao.ProductDAO;
import com.store.electronic.entity.Product;
import lombok.AllArgsConstructor;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> findAll() throws DaoException {
        List<Product> products = productDAO.findAll();
        return products;
    }

    public Product findById(int id) throws ServiceException {
        try {
            return productDAO.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("");
        }
    }
}
