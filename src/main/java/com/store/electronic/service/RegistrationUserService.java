package com.store.electronic.service;

import com.store.electronic.dao.DaoException;
import com.store.electronic.dao.RegisterUserDaoImpl;
import com.store.electronic.entity.User;

public class RegistrationUserService {
    private RegisterUserDaoImpl registerUserDao = new RegisterUserDaoImpl();

    public boolean create(User user) throws ServiceException {
        try {
            return registerUserDao.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("failed to read from database");
        }
    }
}

