package com.store.electronic.controller;

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
    private CategoryService categoryService = new CategoryService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> serviceList = null;

        try {
            serviceList = categoryService.findAll();

        } catch (ServiceException e) {
            e.printStackTrace();
            return new ControllerResultDto("error");
        }

        HttpSession session = req.getSession();
        session.setAttribute("serviceList", serviceList);
        for (Category s : serviceList) {
            out.println(s);
        }
        return new ControllerResultDto("category");
    }
}
