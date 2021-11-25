package com.store.electronic.service;

import com.store.electronic.entity.BaseEntity;
import com.store.electronic.entity.Product;
import com.store.electronic.entity.User;

import java.util.List;

public interface Service<T extends BaseEntity>{
    List<T> findAll() throws ServiceException;
    Object findById(Integer id) throws ServiceException;;
    void buy(User user, Product product) throws ServiceException;
}
