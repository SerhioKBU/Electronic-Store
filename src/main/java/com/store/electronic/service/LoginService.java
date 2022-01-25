package com.store.electronic.service;

import com.store.electronic.dao.DaoException;
import com.store.electronic.dto.LoginPageDto;
import com.store.electronic.dao.LoginPageDtoDAO;

public class LoginService {
    private final LoginPageDtoDAO loginPageDtoDAO = new LoginPageDtoDAO();

    public LoginPageDto assemble(String value) throws ServiceException {
        try {
            return loginPageDtoDAO.assemble(value);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Failed to read from database");
        }
    }
}
