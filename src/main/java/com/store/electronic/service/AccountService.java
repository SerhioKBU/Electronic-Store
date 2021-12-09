package com.store.electronic.service;

import com.store.electronic.dao.AccountDAO;
import com.store.electronic.dao.DaoException;
import com.store.electronic.entity.Account;
import com.store.electronic.entity.Product;
import com.store.electronic.entity.User;

import java.util.List;

public class AccountService implements Service<Account>{
    AccountDAO accountDAO = new AccountDAO();
    @Override
    public List<Account> findAll() throws ServiceException {
        return null;
    }

    @Override
    public Object findById(Integer id) throws ServiceException {
        try {
            return accountDAO.getById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("failed to read from database");
        }
    }

    public boolean findByValue(String value) throws ServiceException {
        try {
            return accountDAO.find(value);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("failed to read from database");
        }
    }

    @Override
    public void buy(User user, Product product) throws ServiceException {
    }
}