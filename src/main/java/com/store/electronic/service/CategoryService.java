package com.store.electronic.service;

import com.store.electronic.dao.CategoryDAO;
import com.store.electronic.dao.DaoException;
import com.store.electronic.entity.Category;
import com.store.electronic.entity.Product;
import com.store.electronic.entity.User;

import java.util.List;

public class CategoryService implements Service<Category>{
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public List<Category> findAll() throws ServiceException {
        try {
            return categoryDAO.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("failed to read from database");
        }
    }

    @Override
    public Object findById(Integer id) throws ServiceException {
        return null;
    }

    @Override
    public void buy(User user, Product product) throws ServiceException {

    }
}
