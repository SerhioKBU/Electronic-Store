package com.store.electronic.controller;

import com.store.electronic.dao.CategoryDAO;
import com.store.electronic.entity.Category;
import com.store.electronic.service.CategoryService;
import com.store.electronic.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class CategoryController implements Controller{
    private final CategoryService categoryService = new CategoryService(new CategoryDAO());

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> categoryList = null;

        try {
            categoryList = categoryService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }

        HttpSession session = req.getSession();
        session.setAttribute("categoryList", categoryList);
        for (Category s : categoryList) {
            out.println(s);
        }
        return new ControllerResultDto("category");
    }
}
