package com.store.electronic.service;

import com.store.electronic.dao.BasketDaoImpl;
import com.store.electronic.dao.DaoException;
import com.store.electronic.entity.Basket;
import com.store.electronic.entity.User;

import java.util.ArrayList;

public class BasketService {

    private final BasketDaoImpl basketDao = new BasketDaoImpl();

    public Basket createOrUpdate(Basket basket) throws ServiceException {
        try {
            return basketDao.insertOrUpdate(basket);
        } catch (DaoException e) {
            throw new ServiceException("Failed to save into database");
        }
    }

    public Basket findOrCreateForUser(User user) throws ServiceException {
        try {
            Basket basket = basketDao.findById(user);

            System.out.println("BASKET: " + basket);
            return basket == null ? new Basket(null, user, new ArrayList<>()) : basket;

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Failed to find");

        }
    }
}
