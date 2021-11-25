package com.store.electronic.service;

import com.store.electronic.dao.DaoException;
import com.store.electronic.dao.RegisterClientDaoImpl;
import com.store.electronic.entity.User;

public class RegistrationUserService {
    private RegisterClientDaoImpl registerClientDao = new RegisterClientDaoImpl();

    public boolean create(User user) throws ServiceException {
        try {
            return registerClientDao.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("failed to read from database");
        }
    }
}

